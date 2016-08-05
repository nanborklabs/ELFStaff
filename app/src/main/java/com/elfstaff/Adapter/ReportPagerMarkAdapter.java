package com.elfstaff.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elfstaff.R;
import com.elfstaff.model.ReportModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 5/8/16.
 */
public class ReportPagerMarkAdapter extends RecyclerView.Adapter<ReportPagerMarkAdapter.ReportHolder> {

    private List<ReportModel> mList;
    private Context mContext;
    LayoutInflater inflater;
    public ReportPagerMarkAdapter(Context mcContext, List<ReportModel> mlist) {
        inflater=LayoutInflater.from(mcContext);
        this.mContext=mcContext;
        this.mList=mlist;
    }

    @Override
    public ReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= inflater.inflate(R.layout.report_item_box,parent,false);
        return new ReportHolder(v);
    }

    @Override
    public void onBindViewHolder(ReportHolder holder, int position) {

        holder.category_text.setText(mList.get(position).getCategory());
        holder.nos_stud.setText(mList.get(position).getNosStudetns());
        holder.growth_text.setText(mList.get(position).getGrowthPercent());
        if (mList.get(position).isIcon()==true){
            //growth has been attained
            holder.growthiconl.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_menu_camera));
    }
        else {
            holder.growthiconl.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_menu_gallery));
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ReportHolder extends RecyclerView.ViewHolder{

        public @BindView(R.id.category_text) TextView category_text;
        public @BindView(R.id.nos_students) TextView nos_stud;
        public @BindView(R.id.growth_percent) TextView growth_text;
        public @BindView(R.id.growth_icon)
        ImageView growthiconl;

        public ReportHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
