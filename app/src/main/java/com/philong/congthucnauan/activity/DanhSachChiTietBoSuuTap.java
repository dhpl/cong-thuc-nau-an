package com.philong.congthucnauan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.adapter.danhsachbst.DanhSachBSTAdapter;
import com.philong.congthucnauan.model.danhsachbst.DanhSachBST;
import com.philong.congthucnauan.util.dansachbst.GetDanhSachBST;
import com.philong.congthucnauan.util.dansachbst.ResponseDanhSachBST;
import com.philong.congthucnauan.util.data.Data;

import java.util.ArrayList;
import java.util.List;

public class DanhSachChiTietBoSuuTap extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerViewDanhSachBST;
    private LinearLayoutManager mLinearLayoutManager;
    private List<DanhSachBST> mDanhSachBSTs;
    private DanhSachBSTAdapter mDanhSachBSTAdapter;



    public static Intent newIntent(Context context, String link, String ten){
        Intent intent = new Intent(context, DanhSachChiTietBoSuuTap.class);
        intent.putExtra("LinkBST", link);
        intent.putExtra("TenBST", ten);
        return intent;
    }

    public String getLinkBST(){
        if(getIntent() != null){
            return getIntent().getStringExtra("LinkBST");
        }
        return "";
    }

    private String getTenBST(){
        if(getIntent() != null){
            return getIntent().getStringExtra("TenBST");
        }
        return "";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chi_tiet_bo_suu_tap);
        //setup toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() == null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(getTenBST());
        }
        //setup recyclerview
        mDanhSachBSTs = new ArrayList<>();
        mRecyclerViewDanhSachBST = (RecyclerView) findViewById(R.id.recyclerViewDanhSachChiTietboSuuTap);
        mRecyclerViewDanhSachBST.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewDanhSachBST.setHasFixedSize(true);
        mRecyclerViewDanhSachBST.setNestedScrollingEnabled(false);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewDanhSachBST.setLayoutManager(mLinearLayoutManager);
        new GetDanhSachBST(this, new ResponseDanhSachBST() {
            @Override
            public void processFinish(List<DanhSachBST> danhSachBSTs) {
                if(danhSachBSTs != null){
                    danhSachBSTs.remove(danhSachBSTs.size() - 1);
                    mDanhSachBSTs.addAll(danhSachBSTs);
                    mDanhSachBSTAdapter = new DanhSachBSTAdapter(DanhSachChiTietBoSuuTap.this, mDanhSachBSTs);
                    mRecyclerViewDanhSachBST.setAdapter(mDanhSachBSTAdapter);
                }else{
                    Toast.makeText(DanhSachChiTietBoSuuTap.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Data.BASE_URL + getLinkBST());
    }
}
