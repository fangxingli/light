package com.light;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.appwidget.AppWidgetManager;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.light.WeiboAPI.FEATURE;
import com.light.sina.bean.JSON2Java;
import com.light.sina.bean.Status;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.net.RequestListener;

public enum DataProvider {
	INSTANCE;

//	private AppWidgetManager mAppWidgetManager = null;
//	private int[] mAppWidgetIds = null;
//	private RemoteViews mRemoteViews = null;
	public static Status[] sStatues = null;
	
	public int update(final AppWidgetManager widget_manager, final int[] appWidgetIds, final RemoteViews rv) {
		Log.i("GoGo", "接收到更新命令");
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
								sStatues = new JSON2Java(arg0).getStatus();
								Log.i("GoGo", "更新完毕了 sStatus 长度: " + sStatues.length);
								StatusProcesser.INSTANCE.updateCurrentStatusList(sStatues, widget_manager, appWidgetIds, rv);
								Log.i("GoGo", "通知Observer更新");
//								DataProvider.this.getContext().getContentResolver().notifyChange(uri_, null);
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