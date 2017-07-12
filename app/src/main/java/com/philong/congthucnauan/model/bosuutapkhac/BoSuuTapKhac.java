package com.philong.congthucnauan.model.bosuutapkhac;

import com.philong.congthucnauan.model.congthuc.CongThuc;

/**
 * Created by Long on 6/20/2017.
 */

public class BoSuuTapKhac extends CongThuc{

    private String mSize;

    public BoSuuTapKhac(String ten, String link, String linkHinh, String size) {
        super(ten, link, linkHinh);
        mSize = size;
    }

    public BoSuuTapKhac(String ten, String link, String linkHinh) {
        super(ten, link, linkHinh);
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
    }
}