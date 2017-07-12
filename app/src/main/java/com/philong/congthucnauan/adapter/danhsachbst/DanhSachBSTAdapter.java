package com.philong.congthucnauan.adapter.danhsachbst;

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
import com.philong.congthucnauan.activity.ChiTietCachCheBien;
import com.philong.congthucnauan.model.danhsachbst.DanhSachBST;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Long on 6/20/2017.
 */

public class DanhSachBSTAdapter extends RecyclerView.Adapter<DanhSachBSTAdapter.DanhSachBSTVIiewHolder>{

    private Context mContext;
    private List<DanhSachBST> mDanhSachBSTList;

    public DanhSachBSTAdapter(Context context, List<DanhSachBST> danhSachBSTList) {
        mContext = context;
        mDanhSachBSTList = danhSachBSTList;
    }

    @Override
    public DanhSachBSTVIiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cong_thuc, parent, false);
        return new DanhSachBSTVIiewHolder(view);
    }

    @Override
    public void onBindViewHolder(DanhSachBSTVIiewHolder holder, int position) {
        final DanhSachBST danhSachBST = mDanhSachBSTList.get(position);
        holder.txtDiemBST.setText(danhSachBST.getDiem());
        holder.txtYeuThichBST.setText(danhSachBST.getYeuThich());
        holder.txtTenBSt.setText(danhSachBST.getTen());
        Picasso.with(mContext).load(danhSachBST.getLinkHinh()).error(R.drawable.placeholder).into(holder.imgCongThucBST);
        holder.cardViewDanSachBST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ChiTietCachCheBien.newIntent(mContext, danhSachBST.getLink(), danhSachBST.getTen(), danhSachBST.getLinkHinh());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDanhSachBSTList.size();
    }

    public static class DanhSachBSTVIiewHolder extends RecyclerView.ViewHolder{

        private TextView txtTenBSt;
        private ImageView imgCongThucBST;
        private TextView txtYeuThichBST;
        private TextView txtDiemBST;
        private CardView cardViewDanSachBST;

        public DanhSachBSTVIiewHolder(View itemView) {
            super(itemView);
            txtTenBSt = (TextView) itemView.findViewById(R.id.txtTenCongThucBST);
            imgCongThucBST = (ImageView) itemView.findViewById(R.id.imgCongThucBST);
            txtYeuThichBST = (TextView) itemView.findViewById(R.id.txtYeuThichBST);
            txtDiemBST = (TextView) itemView.findViewById(R.id.txtDiemBST);
            cardViewDanSachBST = (CardView) itemView.findViewById(R.id.cardViewDanhSachBST);
        }
    }

}
