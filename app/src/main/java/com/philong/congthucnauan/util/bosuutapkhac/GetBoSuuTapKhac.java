package com.philong.congthucnauan.util.bosuutapkhac;

import android.os.AsyncTask;

import com.philong.congthucnauan.model.bosuutapkhac.BoSuuTapKhac;

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

public class GetBoSuuTapKhac extends AsyncTask<String, Void, List<BoSuuTapKhac>>{

    public ResponseBoSuuTapKhac delegate = null;

    public GetBoSuuTapKhac(ResponseBoSuuTapKhac delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<BoSuuTapKhac> doInBackground(String... params) {
        try {
            List<BoSuuTapKhac> boSuuTapKhacs = new ArrayList<>();
            Document doc = Jsoup.connect(params[0]).get();
            Elements elements = doc.select("div.collection-list,.row10").select("div.collection-item,.ng-scope");
            for(Element element : elements){
                String linkBST = element.select("div.collection-item-card").select("div.collection-head").select("div.collection-header-box")
                        .select("h3.title").select("a").attr("href");
                String tenBST = element.select("div.collection-item-card").select("div.collection-head").select("div.collection-header-box")
                        .select("h3.title").select("a").text();
                String linkHinh = element.select("div.collection-item-card").select("div.collection-head").select("div.collection-photo").select("a").select("img.img-responsive").attr("src");
                String size = element.select("div.collection-item-card").select("div.collection-head").select("div.collection-photo-thumbs").select("a.photo,.ng-scope").select("span.collection-photo-more").text();
                boSuuTapKhacs.add(new BoSuuTapKhac(tenBST, linkBST, linkHinh, size));
            }
            return boSuuTapKhacs;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<BoSuuTapKhac> boSuuTapKhacs) {
        super.onPostExecute(boSuuTapKhacs);
        delegate.processFinish(boSuuTapKhacs);
    }
}
