package com.elfstaff.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        Log.d("Adapter", "onBindViewHolder: ");
        holder.category_text.setText(""+mList.get(position).getCategory());
        holder.nos_stud.setText(""+mList.get(position).getNosStudetns());
        holder.growth_text.setText(""+mList.get(position).getGrowthPercent());
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
        return mList.size();
    }

    public static class ReportHolder extends RecyclerView.ViewHolder{

        public TextView category_text;
        public  TextView nos_stud;
        public TextView growth_text;
        public  ImageView growthiconl;

        public ReportHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            category_text=(TextView)itemView.findViewById(R.id.category_text);
            nos_stud=(TextView)itemView.findViewById(R.id.nos_students_item);
            growth_text=(TextView)itemView.findViewById(R.id.growth_percent);
            growthiconl=(ImageView) itemView.findViewById(R.id.growth_icon);


        }
    }
}
