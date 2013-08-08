package com.light;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.light.WeiboAPI.FEATURE;
import com.light.sina.bean.JSON2Java;
import com.light.sina.bean.Status;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.net.RequestListener;

public class DataProvider extends ContentProvider {
	public static final Uri CONTENT_URI =
	        Uri.parse("content://com.light.android.weibo.provider");

	public static Status[] sStatues = null;
	@Override
	public boolean onCreate() {
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		Log.i("GoGo", "接收到更新命令");
		final Uri uri_ = uri;
		if( uri.getPath().contentEquals(CONTENT_URI.getPath()) )
			MainActivity.accessToken = new Oauth2AccessToken("2.00MI1B_CE7mDOC281db9aa0bgbqUPD", "157679999");
			Log.i("GoGo", "uri 相同" + MainActivity.accessToken.getRefreshToken() + String.valueOf(MainActivity.accessToken.getExpiresTime()));
			if( MainActivity.accessToken.isSessionValid() ){
				Log.i("GoGo", "accessToken 成功");
				StatusesAPI status_api = new StatusesAPI(MainActivity.accessToken);
				Log.i("GoGo", "开始更新");
				status_api.friendsTimeline(0, 0, 20, 1, false, FEATURE.ALL, false, 
						new RequestListener(){
							public void onComplete(String arg0) {
								try{
									Log.i("GoGo", "更新完毕了");
									sStatues = new JSON2Java(arg0).getStatus();
									StatusProcesser.INSTANCE.updateCurrentStatusList(sStatues);
									Log.i("GoGo", "通知Observer更新");
									DataProvider.this.getContext().getContentResolver().notifyChange(uri_, null);
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							public void onComplete4binary(ByteArrayOutputStream arg0) {
							}
	
							public void onError(WeiboException arg0) {
								Log.i("GoGo", "Token 获得失败");
							}
	
							public void onIOException(IOException arg0) {
							}
				});
				return 20;
			}else{
				Log.i("GoGo", "accessToken 失败");
			}
		return 0;
	}
	
}