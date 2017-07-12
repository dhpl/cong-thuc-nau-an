package com.philong.congthucnauan.util.dansachbst;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.philong.congthucnauan.model.danhsachbst.DanhSachBST;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Long on 6/20/2017.
 */

public class GetDanhSachBST extends AsyncTask<String, Void, List<DanhSachBST>> {

    private ProgressDialog mProgressDialog;
    private Context mContext;
    public ResponseDanhSachBST delegate = null;

    public GetDanhSachBST(Context context, ResponseDanhSachBST delegate) {
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
    protected List<DanhSachBST> doInBackground(String... params) {
        try {
            List<DanhSachBST> danhSachBSTs = new ArrayList<>();
            Document doc = Jsoup.connect(params[0]).get();
            Elements elements = doc.select("div.container").select("div.crlist,.row10").select("div.crcontainer,.ng-scope");
            for(Element element : elements){
                String link = element.select("div.collection-recipe-item").select("div.collection-recipe-photo").select("a").attr("href");
                String linkHinh =  element.select("div.collection-recipe-item").select("div.collection-recipe-photo").select("a").select("img.img-responsive").attr("src");
                String ten = element.select("div.collection-recipe-item").select("div.collection-recipe-header-box").select("h3").select("a").text();
                int yeuThich = new Random().nextInt(9999);
                int diem = new Random().nextInt(10);
                danhSachBSTs.add(new DanhSachBST(ten, link, linkHinh, String.valueOf(diem), String.valueOf(yeuThich)));
            }
            return danhSachBSTs;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<DanhSachBST> danhSachBSTs) {
        super.onPostExecute(danhSachBSTs);
        mProgressDialog.dismiss();
        delegate.processFinish(danhSachBSTs);

    }



}
