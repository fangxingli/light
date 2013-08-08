package com.light;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.light.sina.bean.Status;

public enum StatusProcesser{
	INSTANCE;
	
	private static final int CACHE_SIZE = 4*1024*1024;
	private static final int LIST_MAX = 80;
	private List<Status> mStatusList = new ArrayList<Status>(LIST_MAX);
	private int mLastCursor = -1;
	private List<String> mImageUris = new ArrayList<String>();
	private LruCache<byte[], Bitmap> mBitmapCache = new LruCache<byte[], Bitmap>(CACHE_SIZE){
		protected int sizeOf(byte[] key, Bitmap value) {
	           return value.getByteCount();
		}
	};
	
	public int updateCurrentStatusList(Status[] array){
		int count = 0;
		if( mStatusList.isEmpty() ){
			for(int i=array.length-1; i>=0; i--){
					mStatusList.add(array[i]);
					mLastCursor++;
			}
		}
		
		for(int i=array.length-1; i>=0; i--){
			Date current_max_date = getWeiboDate(mStatusList.get(mLastCursor).getCreatedAt());
			// 新微博时间在已存在微波时间之后
			if( getWeiboDate(array[i].getCreatedAt()).after(current_max_date) ){
				if( mStatusList.size() < LIST_MAX ){
					mStatusList.add(array[i]);
					mLastCursor++;
				}else{
					mLastCursor = (mLastCursor+1)%LIST_MAX;
					mStatusList.set(mLastCursor, array[i]);
				}
				mImageUris.add(mStatusList.get(mLastCursor).getCreatedAt());
				count++;
			}
		}
		downloadImages();
		return count;
	}
	
	private void downloadImages(){
		new Thread(){
			public void run(){
				for( String item : mImageUris ){
					Bitmap bitmap=null;
			        try {
			            URL url = new URL(item);
			            HttpURLConnection con=(HttpURLConnection) url.openConnection();
			            con.setDoInput(true);
			            con.setReadTimeout(2000);
			            con.connect();
			            InputStream inputStream=con.getInputStream();
			            
			            bitmap=BitmapFactory.decodeStream(inputStream); 
			            inputStream.close();
			            
			            synchronized(mBitmapCache){
			            	mBitmapCache.put(MessageDigest.getInstance("MD5").digest(item.getBytes("UTF-8")), bitmap);
			            }
			        }catch (MalformedURLException e) {
			            e.printStackTrace();
		            }catch (IOException e) {
			            e.printStackTrace();
			        }catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}.start();
	}
	
	public boolean isBitmapReady(String uri){
		synchronized(mBitmapCache){
			try {
				return mBitmapCache.get(MessageDigest.getInstance("MD5").digest(uri.getBytes("UTF-8"))) == null ? false : true;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Bitmap getLruCacheImage(String uri){
		synchronized(mBitmapCache){
			try {
				return mBitmapCache.get(MessageDigest.getInstance("MD5").digest(uri.getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private Date getWeiboDate(String s){
		Date d = null;
		try{
			d = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy").parse(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return d;
	}
}