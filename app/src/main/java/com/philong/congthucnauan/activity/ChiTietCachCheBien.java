package com.philong.congthucnauan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.adapter.chitietchebien.ChiTietCheBienAdapter;
import com.philong.congthucnauan.model.chitiet.ChiTiet;
import com.philong.congthucnauan.util.chitietchebienchuanbi.GetChiTietChuanBi;
import com.philong.congthucnauan.util.chitietchebienchuanbi.ResponseChitietChuanBi;
import com.philong.congthucnauan.util.chitietchebienthuchien.GetChiTietThucHien;
import com.philong.congthucnauan.util.chitietchebienthuchien.ResponseChiTietThucHien;
import com.philong.congthucnauan.util.data.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChiTietCachCheBien extends AppCompatActivity {


    //Init view
    private ImageView imgHinh;
    private TextView txtThucHien;
    private TextView txtPhan;
    private TextView txtDoKho;

    //toolbar
    private Toolbar mToolbar;
    //recylerview chuan bi
    private RecyclerView mRecyclerViewChuanBi;
    private LinearLayoutManager mLinearLayoutManagerChuanBi;
    private List<ChiTiet> mChiTietChuanBis;
    private ChiTietCheBienAdapter mChiTietCheBienChuanBiAdapter;
    //recyclerview thuc hien
    private RecyclerView mRecyclerViewThucHien;
    private LinearLayoutManager mLinearLayoutManagerThucHien;
    private List<ChiTiet> mChiTietThucHiens;
    private ChiTietCheBienAdapter mChiTietCheBienThucHienAdapter;


    public static Intent newIntent(Context context, String link, String ten, String linkHinh){
        Intent intent = new Intent(context, ChiTietCachCheBien.class);
        intent.putExtra("LinkCTCB", link);
        intent.putExtra("Ten", ten);
        intent.putExtra("LinkHinh", linkHinh);
        return intent;
    }

    public String getLink(){
        if(getIntent() != null){
            return getIntent().getStringExtra("LinkCTCB");
        }
        return "";
    }

    public String getTen(){
        if(getIntent() != null){
            return getIntent().getStringExtra("Ten");
        }
        return "";
    }

    public String getLinkHinh(){
        if(getIntent() != null){
            return getIntent().getStringExtra("LinkHinh");
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_cach_che_bien);
        //init view
        imgHinh = (ImageView) findViewById(R.id.imgChiTietCachCheBien);
        txtThucHien = (TextView) findViewById(R.id.txtThucHien);
        txtPhan = (TextView) findViewById(R.id.txtPhan);
        txtDoKho = (TextView) findViewById(R.id.txtDoKho);
        txtThucHien.setText(String.valueOf(new Random().nextInt(180)) + " m");
        txtPhan.setText(String.valueOf(new Random().nextInt(10)) + " người");
        String doKho;
        switch(new Random().nextInt(2)){
            case 0:
                doKho = "Khó";
                break;
            case 1:
                doKho = "Dễ";
                break;
            case 2:
                doKho = "Trung bình";
                break;
            default:
                doKho = "Trung bình";
        }
        txtDoKho.setText("Độ khó: " + doKho);
        Picasso.with(this).load(getLinkHinh()).error(R.drawable.placeholder).into(imgHinh);
        //setup toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() == null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(getTen());

        }
        //setup recyclerview chuan bi
        String[] link = getLink().split("\\?");
        mChiTietChuanBis = new ArrayList<>();
        mRecyclerViewChuanBi = (RecyclerView) findViewById(R.id.recyclerViewChuanBi);
        mLinearLayoutManagerChuanBi = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewChuanBi.setHasFixedSize(true);
        mRecyclerViewChuanBi.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewChuanBi.setNestedScrollingEnabled(false);
        mRecyclerViewChuanBi.setLayoutManager(mLinearLayoutManagerChuanBi);
        new GetChiTietChuanBi(this, new ResponseChitietChuanBi() {
            @Override
            public void processFinish(List<ChiTiet> chiTiets) {
                if(chiTiets != null){
                    mChiTietChuanBis.addAll(chiTiets);
                    mChiTietCheBienChuanBiAdapter = new ChiTietCheBienAdapter(ChiTietCachCheBien.this, mChiTietChuanBis);
                    mRecyclerViewChuanBi.setAdapter(mChiTietCheBienChuanBiAdapter);
                }else{
                    Toast.makeText(ChiTietCachCheBien.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Data.BASE_URL + link[0]);
        //setup recyclerview thuc hien
        mChiTietThucHiens = new ArrayList<>();
        mRecyclerViewThucHien = (RecyclerView) findViewById(R.id.recyclerViewThucHien);
        mLinearLayoutManagerThucHien = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewThucHien.setHasFixedSize(true);
        mRecyclerViewChuanBi.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewThucHien.setNestedScrollingEnabled(false);
        mRecyclerViewThucHien.setLayoutManager(mLinearLayoutManagerThucHien);
        new GetChiTietThucHien(new ResponseChiTietThucHien() {
            @Override
            public void processFinish(List<ChiTiet> chiTietList) {
                if(chiTietList != null){
                    mChiTietThucHiens.addAll(chiTietList);
                    mChiTietCheBienThucHienAdapter = new ChiTietCheBienAdapter(ChiTietCachCheBien.this, mChiTietThucHiens);
                    mRecyclerViewThucHien.setAdapter(mChiTietCheBienThucHienAdapter);
                }else{
                    Toast.makeText(ChiTietCachCheBien.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Data.BASE_URL + link[0]);
    }
}
