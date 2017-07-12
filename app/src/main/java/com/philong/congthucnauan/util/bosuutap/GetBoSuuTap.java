package com.philong.congthucnauan.util.bosuutap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.philong.congthucnauan.model.congthuc.CongThuc;
import com.philong.congthucnauan.util.data.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 6/20/2017.
 */

public class GetBoSuuTap extends AsyncTask<String, Void, List<CongThuc>> {

    private ProgressDialog mProgressDialog;
    private Context mContext;
    public ResponseBoSuuTap delegate = null;

    public GetBoSuuTap(Context context, ResponseBoSuuTap delegate) {
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
    protected List<CongThuc> doInBackground(String... params) {
        try {
            List<CongThuc> congThucs = new ArrayList<>();
            Document doc = Jsoup.connect(Data.BASE_URL_BO_SUU_TAP).get();
            Elements elements = doc.select("div.featured-collection-container,.row,.clearfix").select("div.col-md-4,.col-sm-4,.col-xs-4,.featured-collection");
            for(Element element : elements){
                String linkBoSuuTap = element.select("div.collection-featured").select("a").attr("href");
                String linkHinhBoSuuTap = element.select("div.collection-featured").select("a").select("img").attr("src");
                String tenBoSuuTap = element.select("div.collection-featured").select("a").select("img").attr("alt");
                congThucs.add(new CongThuc(tenBoSuuTap, linkBoSuuTap, linkHinhBoSuuTap));
            }
            return congThucs;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(List<CongThuc> congThucs) {
        super.onPostExecute(congThucs);
        mProgressDialog.dismiss();
        delegate.processFinish(congThucs);
    }
}
