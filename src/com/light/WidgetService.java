package com.light;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.light.sina.bean.Status;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
	private Context mContext;
    private Cursor mCursor;
    private int mAppWidgetId;
    private List<Status> mStatus = null;
    
    private ArrayList<RemoteViews> mRemoteViewList = null;
    
    private BroadcastReceiver mReceiver = new BroadcastReceiver(){
    	public void onReceive(Context context, Intent intent){
    		if( intent.getAction().contentEquals("user_pic_update") ){
    			int id = intent.getIntExtra("id", -1);
    			if( id != -1 ){
    				Log.i("GoGo", "更新图片: " + String.valueOf(id));
    				mRemoteViewList.get(id).setImageViewBitmap(
    						R.id.user_pic, 
    						StatusProcesser.INSTANCE.getLruCacheImage(mStatus.get(id).getUser().getProfileImageUrl()));
    			}
    		}
    	}
    };
    
	public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }
	
	@Override
	public void onCreate() {
		IntentFilter filter = new IntentFilter();
		filter.addAction("user_pic_update");
		mContext.registerReceiver(mReceiver, filter);
		
		if( mRemoteViewList == null )
			mRemoteViewList = new ArrayList<RemoteViews>();
	}

	@Override
	public void onDataSetChanged() {
		Log.i("GoGo", "Service 收到更新命令");
//		mStatus = DataProvider.sStatues;
		mStatus = StatusProcesser.INSTANCE.getStatusList();
		mRemoteViewList.clear();
	}

	@Override
	public void onDestroy() {
		mStatus = null;
	}

	@Override
	public int getCount() {
		if( mStatus != null)
			return mStatus.size();
		return 0;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		// Get the data for this position from the content provider
		Log.i("GoGo", "更新第" + String.valueOf(position) + "个");
		Status item = mStatus.get(position);
		
        final int item_layout_id = R.layout.widget_item;
        final RemoteViews rv = new RemoteViews(mContext.getPackageName(), item_layout_id);
        
        rv.setTextViewText(R.id.user_name, item.getUser().getName());
        rv.setTextViewText(R.id.user_time, item.getUser().getCreatedAt());
        rv.setTextViewText(R.id.content_text, item.getText());
        
        rv.setImageViewResource(R.id.logo, R.drawable.sina);
        
        if( StatusProcesser.INSTANCE.isBitmapReady(item.getCreatedAt()) )
        	rv.setImageViewBitmap(R.id.user_pic, StatusProcesser.INSTANCE.getLruCacheImage(item.getUser().getProfileImageUrl()) );
        else
        	StatusProcesser.INSTANCE.downloadOnePicture(item.getUser().getProfileImageUrl(), position);
        
        mRemoteViewList.add(rv);
        // Set the click intent so that we can handle it and show a toast message
//        final Intent fillInIntent = new Intent();
//        final Bundle extras = new Bundle();
//        extras.putString(WeatherWidgetProvider.EXTRA_DAY_ID, day);
//        fillInIntent.putExtras(extras);
//        rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

        return rv;
	}

	@Override
	public RemoteViews getLoadingView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	
}