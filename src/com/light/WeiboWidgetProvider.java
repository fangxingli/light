package com.light;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.RemoteViews;

class DataProviderObserver extends ContentObserver {
    private AppWidgetManager mAppWidgetManager;
    private ComponentName mComponentName;

    public DataProviderObserver(AppWidgetManager mgr, ComponentName cn, Handler h) {
        super(h);
        mAppWidgetManager = mgr;
        mComponentName = cn;
    }

    @Override
    public void onChange(boolean selfChange) {
        // The data has changed, so notify the widget that the collection view needs to be updated.
        // In response, the factory's onDataSetChanged() will be called which will requery the
        // cursor for the new data.
    	Log.i("GoGo", "Observer 收到通知");
        mAppWidgetManager.notifyAppWidgetViewDataChanged(
                mAppWidgetManager.getAppWidgetIds(mComponentName), R.id.list);
    }
}

public class WeiboWidgetProvider extends AppWidgetProvider {
	private DataProviderObserver sDataObserver = null;
	
	private static Handler sWorkerQueue;
	private static HandlerThread sWorkerThread;
    private static final String REFRESH_ACTION = "fresh";
	public WeiboWidgetProvider(){
		sWorkerThread = new HandlerThread("WeatherWidgetProvider-worker");
        sWorkerThread.start();
        sWorkerQueue = new Handler(sWorkerThread.getLooper());
	}
	
	@Override
	public void onEnabled(Context context) {
		Log.i("GoGo", "onEnabled");
		super.onEnabled(context);
		
		
		final ContentResolver r = context.getContentResolver();
        if (sDataObserver == null) {
            final AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            final ComponentName cn = new ComponentName(context, WeiboWidgetProvider.class);
            sDataObserver = new DataProviderObserver(mgr, cn, sWorkerQueue);
            r.registerContentObserver(DataProvider.CONTENT_URI, true, sDataObserver);
        }
	}
	
	@Override
    public void onReceive(Context ctx, Intent intent) {
		final String action = intent.getAction();
        if (action.equals(REFRESH_ACTION)) {
        	final ContentResolver r = ctx.getContentResolver();
            sWorkerQueue.removeMessages(0);
            sWorkerQueue.post(new Runnable(){
            	public void run(){
            		r.update(DataProvider.CONTENT_URI, null, null, null);
            		Log.i("GoGo", "发送更新命令");
            	}
            });
        }
		super.onReceive(ctx, intent);
	}
	
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i("GoGo", "onUpdate");
		for (int i = 0; i < appWidgetIds.length; ++i) {			
			final RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			
			Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            
            rv.setRemoteAdapter(R.id.list, intent);

            // Set the empty view to be displayed if the collection is empty.  It must be a sibling
            // view of the collection view.
            rv.setEmptyView(R.id.list, R.id.empty_view);
			
//			intent = new Intent(context, DialogActivity.class);
//	        PendingIntent pending_intent = PendingIntent.getActivity(context, 0, intent, 0);
//			rv.setOnClickPendingIntent(R.id.up_arrow, pending_intent);
			
			final Intent refreshIntent = new Intent(context, WeiboWidgetProvider.class);
            refreshIntent.setAction(WeiboWidgetProvider.REFRESH_ACTION);
            final PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 0,
                    refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.up_arrow, refreshPendingIntent);
            
			rv.setImageViewBitmap(R.id.up_arrow, buildUpdate(context, "\uf077"));
			appWidgetManager.updateAppWidget(appWidgetIds[i], rv);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	private Bitmap buildUpdate(Context context, String text) 
	{
	    Bitmap myBitmap = Bitmap.createBitmap(160, 84, Bitmap.Config.ARGB_4444);
	    Canvas myCanvas = new Canvas(myBitmap);
	    Paint paint = new Paint();
	    Typeface clock = Typeface.createFromAsset(context.getAssets(),"fontawesome-webfont.ttf");
	    paint.setAntiAlias(true);
	    paint.setSubpixelText(true);
	    paint.setTypeface(clock);
	    paint.setStyle(Paint.Style.FILL);
	    paint.setTextSize(30);
	    paint.setTextAlign(Align.CENTER);
	    myCanvas.drawText(text, 80, 60, paint);
	    return myBitmap;
	}
}