package com.philong.congthucnauan.util.chitietmeovat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Long on 6/20/2017.
 */

public class GetChiTietMeoVat extends AsyncTask<String, Void, String> {

    private ProgressDialog mProgressDialog;
    private Context mContext;
    public ResponseChiTietMeoVat delegate = null;

    public GetChiTietMeoVat(Context context, ResponseChiTietMeoVat delegate) {
        mContext = context;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Đang tải vui lòng chờ");
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Document document = Jsoup.connect(params[0]).get();
            String s = document.select("article.article-content").html();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressDialog.dismiss();
        delegate.processFinish(s);
    }
}
