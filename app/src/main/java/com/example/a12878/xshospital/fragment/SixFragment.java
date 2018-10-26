package com.example.a12878.xshospital.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.a12878.xshospital.R;

import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 12878 on 2018/7/10.
 */

public class SixFragment extends Fragment {

    @BindView(R.id.industrial_pull_down)
    CheckBox industrialPullDown;
    @BindView(R.id.industrial_accident)
    LinearLayout mLinearLayout;
    @BindView(R.id.traffic_pull_down)
    CheckBox trafficPullDown;
    @BindView(R.id.traffic_accident)
    LinearLayout mTrafficLinearLayout;

    private LayoutInflater mLayountInflater;
    private ScrollView scrollView;
    private ScrollView trafficScrollView;
    private Stack<View> mStack;
    private Stack<View> mTrafficStack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sixth_fragment, container, false);
        mLayountInflater = inflater;
        mStack=new Stack<>();
        mTrafficStack=new Stack<>();
        ButterKnife.bind(this, view);

        industrialPullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    addIndustrialView();
                }else {
                    removeView();
                }
            }
        });

        trafficPullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    addTrafficView();
                }else {
                    removeTrafficView();
                }
            }
        });
        return view;
    }


    private void removeView() {
        if(mStack.size()>0){
            mLinearLayout.removeView(mStack.pop());
        }
    }

    private void removeTrafficView() {
        if (mTrafficStack.size() > 0){
            mTrafficLinearLayout.removeView(mTrafficStack.pop());
        }
    }

    private void addIndustrialView() {
        scrollView = (ScrollView) mLayountInflater.inflate(R.layout.industrial_accident,null);
        mLinearLayout.addView(scrollView);
        mStack.push(scrollView);
    }

    private void addTrafficView() {
        trafficScrollView = (ScrollView) mLayountInflater.inflate(R.layout.traffic_accident,null);
        mTrafficLinearLayout.addView(trafficScrollView);
        mTrafficStack.push(trafficScrollView);
    }
}
