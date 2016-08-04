package com.elfstaff.Fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.elfstaff.R;
import com.elfstaff.Utils;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class HomeFragment extends Fragment {
    private  View mView;

    @BindView(R.id.home_rank_no)
    TextView topRankNo;
    @BindView(R.id.first_card) CardView first_card;
    @BindView(R.id.second_card) CardView second_card;
    @BindView(R.id.third_card) CardView third_card;

    @BindView(R.id.home_user_name_top_name) TextView topRankUserName;
    @BindView(R.id.home_bottom_rank) TextView bottomRankNo;
    @BindView(R.id.bottom_rank_user_name) TextView bottomRankUserName;

    @BindView(R.id.std_spinner)
    Spinner std_spinner;
    @BindView(R.id.subject_spinner) Spinner sub_spinner;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return super.getLayoutInflater(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("home", "onCreate: ");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.home_frag,container,false);
        ButterKnife.bind(this,mView);
        ArrayAdapter<CharSequence> adapter=  ArrayAdapter.createFromResource(getContext(), R.array.subjects_list,
              R.layout.spinner);
        ArrayAdapter<CharSequence> adapter2=  ArrayAdapter.createFromResource(getContext(), R.array.std_list,
               R.layout.spinner);
        sub_spinner.setAdapter(adapter);
        std_spinner.setAdapter(adapter2);

        second_card.setAlpha(0.0f);
        third_card.setAlpha(0.0f);
        int size=Utils.dpToPx(first_card.getHeight());
        first_card.setTranslationY(-200f);
        first_card.animate().translationY(0).setDuration(400)
                .setInterpolator(new OvershootInterpolator(1.5f))
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    second_card.setTranslationY(Utils.getScreenHeight(getContext()));
                        third_card.setTranslationY(Utils.getScreenHeight(getContext()));
                        second_card.animate().alpha(1f).translationY(0)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(600)
                                .start();
                        third_card.animate().alpha(1f).translationY(0)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(600)
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();

        return mView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
