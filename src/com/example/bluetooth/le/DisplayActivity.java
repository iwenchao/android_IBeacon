package com.example.bluetooth.le;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

@SuppressLint("SetJavaScriptEnabled")
public class DisplayActivity extends Activity{
	private WebView mWebView;
	private ProgressBar mProgressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setTitle(R.string.title_display);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_display);
		
		initUI();
		
		
	}
	private void initUI(){
		mWebView = (WebView) findViewById(R.id.display_wb);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		WebSettings mWebSettings = mWebView.getSettings();
		// 支持多点触控缩放
		mWebSettings.setSupportZoom(false);
		mWebSettings.setBuiltInZoomControls(false);
		// 使用缓冲
		mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setHorizontalScrollBarEnabled(false);
		mWebView.setVerticalScrollBarEnabled(true);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		mWebView.loadUrl(ConstantUtil.URL);
		mWebView.setWebViewClient(new WebViewClient() {    
		    
		    @Override  
		    public void onPageStarted(WebView view, String url, Bitmap favicon) {  
		        super.onPageStarted(view, url, favicon);  
		        // 开始加载网页时处理 如：显示"加载提示" 的加载对话框  
		        mProgressBar.setVisibility(View.VISIBLE);
//		        DialogManager.showLoadingDialog(this);  
		    }  
		  
		    @Override  
		    public void onPageFinished(WebView view, String url) {  
		        super.onPageFinished(view, url);  
		        // 网页加载完成时处理  如：让 加载对话框 消失  
		        mProgressBar.setVisibility(View.INVISIBLE);
//		        DialogManager.dismissLoadingDialog();  
		    }  
		  
		    @Override  
		    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {  
		        super.onReceivedError(view, errorCode, description, failingUrl);  
		        // 加载网页失败时处理  如：  
		        mProgressBar.setVisibility(View.INVISIBLE);
		        view.loadDataWithBaseURL(null,  
		                "网页加载失败",  
		                "text/html",  
		                "utf-8",  
		                null);  
		    }    
		});  
		
		
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:// 点击返回图标事件
	            this.finish();
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	
}
