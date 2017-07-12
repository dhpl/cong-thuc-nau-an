package com.philong.congthucnauan.model.danhsachbst;

import com.philong.congthucnauan.model.congthuc.CongThuc;

/**
 * Created by Long on 6/19/2017.
 */

public class DanhSachBST extends CongThuc {

    private String mDiem;
    private String mYeuThich;

    public DanhSachBST(String ten, String link, String linkHinh) {
        super(ten, link, linkHinh);
    }

    public DanhSachBST(String ten, String link, String linkHinh, String diem, String yeuThich) {
        super(ten, link, linkHinh);
        mDiem = diem;
        mYeuThich = yeuThich;
    }

    public String getDiem() {
        return mDiem;
    }

    public void setDiem(String diem) {
        mDiem = diem;
    }

    public String getYeuThich() {
        return mYeuThich;
    }

    public void setYeuThich(String yeuThich) {
        mYeuThich = yeuThich;
    }
}
