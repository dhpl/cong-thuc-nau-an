package com.philong.congthucnauan.util.congthucdaubep;

import android.os.AsyncTask;

import com.philong.congthucnauan.model.congthucdaubep.CongThucDauBep;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 6/18/2017.
 */

public class GetCongThucDauBep extends AsyncTask<String, Void, List<CongThucDauBep>> {

    public ResponseCongThucDauBep delegate = null;

    public GetCongThucDauBep(ResponseCongThucDauBep delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<CongThucDauBep> doInBackground(String... params) {
        try {
            List<CongThucDauBep> congThucDauBeps = new ArrayList<>();
            Document doc = Jsoup.connect(params[0]).get();
            Elements elements = doc.select("div.top-recipes-user").select("div.today-recipe-user");
            for(Element element : elements){
                String linkUser = element.select("div.item-block,.recipe-block").select("div.item-header").select("div.hprofile").select("div.avt").select("a").attr("href");
                String linkAvatar = element.select("div.item-block,.recipe-block").select("div.item-header").select("div.hprofile").select("div.avt").select("a").select("img").attr("src");
                String tenUser = element.select("div.item-block,.recipe-block").select("div.item-header").select("div.profile").select("a.name").text();
                String link = element.select("div.item-content").select("div.featured-recipe-item").select("div.recipe-photo").select("a").attr("href");
                String linkHinh =  element.select("div.item-content").select("div.featured-recipe-item").select("div.recipe-photo").select("a").select("img").attr("src");
                String ten = element.select("div.item-content").select("div.featured-recipe-item").select("div.recipe-photo").select("a").select("img").attr("alt");
                String tomTat = element.select("div.item-content").select("div.item-info-box").select("div.desc").text();
                Elements elements1 = element.select("div.item-content").select("div.recipe-photo-thumb-list").select("div.photo-thumb");
                String linkHinhLienQuan[] = new String[elements1.size()];
                for (int i = 0; i < elements1.size(); i++) {
                    linkHinhLienQuan[i] = elements1.get(i).select("a").select("img").attr("src");
                }
                congThucDauBeps.add(new CongThucDauBep(ten, link, linkHinh, tenUser, linkAvatar, tomTat, linkHinhLienQuan));
            }
            return congThucDauBeps;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<CongThucDauBep> congThucDauBeps) {
        super.onPostExecute(congThucDauBeps);
        delegate.processFinish(congThucDauBeps);
    }

//    String html = doc.html();
//
//    String regexHinhUser = "https://media.cooky.vn/usr/.*/c50x50/.*(.jpg|.png)"; // complete
//    Pattern pattern = Pattern.compile(regexHinhUser);
//    Matcher matcher = pattern.matcher(html);
//    //
//    String  elements = doc.select("div.recently-item-overlay-box").last().select("div").html();
//
//
//            while(matcher.find()){
//        congThucDauBeps.add(new CongThucDauBep("", "", "", elements,"", "", null));
//    }
}
