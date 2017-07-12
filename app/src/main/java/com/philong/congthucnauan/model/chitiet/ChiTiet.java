package com.philong.congthucnauan.model.chitiet;

/**
 * Created by Long on 6/20/2017.
 */

public class ChiTiet {

    private String mCachThucHien;
    private String[] mLinkHinhs;

    public ChiTiet(String cachThucHien, String[] linkHinhs) {
        mCachThucHien = cachThucHien;
        mLinkHinhs = linkHinhs;
    }

    public String getCachThucHien() {
        return mCachThucHien;
    }

    public void setCachThucHien(String cachThucHien) {
        mCachThucHien = cachThucHien;
    }

    public String[] getLinkHinhs() {
        return mLinkHinhs;
    }

    public void setLinkHinhs(String[] linkHinhs) {
        mLinkHinhs = linkHinhs;
    }
}
