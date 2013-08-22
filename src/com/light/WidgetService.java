package com.light;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.text.Html;
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
		Log.i("GoGo", "onDataSetChanged 收到更新命令");
//		mStatus = StatusProcesser.INSTANCE.getStatusList();
//		mRemoteViewList.clear();
//		DataProvider.INSTANCE.update();
		
		mStatus = StatusProcesser.INSTANCE.getStatusList();
		mRemoteViewList.clear();
		Log.i("GoGo", "onDataSetChanged 完成");
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
		Status item = mStatus.get(mStatus.size() - position - 1);
		
        final int item_layout_id = R.layout.widget_item;
        final RemoteViews rv = new RemoteViews(mContext.getPackageName(), item_layout_id);
        
        rv.setTextViewText(R.id.user_name, item.getUser().getName());
        rv.setTextViewText(R.id.user_time, item.getCreatedAt());
        rv.setTextViewText(R.id.content_text, item.getText());
        
        rv.setImageViewResource(R.id.logo, R.drawable.sina);
        // 更新头像
        if( StatusProcesser.INSTANCE.isBitmapReady(item.getUser().getProfileImageUrl()) ){
        	rv.setImageViewBitmap(R.id.user_pic, StatusProcesser.INSTANCE.getLruCacheImage(item.getUser().getProfileImageUrl()) );
        }
        // 更新微博图片
        if( item.getPicUrls().length() > 0 ){
        	JSONArray arr = item.getPicUrls();
        	for(int j=0; j<arr.length(); j++ ){
				try {
					if( StatusProcesser.INSTANCE.isBitmapReady(arr.getJSONObject(j).getString("thumbnail_pic")) )
						rv.setImageViewBitmap(R.id.image10, StatusProcesser.INSTANCE.getLruCacheImage(arr.getJSONObject(j).getString("thumbnail_pic")) );
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
        }
        // 更新转发内容
        if( item.getRetweetedStatus() != null ){
        	Status retweeted_status = item.getRetweetedStatus();
        	rv.setTextViewText(R.id.retweet_content_text, 
        			Html.fromHtml(
        					"<font color=\"#ff0000\">" + retweeted_status.getUser().getName() + ": </font>" +
        					retweeted_status.getText())
        	);
        	if( retweeted_status.getPicUrls().length() > 0 ){
            	JSONArray arr = retweeted_status.getPicUrls();
            	for(int j=0; j<arr.length(); j++ ){
    				try {
    					if( StatusProcesser.INSTANCE.isBitmapReady(arr.getJSONObject(j).getString("thumbnail_pic")) )
    						rv.setImageViewBitmap(R.id.retweet_image, StatusProcesser.INSTANCE.getLruCacheImage(arr.getJSONObject(j).getString("thumbnail_pic")) );
    				} catch (JSONException e) {
    					e.printStackTrace();
    				}
    			}
            }
        }
        
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