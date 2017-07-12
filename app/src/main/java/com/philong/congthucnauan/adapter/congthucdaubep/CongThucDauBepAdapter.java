package com.philong.congthucnauan.adapter.congthucdaubep;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.activity.ChiTietCachCheBien;
import com.philong.congthucnauan.adapter.hinhanhlienquan.HinhLienQuanAdaper;
import com.philong.congthucnauan.model.congthucdaubep.CongThucDauBep;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Long on 6/18/2017.
 */

public class CongThucDauBepAdapter extends RecyclerView.Adapter<CongThucDauBepAdapter.CongThucDauBepViewHolder>{

    private Context mContext;
    private List<CongThucDauBep> mCongThucDauBepList;

    public CongThucDauBepAdapter(Context context, List<CongThucDauBep> congThucDauBepList) {
        mContext = context;
        mCongThucDauBepList = congThucDauBepList;
    }

    @Override
    public CongThucDauBepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cong_thuc_tu_dau_bep, parent, false);
        return new CongThucDauBepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CongThucDauBepViewHolder holder, int position) {
        final CongThucDauBep congThucDauBep = mCongThucDauBepList.get(position);
        holder.txtTenDauBep.setText(congThucDauBep.getTenDauBep());
        holder.txtTenCongThuc.setText(congThucDauBep.getTen());
        holder.txtTomTat.setText(congThucDauBep.getTomTat());
        Picasso.with(mContext).load(congThucDauBep.getLinkAvatar()).error(R.drawable.placeholder).into(holder.profileImage);
        Picasso.with(mContext).load(congThucDauBep.getLinkHinh()).error(R.drawable.placeholder).into(holder.imgHinh);
        //Setup recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerViewHinhLienQuanCongThuc.setHasFixedSize(true);
        holder.recyclerViewHinhLienQuanCongThuc.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerViewHinhLienQuanCongThuc.setNestedScrollingEnabled(false);
        holder.recyclerViewHinhLienQuanCongThuc.setLayoutManager(linearLayoutManager);
        HinhLienQuanAdaper hinhLienQuanAdaper = new HinhLienQuanAdaper(mContext, congThucDauBep.getLinkHinhLienQuan());
        holder.recyclerViewHinhLienQuanCongThuc.setAdapter(hinhLienQuanAdaper);
        holder.cardViewCongThucTuDauBep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ChiTietCachCheBien.newIntent(mContext, congThucDauBep.getLink(), congThucDauBep.getTen(), congThucDauBep.getLinkHinh());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCongThucDauBepList.size();
    }

    public static class CongThucDauBepViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayoutCongThucDauBep;
        private CircleImageView profileImage;
        private TextView txtTenDauBep;
        private TextView txtTenCongThuc;
        private TextView txtTomTat;
        private ImageView imgHinh;
        private RecyclerView recyclerViewHinhLienQuanCongThuc;
        private CardView cardViewCongThucTuDauBep;

        public CongThucDauBepViewHolder(View itemView) {
            super(itemView);
            linearLayoutCongThucDauBep = (LinearLayout) itemView.findViewById(R.id.linearLayoutCongThucDauBep);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
            txtTenDauBep = (TextView) itemView.findViewById(R.id.txtTenDauBep);
            txtTenCongThuc = (TextView) itemView.findViewById(R.id.txtTenCongThuc);
            txtTomTat = (TextView) itemView.findViewById(R.id.txtTomTat);
            imgHinh = (ImageView) itemView.findViewById(R.id.imgHinh);
            recyclerViewHinhLienQuanCongThuc = (RecyclerView) itemView.findViewById(R.id.recyclerViewHinh);
            cardViewCongThucTuDauBep = (CardView) itemView.findViewById(R.id.cardViewCongThucTuDauBep);
        }
    }

}
