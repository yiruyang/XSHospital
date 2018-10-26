package com.example.a12878.xshospital.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.a12878.xshospital.R;
import com.example.a12878.xshospital.VideoActivity;
import com.example.a12878.xshospital.adapter.VodRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @descriptoin  首页Fragment
 * @author   Mr Yang
 * @date 2018/9/1 10:53
*/

public class FirstFragment extends Fragment {

    private String mVideoUrl = Environment.getExternalStorageDirectory().getPath()+"/video/";
    public static final String TAG = "FirstFragment";
    @BindView(R.id.first_tabLayout)
    VerticalTabLayout firstTabLayout;
    @BindView(R.id.first_viewPager)
    ViewPager firstViewPager;

    /**
     * 收缩界面的控件
     * first_fourth
     */
    //术前
    private CheckBox operationBeforePullDown;
    private LinearLayout mOperationBeforeLinearLayout;
    //手术当天
    private CheckBox operationBeOnPullDown;
    private LinearLayout mOperationBeOnLinearLayout;
    //术后
    private CheckBox operationAfterPullDown;
    private LinearLayout mOperationAfterLinearLayout;
    //预防深静脉血栓
    private CheckBox dvtPullDown;
    private LinearLayout dvtLinearLayout;

    /*
    * first_third
    * */
    private CheckBox first_third_firstPullDown;
    private LinearLayout first_third_firstLinearLayout;
    private CheckBox first_third_second_PullDown;
    private LinearLayout first_third_second_part;
    private CheckBox first_third_third_PullDown;
    private LinearLayout first_third_third_LinearLayout;
    private CheckBox first_third_fourth_PullDown;
    private LinearLayout first_third_fourth_LinearLayout;

    private Button linshi;
    private Button firstThirdFirstButton;

    //防止血液高凝状态
    private CheckBox hcsPullDown;
    private LinearLayout hcsLinearLayout;
    private Stack<View> mStack;
    private ScrollView scrollView;

    private List<View> viewList;
    private Context mContext;
    private LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        ButterKnife.bind(this, view);
        mContext = view.getContext();

        init();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() {
        viewList = new ArrayList<>();

        View firstPart = mInflater.inflate(R.layout.first_first__part, null);
        View secondPart = mInflater.inflate(R.layout.first_second__part, null);
        View thirdPart = mInflater.inflate(R.layout.first_third__part, null);
        View fourthPart = mInflater.inflate(R.layout.first_fourth__part, null);
        View fifthPart = mInflater.inflate(R.layout.first_fifth__part, null);
        View sixthPart = mInflater.inflate(R.layout.first_sixth__part, null);

        viewList.add(firstPart);
        viewList.add(secondPart);
        viewList.add(thirdPart);
        viewList.add(fourthPart);
        viewList.add(fifthPart);
        viewList.add(sixthPart);

        firstViewPager.setAdapter(new MyPageAdapter());//添加适配器
        firstTabLayout.setupWithViewPager(firstViewPager);//设置关联

        //设置分割线
        LinearLayout linearLayout = (LinearLayout) firstTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(mContext,
                R.drawable.divider)); //设置分割线的样式
        linearLayout.setDividerPadding(0); //设置分割线间隔

        /*thirdPart的操作*/
        first_third_firstPullDown = thirdPart.findViewById(R.id.first_third_first_PullDown);
        first_third_firstLinearLayout = thirdPart.findViewById(R.id.first_third_first_part);
        first_third_second_PullDown = thirdPart.findViewById(R.id.first_third_second_PullDown);
        first_third_second_part = thirdPart.findViewById(R.id.first_third_second_part);
        first_third_third_PullDown = thirdPart.findViewById(R.id.first_third_third_PullDown);
        first_third_third_LinearLayout = thirdPart.findViewById(R.id.first_third_third_part);
        first_third_fourth_PullDown = thirdPart.findViewById(R.id.first_third_fourth_PullDown);
        first_third_fourth_LinearLayout = thirdPart.findViewById(R.id.first_third_fourth_part);
        scrollView = (ScrollView) mInflater.inflate(R.layout.first_third_first,null);

        linshi = fifthPart.findViewById(R.id.linshi);
        firstThirdFirstButton = scrollView.findViewById(R.id.first_third_first_button);


        /*fourthPart的操作*/
        operationBeforePullDown = fourthPart.findViewById(R.id.operationBeforePullDown);
        mOperationBeforeLinearLayout = fourthPart.findViewById(R.id.operation_before_attention);
        operationBeOnPullDown = fourthPart.findViewById(R.id.operationBeOnPullDown);
        mOperationBeOnLinearLayout= fourthPart.findViewById(R.id.operation_beOn_attention);
        operationAfterPullDown = fourthPart.findViewById(R.id.operationAfterPullDown);
        mOperationAfterLinearLayout = fourthPart.findViewById(R.id.operation_after_attention);
        hcsPullDown = fourthPart.findViewById(R.id.hcsPullDown);
        hcsLinearLayout = fourthPart.findViewById(R.id.hcs_attention);
        dvtPullDown = fourthPart.findViewById(R.id.dvtPullDown);
        dvtLinearLayout = fourthPart.findViewById(R.id.dvt_attention);

        /*first_third*/
        first_third_firstPullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG,"before isChecked");
                    addView(scrollView, first_third_firstLinearLayout);
                }else {
                    removeView(first_third_firstLinearLayout);
                }
            }
        });

        first_third_second_PullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG,"before isChecked");
                    scrollView = (ScrollView) mInflater.inflate(R.layout.first_third_second,null);
                    addView(scrollView, first_third_second_part);
                }else {
                    removeView(first_third_second_part);
                }
            }
        });

        first_third_third_PullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG,"before isChecked");
                    scrollView = (ScrollView) mInflater.inflate(R.layout.first_third_third,null);
                    addView(scrollView, first_third_third_LinearLayout);
                }else {
                    removeView(first_third_third_LinearLayout);
                }
            }
        });


        first_third_fourth_PullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG,"before isChecked");
                    scrollView = (ScrollView) mInflater.inflate(R.layout.first_third_fourth,null);
                    addView(scrollView, first_third_fourth_LinearLayout);
                }else {
                    removeView(first_third_fourth_LinearLayout);
                }
            }
        });

        /*fifth_part*/
        linshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVodUrl("second/复杂腰椎退行性疾病.mp4");
            }
        });

        firstThirdFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVodUrl("first/病房入住指南.mp4");
            }
        });

        /*first_fourth*/
        operationBeforePullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG,"before isChecked");
                    scrollView = (ScrollView) mInflater.inflate(R.layout.operation_before_attention,null);
                    addView(scrollView, mOperationBeforeLinearLayout);
                }else {
                    removeView(mOperationBeforeLinearLayout);
                }
            }
        });

        operationBeOnPullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    scrollView = (ScrollView) mInflater.inflate(R.layout.operation_beon_attention,null);
                    addView(scrollView, mOperationBeOnLinearLayout);
                }else {
                    removeView(mOperationBeOnLinearLayout);
                }
            }
        });

        operationAfterPullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    scrollView = (ScrollView) mInflater.inflate(R.layout.operation_after_attention,null);
                    addView(scrollView, mOperationAfterLinearLayout);
                }else {
                    removeView(mOperationAfterLinearLayout);
                }
            }
        });

        hcsPullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    scrollView = (ScrollView) mInflater.inflate(R.layout.hcs_attention,null);
                    addView(scrollView, hcsLinearLayout);
                }else {
                    removeView(hcsLinearLayout);
                }
            }
        });

        dvtPullDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    scrollView = (ScrollView) mInflater.inflate(R.layout.dvt_attention,null);
                    addView(scrollView, dvtLinearLayout);
                }else {
                    removeView(dvtLinearLayout);
                }
            }
        });



    }

    private class MyPageAdapter extends PagerAdapter implements TabAdapter{

        List<String> tabList;

        @Override
        public int getCount() {
            return tabList.size();
        }

        public MyPageAdapter() {
            tabList = new ArrayList<>();
            Collections.addAll(tabList, getString(R.string.first_firstPart), getString(R.string.first_secondPart)
                    , getString(R.string.first_thirdPart), getString(R.string.first_fourthPart)
                    , getString(R.string.first_fifthPart), getString(R.string.first_sixthPart));
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            return new TabView.TabTitle.Builder()
                    .setContent(tabList.get(position))
                    .setTextColor(Color.WHITE, Color.BLACK)
                    .setTextSize(20)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = viewList.get(position);
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null){
                parent.removeAllViews();
            }
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void removeView(LinearLayout linearLayout) {
        if (mStack.size() > 0){
            linearLayout.removeView(mStack.pop());
        }
    }

    private void addView(ScrollView scrollView, LinearLayout linearLayout) {
        mStack = new Stack<>();
        linearLayout.addView(scrollView);
        mStack.push(scrollView);
    }

    public void sendVodUrl(String name){
        Intent intent = new Intent();
        intent.setClass(getActivity(), VideoActivity.class);
        intent.putExtra("key", "1");
        intent.putExtra("vodURlOne", mVideoUrl + name);
        startActivity(intent);
    }
}