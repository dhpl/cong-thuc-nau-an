package com.philong.congthucnauan.util.slide;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.philong.congthucnauan.model.congthuc.CongThuc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 6/18/2017.
 */

public class GetSlide extends AsyncTask<String, Void, List<CongThuc>> {

    private ProgressDialog mProgressDialog;
    private Context mContext;

    public ResponseSlide delegate = null;

    public GetSlide(ResponseSlide delegate, Context context) {
        this.delegate = delegate;
        mContext = context;
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
        List<CongThuc> congThucs = new ArrayList<>();
        congThucs.add(new CongThuc("Gà rang sả rắc muối", "/cong-thuc/ga-rang-sa-rac-muoi-7235", "https://media.cooky.vn/recipe/g1/7235/s320x320/recipe7235-636338149927204566.jpg"));
        congThucs.add(new CongThuc("Gà tre hấp muối hột", "/cong-thuc/ga-tre-hap-muoi-hot-8016", "https://media.cooky.vn/recipe/g1/8016/s320x320/recipe8016-636335724091899812.jpg"));
        congThucs.add(new CongThuc("Thạch đậu nành lá dứa", "/cong-thuc/thach-dau-nanh-la-dua-20363", "https://media.cooky.vn/recipe/g3/20363/s320x320/recipe20363-636335675921199204.jpg"));
        congThucs.add(new CongThuc("Súp hải sản trứng cút", "/cong-thuc/sup-hai-san-trung-cut-20362", "https://media.cooky.vn/recipe/g3/20362/s320x320/cooky-recipe-636335527849171129.jpg"));
        congThucs.add(new CongThuc("Ớt rim miền Trung", "/cong-thuc/ot-rim-mien-trung-20336", "https://media.cooky.vn/recipe/g3/20336/s320x320/recipe20336-636334817004903455.jpg"));
        congThucs.add(new CongThuc("Gỏi xoài cá trê kiểu Thái", "/cong-thuc/goi-xoai-ca-tre-kieu-thai-19150", "https://media.cooky.vn/recipe/g2/19150/s320x320/recipe19150-636328909367017006.JPG"));
        congThucs.add(new CongThuc("Trứng cút lộn xào me", "/cong-thuc/trung-cut-lon-xao-me-17996", "https://media.cooky.vn/recipe/g2/17996/s320x320/cooky-recipe-636088604576071214.jpg"));
        congThucs.add(new CongThuc("Chôm chôm xóc muối tắc", "/cong-thuc/chom-chom-xoc-muoi-tac-20205", "https://media.cooky.vn/recipe/g3/20205/s320x320/recipe20205-636319375892276474.jpg"));
        congThucs.add(new CongThuc("Thịt heo giả khô bò", "/cong-thuc/thit-heo-gia-kho-bo-20199", "https://media.cooky.vn/recipe/g3/20199/s320x320/cooky-recipe-636319110742666763.jpg"));
        return congThucs;
    }

    @Override
    protected void onPostExecute(List<CongThuc> congThucs) {
        super.onPostExecute(congThucs);
        mProgressDialog.dismiss();
        delegate.processFinish(congThucs);
    }
}
