package com.philong.congthucnauan.model.congthucdaubep;

import com.philong.congthucnauan.model.congthuc.CongThuc;

/**
 * Created by Long on 6/18/2017.
 */

public class CongThucDauBep extends CongThuc {

    private String mTenDauBep;
    private String mLinkAvatar;
    private String mTomTat;
    private String[] mLinkHinhLienQuan;


    public CongThucDauBep(String ten, String link, String linkHinh) {
        super(ten, link, linkHinh);
    }

    public CongThucDauBep(String ten, String link, String linkHinh, String tenDauBep, String linkAvatar, String tomTat, String[] linkHinhLienQuan) {
        super(ten, link, linkHinh);
        mTenDauBep = tenDauBep;
        mLinkAvatar = linkAvatar;
        mTomTat = tomTat;
        mLinkHinhLienQuan = linkHinhLienQuan;
    }

    public String getTenDauBep() {
        return mTenDauBep;
    }

    public void setTenDauBep(String tenDauBep) {
        mTenDauBep = tenDauBep;
    }

    public String getLinkAvatar() {
        return mLinkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        mLinkAvatar = linkAvatar;
    }

    public String getTomTat() {
        return mTomTat;
    }

    public void setTomTat(String tomTat) {
        mTomTat = tomTat;
    }

    public String[] getLinkHinhLienQuan() {
        return mLinkHinhLienQuan;
    }

    public void setLinkHinhLienQuan(String[] linkHinhLienQuan) {
        mLinkHinhLienQuan = linkHinhLienQuan;
    }
}
