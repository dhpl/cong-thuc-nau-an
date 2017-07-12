package com.philong.congthucnauan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.Toast;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.util.chitietmeovat.GetChiTietMeoVat;
import com.philong.congthucnauan.util.chitietmeovat.ResponseChiTietMeoVat;
import com.philong.congthucnauan.util.data.Data;

public class ChiTietMeoVat extends AppCompatActivity {


    private Toolbar mToolbar;
    private WebView mWebView;

    public static Intent newIntent(Context context, String link, String ten){
        Intent intent = new Intent(context, ChiTietMeoVat.class);
        intent.putExtra("LinkCT", link);
        intent.putExtra("TenCT", ten);
        return intent;
    }

    public String getLink(){
        if(getIntent() != null){
            return getIntent().getStringExtra("LinkCT");
        }
        return "";
    }

    public String getTen(){
        if(getIntent() != null){
            return getIntent().getStringExtra("TenCT");
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_meo_vat);
        //setup toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() == null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(getTen());
        }
        //set webview;
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        String[] links = getLink().split("\\?");
        new GetChiTietMeoVat(this, new ResponseChiTietMeoVat() {
            @Override
            public void processFinish(String s) {
                if(s != null){
                    mWebView.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + s, "text/html", "UTF-8", null);
                }else{
                    Toast.makeText(ChiTietMeoVat.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Data.BASE_URL + links[0]);
    }
}
