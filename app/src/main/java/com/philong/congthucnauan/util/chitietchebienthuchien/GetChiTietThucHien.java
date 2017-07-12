package com.philong.congthucnauan.util.chitietchebienthuchien;

import android.os.AsyncTask;

import com.philong.congthucnauan.model.chitiet.ChiTiet;

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

public class GetChiTietThucHien extends AsyncTask<String, Void, List<ChiTiet>> {

    public ResponseChiTietThucHien delegate = null;

    public GetChiTietThucHien(ResponseChiTietThucHien delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<ChiTiet> doInBackground(String... params) {
        try {
            List<ChiTiet> chiTiets = new ArrayList<>();
            String[] linkHinhLienQuans;
            Document doc = Jsoup.connect(params[0]).get();
            Elements elements = doc.select("div#accordionDirection").select("div.panel,.panel-default");
            for(Element element : elements){
                Elements elements1 = element.select("div.panel-collapse,.collapse,.in").select("div.panel-body,.has-photo").select("div.step-photos").select("a");
                linkHinhLienQuans = new String[elements1.size()];
                for (int i = 0; i < elements1.size(); i++) {
                    linkHinhLienQuans[i] = elements1.get(i).select("img").attr("src");
                }
                String cachThucHien = element.select("div.panel-collapse,.collapse,.in").select("div.panel-body,.has-photo").select("div.step-desc,.instructions").text();
                chiTiets.add(new ChiTiet(cachThucHien, linkHinhLienQuans));
            }
            return chiTiets;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<ChiTiet> chiTiets) {
        super.onPostExecute(chiTiets);
        delegate.processFinish(chiTiets);
    }
}
