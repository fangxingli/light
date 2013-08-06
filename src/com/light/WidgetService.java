package com.light;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
    private Status[] mStatus = null;
    
	public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }
	
	@Override
	public void onCreate() {
		
	}

	@Override
	public void onDataSetChanged() {
		Log.i("GoGo", "Service 收到更新命令");
		mStatus = DataProvider.sStatues;
	}

	@Override
	public void onDestroy() {
		mStatus = null;
	}

	@Override
	public int getCount() {
		if( mStatus != null)
			return mStatus.length;
		return 0;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		// Get the data for this position from the content provider
		Log.i("GoGo", "更新第" + String.valueOf(position) + "个");
		Status item = mStatus[position];
		
        final int item_layout_id = R.layout.widget_item;
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), item_layout_id);
        
        rv.setTextViewText(R.id.user_name, item.getUser().getName());
        rv.setTextViewText(R.id.user_time, item.getUser().getCreatedAt());
        rv.setTextViewText(R.id.content_text, item.getText());
        
        rv.setImageViewResource(R.id.logo, R.drawable.sina);

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	
}