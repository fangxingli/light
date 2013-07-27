package com.light;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
		
		setContentView(R.layout.activity_main);
		
		Button up_button = (Button)findViewById(R.id.up_arrow);
		up_button.setTypeface(font);
		
		Button down_button = (Button)findViewById(R.id.down_arrow);
		down_button.setTypeface(font);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
