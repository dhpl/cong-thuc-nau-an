package com.philong.congthucnauan.adapter.bosuutap;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.activity.DanhSachChiTietBoSuuTap;
import com.philong.congthucnauan.model.congthuc.CongThuc;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Long on 6/20/2017.
 */

public class BoSuuTapAdapter extends RecyclerView.Adapter<BoSuuTapAdapter.BoSuuTapViewHolder>{

    private Context mContext;
    private List<CongThuc> mCongThucList;

    public BoSuuTapAdapter(Context context, List<CongThuc> congThucList) {
        mContext = context;
        mCongThucList = congThucList;
    }

    @Override
    public BoSuuTapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bo_suu_tap, parent, false);
        return new BoSuuTapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoSuuTapViewHolder holder, int position) {
        final CongThuc congThuc = mCongThucList.get(position);
        holder.txtTenBoSuuTap.setText(congThuc.getTen());
        Picasso.with(mContext).load(congThuc.getLinkHinh()).error(R.drawable.placeholder).into(holder.imgBoSuuTap);
        holder.cardViewBoSuuTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DanhSachChiTietBoSuuTap.newIntent(mContext, congThuc.getLink(), congThuc.getTen());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCongThucList.size();
    }

    public static class BoSuuTapViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgBoSuuTap;
        private TextView txtTenBoSuuTap;
        private CardView cardViewBoSuuTap;

        public BoSuuTapViewHolder(View itemView) {
            super(itemView);
            imgBoSuuTap = (ImageView) itemView.findViewById(R.id.imgBoSuuTap);
            txtTenBoSuuTap = (TextView) itemView.findViewById(R.id.txtTenBoSuuTap);
            cardViewBoSuuTap = (CardView) itemView.findViewById(R.id.cardViewBoSuuTap);
        }
    }

}
