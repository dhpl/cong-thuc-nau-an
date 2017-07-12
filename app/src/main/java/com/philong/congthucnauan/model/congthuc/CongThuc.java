package com.philong.congthucnauan.model.congthuc;

/**
 * Created by Long on 6/18/2017.
 */

public class CongThuc {

    protected String mTen;
    protected String mLink;
    protected String mLinkHinh;

    public CongThuc(String ten, String link, String linkHinh) {
        mTen = ten;
        mLink = link;
        mLinkHinh = linkHinh;
    }

    public String getTen() {
        return mTen;
    }

    public void setTen(String ten) {
        mTen = ten;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getLinkHinh() {
        return mLinkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        mLinkHinh = linkHinh;
    }
}
