package com.light;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
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
		Typeface font = Typeface.createFromAsset( context.getAssets(), "fontawesome-webfont.ttf" );
		for (int i = 0; i < appWidgetIds.length; ++i) {			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			FrameLayout fl = (FrameLayout) inflater.inflate(R.layout.widget_layout, null);
				
			Button up_b = (Button)fl.findViewById(R.id.up_arrow);
			up_b.setTypeface(font);
				
			Button down_b = (Button)fl.findViewById(R.id.down_arrow);
			down_b.setTypeface(font);
			
			final RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			appWidgetManager.updateAppWidget(appWidgetIds[i], rv);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
}