package com.philong.congthucnauan.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.adapter.bosuutap.BoSuuTapAdapter;
import com.philong.congthucnauan.adapter.bosuutapkhac.BoSuuTapKhacAdapter;
import com.philong.congthucnauan.model.bosuutapkhac.BoSuuTapKhac;
import com.philong.congthucnauan.model.congthuc.CongThuc;
import com.philong.congthucnauan.util.bosuutap.GetBoSuuTap;
import com.philong.congthucnauan.util.bosuutap.ResponseBoSuuTap;
import com.philong.congthucnauan.util.bosuutapkhac.GetBoSuuTapKhac;
import com.philong.congthucnauan.util.bosuutapkhac.ResponseBoSuuTapKhac;
import com.philong.congthucnauan.util.data.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 6/18/2017.
 */

public class FragmentBoSuuTap extends Fragment {

    //Bo suu tap
    private RecyclerView mRecyclerViewBoSuuTapNoiBat;
    private List<CongThuc> mBoSuuTapNoiBats;
    private BoSuuTapAdapter mBoSuuTapNoiBatAdapters;
    private LinearLayoutManager mLinearLayoutManager;
    //Bo suu tap khac;
    private RecyclerView mRecyclerViewBSTKhac;
    private List<BoSuuTapKhac> mBoSuuTapKhacs;
    private LinearLayoutManager mLinearLayoutManagerBSTKhac;
    private BoSuuTapKhacAdapter mBoSuuTapKhacAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bo_suu_tap, container, false);
        //Setup bo suu tap noi bat
        mBoSuuTapNoiBats = new ArrayList<>();
        mRecyclerViewBoSuuTapNoiBat = (RecyclerView) view.findViewById(R.id.recyclerViewBoSuuTapNoiBat);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewBoSuuTapNoiBat.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewBoSuuTapNoiBat.setNestedScrollingEnabled(false);
        mRecyclerViewBoSuuTapNoiBat.setHasFixedSize(true);
        mRecyclerViewBoSuuTapNoiBat.setLayoutManager(mLinearLayoutManager);
        new GetBoSuuTap(getContext(), new ResponseBoSuuTap() {
            @Override
            public void processFinish(List<CongThuc> congThucList) {
                if(congThucList != null){
                    congThucList.remove(congThucList.size() - 1);
                    mBoSuuTapNoiBats.addAll(congThucList);
                    mBoSuuTapNoiBatAdapters = new BoSuuTapAdapter(getContext(), mBoSuuTapNoiBats);
                    mRecyclerViewBoSuuTapNoiBat.setAdapter(mBoSuuTapNoiBatAdapters);
                }else{
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Data.BASE_URL_BO_SUU_TAP);


        //setup bst khac;
        mBoSuuTapKhacs = new ArrayList<>();
        mRecyclerViewBSTKhac = (RecyclerView) view.findViewById(R.id.recyclerViewBoSuuTapKhac);
        mLinearLayoutManagerBSTKhac = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewBSTKhac.setHasFixedSize(true);
        mRecyclerViewBSTKhac.setNestedScrollingEnabled(false);
        mRecyclerViewBSTKhac.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewBSTKhac.setLayoutManager(mLinearLayoutManagerBSTKhac);
        new GetBoSuuTapKhac(new ResponseBoSuuTapKhac() {
            @Override
            public void processFinish(List<BoSuuTapKhac> boSuuTapKhacs) {
                if(boSuuTapKhacs != null){
                    boSuuTapKhacs.remove(boSuuTapKhacs.size() - 1);
                    mBoSuuTapKhacs.addAll(boSuuTapKhacs);
                    mBoSuuTapKhacAdapter = new BoSuuTapKhacAdapter(getContext(), mBoSuuTapKhacs);
                    mRecyclerViewBSTKhac.setAdapter(mBoSuuTapKhacAdapter);
                }else{
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Data.BASE_URL_BO_SUU_TAP);

        //Test
        new A().execute("https://www.cooky.vn/cong-thuc/goi-muc-xoai-xanh-13129");

        return view;
    }






    //Test
    private class A extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                Document doc = Jsoup.connect(params[0]).get();
                String html = doc.select("div.ng-scope").select("ul.list-unstyled").html();
                return html;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println(s);
        }
    }
}
