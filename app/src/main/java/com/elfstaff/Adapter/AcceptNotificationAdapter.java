package com.elfstaff.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elfstaff.R;
import com.elfstaff.model.Student;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 5/8/16.
 */
public class AcceptNotificationAdapter extends RecyclerView.Adapter<AcceptNotificationAdapter.AcceptHolder> {


    public List<Student> mList;
    private Context mContext;

    public AcceptNotificationAdapter(Context context, List<Student> students) {
        this.mContext=context;
        mList=students;
    }

    @Override
    public AcceptHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.accpet_item_box,parent,false);
        return new AcceptHolder(v);
    }

    @Override
    public void onBindViewHolder(AcceptHolder holder, int position) {

//        IF student has been accpeted disply another icon
//        ddispkay antoher icon
        holder.mText.setText(mList.get(position).getStudentname()+" is you student");



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class AcceptHolder extends RecyclerView.ViewHolder{
       public @BindView(R.id.accept_text) TextView mText;
        public  @BindView(R.id.accept_icon)
        ImageView mIcon;
        public AcceptHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
