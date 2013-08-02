package com.light;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.RemoteViews;

public class WeiboWidgetProvider extends AppWidgetProvider {
	public WeiboWidgetProvider(){
		
	}
	
	@Override
	public void onEnabled(Context context) {
		Log.i("GoGo", "onEnabled");
		super.onEnabled(context);
	}
	
	@Override
    public void onReceive(Context ctx, Intent intent) {
		Log.i("GoGo", "onReceive");
		super.onReceive(ctx, intent);
	}
	
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i("GoGo", "onUpdate");
		for (int i = 0; i < appWidgetIds.length; ++i) {			
			final RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			rv.setImageViewBitmap(R.id.up_arrow, buildUpdate(context, "\uf077"));
			
			Intent intent = new Intent(context, DialogActivity.class);
	        PendingIntent pending_intent = PendingIntent.getActivity(context, 0, intent, 0);
			rv.setOnClickPendingIntent(R.id.up_arrow, pending_intent);
			rv.setImageViewBitmap(R.id.down_arrow, buildUpdate(context, "\uf078"));
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