package com.light;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

class WeiboData{
	private String mDate = null;
	private String mText = null;
	private String mSource = null;
	private String mHeadImage = null;
	private String mOriginName = null;
	private String mRepostCount = null;
	private String mCommentCount = null;
	private String mattitudeCount = null;
	private String mContentPictures= null;
}

public class DataProvider extends ContentProvider {
	public static final Uri CONTENT_URI =
	        Uri.parse("content://com.light.android.weibo.provider");

	@Override
	public boolean onCreate() {
		if( MainActivity.accessToken.isSessionValid() ){
			StatusesAPI status_api = new StatusesAPI(MainActivity.accessToken);
			status_api.friendsTimeline(0, 0, 20, 1, 0, 0, 0, listener)
		}
		return false;
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
		// TODO Auto-generated method stub
		return 0;
	}
	
}