package com.philong.congthucnauan.util.meovat;

import com.philong.congthucnauan.model.congthuc.CongThuc;

import java.util.List;

/**
 * Created by Long on 6/19/2017.
 */

public interface ResponseMeoVat {

    void processFinish(List<CongThuc> congThucList);

}
