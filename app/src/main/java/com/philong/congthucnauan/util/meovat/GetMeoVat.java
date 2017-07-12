package com.philong.congthucnauan.util.meovat;

import android.os.AsyncTask;

import com.philong.congthucnauan.model.congthuc.CongThuc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 6/19/2017.
 */

public class GetMeoVat extends AsyncTask<String, Void, List<CongThuc>>{

    public ResponseMeoVat delegate = null;

    public GetMeoVat(ResponseMeoVat delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<CongThuc> doInBackground(String... params) {
        try {
            List<CongThuc> congThucs = new ArrayList<>();
            Document doc = Jsoup.connect(params[0]).get();
            Elements elements = doc.select("div.wide-box,.wide-box-white,.home-top-box").select("div.container").select("div.blog-home,.row row10").select("div.item");
            for(Element element : elements){
                String linkMeoVat = element.select("article").select("a").attr("href");
                String linkHinhMeoVat = element.select("article").select("a").select("div.item-img-effect").select("img.img-responsice,.effect").attr("src");
                String tenMeoVat = element.select("article").select("a").select("div.item-img-effect").select("img.img-responsice,.effect").attr("alt");
                congThucs.add(new CongThuc(tenMeoVat, linkMeoVat, linkHinhMeoVat));
            }
            return congThucs;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<CongThuc> s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }
}
