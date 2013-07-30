package com.light;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;

public class MainActivity extends Activity {
	
	private WebView	mWebView;
	private Weibo mWeibo = null;
	
	public static Oauth2AccessToken accessToken;
	
	class AuthDialogListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
        	
        	String code = values.getString("code");
        	if(code != null){
	        	Toast.makeText(MainActivity.this, "认证code成功" + code, Toast.LENGTH_SHORT).show();
	        	return;
        	}
            String token = values.getString("access_token");
            String expires_in = values.getString("expires_in");
            MainActivity.accessToken = new Oauth2AccessToken(token, expires_in);
            if (MainActivity.accessToken.isSessionValid()) {
                String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                        .format(new java.util.Date(MainActivity.accessToken
                                .getExpiresTime()));
             
                AccessTokenKeeper.keepAccessToken(MainActivity.this, accessToken); // 保存access_token
                Toast.makeText(MainActivity.this, "认证成功", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        @Override
        public void onError(WeiboDialogError e) {
            Toast.makeText(getApplicationContext(),
                    "Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "Auth cancel",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(getApplicationContext(),
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }

    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
		
		setContentView(R.layout.activity_main);
		
		mWeibo = Weibo.getInstance(ConstantS.APP_KEY, ConstantS.REDIRECT_URL, ConstantS.SCOPE);
		mWebView = (WebView) findViewById(R.id.WebView01);
		
		
		Button b = (Button)this.findViewById(R.id.sina_weibo);
		b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeibo.anthorize(MainActivity.this, new AuthDialogListener());
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
