package com.philong.congthucnauan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.philong.congthucnauan.R;
import com.philong.congthucnauan.activity.ChiTietCachCheBien;
import com.philong.congthucnauan.adapter.congthucdaubep.CongThucDauBepAdapter;
import com.philong.congthucnauan.adapter.meovat.MeoVatAdapter;
import com.philong.congthucnauan.model.congthuc.CongThuc;
import com.philong.congthucnauan.model.congthucdaubep.CongThucDauBep;
import com.philong.congthucnauan.util.congthucdaubep.GetCongThucDauBep;
import com.philong.congthucnauan.util.congthucdaubep.ResponseCongThucDauBep;
import com.philong.congthucnauan.util.data.Data;
import com.philong.congthucnauan.util.meovat.GetMeoVat;
import com.philong.congthucnauan.util.meovat.ResponseMeoVat;
import com.philong.congthucnauan.util.slide.GetSlide;
import com.philong.congthucnauan.util.slide.ResponseSlide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 6/18/2017.
 */

public class FragmentHome extends Fragment implements BaseSliderView.OnSliderClickListener{

    //https://www.cooky.vn/
    private SliderLayout mSliderLayout;
    private List<CongThuc> mCongThucs;
    //Cong thuc dau bep
    private List<CongThucDauBep> mCongThucDauBeps;
    private RecyclerView mRecyclerViewCongThucDauBep;
    private LinearLayoutManager mLinearLayoutManager;
    private CongThucDauBepAdapter mCongThucDauBepAdapter;
    //Meo vat
    private List<CongThuc> mMeoVats;
    private RecyclerView mRecyclerViewMeoVat;
    private GridLayoutManager mGridLayoutManager;
    private MeoVatAdapter mMeoVatAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //init
        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        //Get slide
        mCongThucs = new ArrayList<>();
        new GetSlide(new ResponseSlide() {
            @Override
            public void processFinish(List<CongThuc> congThucs) {
                if(congThucs != null){
                    mCongThucs.addAll(congThucs);
                    //Slider
                    mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    mSliderLayout.setCustomAnimation(new DescriptionAnimation());
                    mSliderLayout.setDuration(4000);
                    // initialize a SliderLayout
                    for(CongThuc congThuc : mCongThucs){
                        Bundle bundle = new Bundle();
                        bundle.putString("Link", congThuc.getLink());
                        bundle.putString("Ten", congThuc.getTen());
                        bundle.putString("Hinh", congThuc.getLinkHinh());
                        TextSliderView textSliderView = new TextSliderView(getContext());
                        textSliderView
                                .bundle(bundle)
                                .description(congThuc.getTen())
                                .image(congThuc.getLinkHinh())
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(FragmentHome.this);
                        mSliderLayout.addSlider(textSliderView);
                    }
                    System.out.println(congThucs.get(0).getTen());
                }else{
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }, getContext()).execute(Data.BASE_URL_CONG_THUC);

        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Get cong thuc dau bep
        mRecyclerViewCongThucDauBep = (RecyclerView) view.findViewById(R.id.recyclerViewCongThucDauBep);
        mCongThucDauBeps = new ArrayList<>();
        mRecyclerViewCongThucDauBep.setHasFixedSize(true);
        mRecyclerViewCongThucDauBep.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewCongThucDauBep.setNestedScrollingEnabled(false);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCongThucDauBep.setLayoutManager(mLinearLayoutManager);
        new GetCongThucDauBep(new ResponseCongThucDauBep() {
            @Override
            public void processFinish(List<CongThucDauBep> congThucDauBeps) {
               if(congThucDauBeps != null){
                   mCongThucDauBeps.addAll(congThucDauBeps);
                   System.out.println("Size: " + mCongThucDauBeps.size());
                   mCongThucDauBepAdapter = new CongThucDauBepAdapter(getContext(), mCongThucDauBeps);
                   mRecyclerViewCongThucDauBep.setAdapter(mCongThucDauBepAdapter);
               }else{
                   Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
               }
            }
        }).execute(Data.BASE_URL);

        //Get meo vat
        mMeoVats = new ArrayList<>();
        mRecyclerViewMeoVat = (RecyclerView) view.findViewById(R.id.recyclerViewMeoVat);
        mRecyclerViewMeoVat.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewMeoVat.setNestedScrollingEnabled(false);
        mRecyclerViewMeoVat.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerViewMeoVat.setLayoutManager(mGridLayoutManager);
        new GetMeoVat(new ResponseMeoVat() {
            @Override
            public void processFinish(List<CongThuc> congThucList) {
                if(congThucList != null){
                    mMeoVats.addAll(congThucList);
                    mMeoVatAdapter = new MeoVatAdapter(getContext(), mMeoVats);
                    mRecyclerViewMeoVat.setAdapter(mMeoVatAdapter);
                }else{
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Data.BASE_URL);

        return view;
    }

    @Override
    public void onResume() {
        mSliderLayout.startAutoCycle();
        super.onResume();
    }

    @Override
    public void onStop() {
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    //Click image on slider
    @Override
    public void onSliderClick(BaseSliderView slider) {
        System.out.println("Slide Link: " + slider.getBundle().getString("Link"));
        String link = slider.getBundle().getString("Link");
        String ten = slider.getBundle().getString("Ten");
        String hinh = slider.getBundle().getString("Hinh");
        Intent intent = ChiTietCachCheBien.newIntent(getContext(), link, ten, hinh);
        getContext().startActivity(intent);
    }
}