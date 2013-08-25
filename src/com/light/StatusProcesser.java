package com.light;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import android.appwidget.AppWidgetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.widget.RemoteViews;

import com.light.sina.bean.Status;

public enum StatusProcesser{
	INSTANCE;
	
	private static final int CACHE_SIZE = 4*1024*1024;
	private static final int LIST_MAX = 80;
	private List<Status> mStatusList = new ArrayList<Status>(LIST_MAX);
	private int mLastCursor = -1;
	private Set<String> mImageUris = new HashSet<String>();
	private LruCache<String, Bitmap> mBitmapCache = new LruCache<String, Bitmap>(CACHE_SIZE){
		protected int sizeOf(byte[] key, Bitmap value) {
	           return value.getByteCount();
		}
	};
	
	public int updateCurrentStatusList(Status[] array, AppWidgetManager widget_manager, int[] appWidgetIds, RemoteViews rv){
		int count = 0;
		if( mStatusList.isEmpty() ){
			for(int i=array.length-1; i>=0; i--){
				mStatusList.add(array[i]);
				mLastCursor++;
				putImageLru(array, i);
				count++;
			}
		}else{
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
				}
				putImageLru(array, i);
				count++;
			}
		}
		
		downloadImages();
		for(int i=0; i<appWidgetIds.length; i++){
			widget_manager.updateAppWidget(appWidgetIds[i], rv);
		}
		return count;
	}
	
	public List<Status> getStatusList(){
		return mStatusList;
	}
	
	private void putImageLru(Status[] array, int i){
		// 下头像
		mImageUris.add(mStatusList.get(mLastCursor).getUser().getProfileImageUrl());
		// 下微博图片
		JSONArray arr = array[i].getPicUrls();
		for(int j=0; j<arr.length(); j++ ){
			try {
				mImageUris.add(arr.getJSONObject(j).getString("thumbnail_pic"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// 下转发图片
		if( mStatusList.get(mLastCursor).getRetweetedStatus() != null ){
			JSONArray retweet_pics = mStatusList.get(mLastCursor).getRetweetedStatus().getPicUrls();
			for(int j=0; j<retweet_pics.length(); j++ ){
				try {
					mImageUris.add(retweet_pics.getJSONObject(j).getString("thumbnail_pic"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void downloadImages(){
//		new Thread(){
//			public void run(){
				Log.i("GoGo", "开始图片下载 mImageUris:" + mImageUris.size());
				for(String item : mImageUris ){
//					String item = mImageUris.get(i);
					if( isBitmapReady(item) )
						continue;
					Bitmap bitmap=null;
			        try {
			            Log.i("GoGo", "图片" + item + "开始下载");
			            URL url = new URL(item);
			            HttpURLConnection con=(HttpURLConnection) url.openConnection();
			            con.setDoInput(true);
			            con.setReadTimeout(2000);
			            con.connect();
			            InputStream inputStream=con.getInputStream();
			            
			            bitmap=BitmapFactory.decodeStream(inputStream); 
			            inputStream.close();
			            
			            synchronized(mBitmapCache){
			            	mBitmapCache.put(item, bitmap);
			            	Log.i("GoGo", "图片大小" + item + ":" + String.valueOf(bitmap.getByteCount()));
			            }
			            Log.i("GoGo", "图片" + item + "下载完成");
			        }catch (MalformedURLException e) {
			            e.printStackTrace();
		            }catch (IOException e) {
			            e.printStackTrace();
			        }
				}
//			}
//		}.start();
	}
	
	public void downloadOnePicture(final String uri, final int id){
		Bitmap bitmap=null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setReadTimeout(2000);
            con.connect();
            InputStream inputStream=con.getInputStream();
            
            bitmap=BitmapFactory.decodeStream(inputStream); 
            inputStream.close();
            
            synchronized(mBitmapCache){
            	mBitmapCache.put(uri, bitmap);
            }
//            Intent intent = new Intent("user_pic_update");
//            intent.putExtra("id", id);
//            mContext.sendBroadcast(intent);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public boolean isBitmapReady(String uri){
		synchronized(mBitmapCache){
//			Log.i("GoGoL", "拿" + mBitmapCache.get(MessageDigest.getInstance("MD5").digest(uri.getBytes("UTF-8"))));
			return mBitmapCache.get(uri) == null ? false : true;
		}
	}
	
	public Bitmap getLruCacheImage(String uri){
		synchronized(mBitmapCache){
			return mBitmapCache.get(uri);
		}
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