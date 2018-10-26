package com.example.a12878.xshospital.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a12878.xshospital.VideoActivity;
import com.example.a12878.xshospital.R;
import com.example.a12878.xshospital.adapter.VodRecyclerViewAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 12878 on 2018/7/10.
 */

public class FifthFragment extends Fragment {


    @BindView(R.id.vod_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vod)
    ViewPager viewPager;

    private Context mContext;
    private LayoutInflater mInflater;
    //当前界面中的几个viewpager
    private RecyclerView firstRecyclerView, secondRecyclerView, thirdRecyclerView, fourthRecyclerView;
    //viewpager对应的tab
    private TabLayout.Tab one, two, three, four;
    private List<View> viewList;

    private Spinner three_spinner;
    private Spinner four_spinner;
    private TextView three_textView;
    private TextView four_textView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.fifth_fragment, container, false);
        ButterKnife.bind(this, view);
        mContext = view.getContext();

        init();
        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() {

        viewList = new ArrayList<>();
        firstRecyclerView = mInflater.inflate(R.layout.viewpager_recyclerview, null)
                .findViewById(R.id.movie_recyclerView);
        secondRecyclerView = mInflater.inflate(R.layout.viewpager_recyclerview, null)
                .findViewById(R.id.movie_recyclerView);
        thirdRecyclerView = mInflater.inflate(R.layout.viewpager_recyclerview, null)
                .findViewById(R.id.movie_recyclerView);
        fourthRecyclerView = mInflater.inflate(R.layout.viewpager_recyclerview, null)
                .findViewById(R.id.movie_recyclerView);
        viewList.add(firstRecyclerView);
        viewList.add(secondRecyclerView);
        viewList.add(thirdRecyclerView);
        viewList.add(fourthRecyclerView);

        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            //对显示的资源进行初始化
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v=viewList.get(position);
                ViewGroup parent = (ViewGroup) v.getParent();
                if (parent != null) {
                    parent.removeAllViews();
                }
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        };

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        //添加viewpager的适配器
        viewPager.setAdapter(adapter);
        //tablayout和viewpager关联
        tabLayout.setupWithViewPager(viewPager);

        //更新tablayout
        notifyTablayout();

        initRecyclerView();

    }

    private void notifyTablayout() {

        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
        three = tabLayout.getTabAt(2);
        four = tabLayout.getTabAt(3);

        View viewThree = LayoutInflater.from(mContext).inflate(R.layout.spinner_three, null);
        three_textView = viewThree.findViewById(R.id.spinner_three_text);
        three_spinner = viewThree.findViewById(R.id.spinner_three);
        three.setCustomView(viewThree);

        View viewfour = LayoutInflater.from(mContext).inflate(R.layout.spinner_four, null);
        four.setCustomView(viewfour);
        four_textView = viewfour.findViewById(R.id.spinner_four_text);
        four_spinner = viewfour.findViewById(R.id.spinner_four);

        one.setText(R.string.health_tab1);
        two.setText(R.string.health_tab2);
        three_textView.setText(R.string.health_tab3);
        four_textView.setText(R.string.health_tab4);





        //设置分割线
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(mContext,
                R.drawable.divider)); //设置分割线的样式
        linearLayout.setDividerPadding(8); //设置分割线间隔

        //添加Adapter和Adapter设置监听
    }

    private void initRecyclerView() {
        final String[] threeSpinnerData = getResources().getStringArray(R.array.upper_limb);
        final String[] fourSpinnerData = getResources().getStringArray(R.array.lower_limb);
        //viewpager设置为宫格布局
        firstRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));//宫格布局
        secondRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));//宫格布局
        thirdRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));//宫格布局
        fourthRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));//宫格布局
        //viewpager各自添加适配器
        VodRecyclerViewAdapter firstVodRecyclerViewAdapter = new VodRecyclerViewAdapter(mContext,"first/");
        VodRecyclerViewAdapter secondVodRecyclerViewAdapter = new VodRecyclerViewAdapter(mContext,"second/");
        final VodRecyclerViewAdapter thirdVodRecyclerViewAdapter = new VodRecyclerViewAdapter(mContext,"third/前臂/");
        final VodRecyclerViewAdapter fourthVodRecyclerViewAdapter = new VodRecyclerViewAdapter(mContext,"fourth/大腿/");
        firstRecyclerView.setAdapter(firstVodRecyclerViewAdapter);
        secondRecyclerView.setAdapter(secondVodRecyclerViewAdapter);
        thirdRecyclerView.setAdapter(thirdVodRecyclerViewAdapter);
        fourthRecyclerView.setAdapter(fourthVodRecyclerViewAdapter);

        //FirstVodRecyclerView的item添加监听
        firstVodRecyclerViewAdapter.setItemListener(new VodRecyclerViewAdapter.ItemListener() {
            @Override
            public void onclickRoom(int position, String[] data, String url) {
                sendVodUrl(data, position, url);
            }
        });

        //SecondVodRecyclerView的item添加监听
        secondVodRecyclerViewAdapter.setItemListener(new VodRecyclerViewAdapter.ItemListener() {
            @Override
            public void onclickRoom(int position, String[] data, String url) {
                sendVodUrl(data, position, url);
            }
        });
        //ThirdVodRecyclerView的item添加监听
        thirdVodRecyclerViewAdapter.setItemListener(new VodRecyclerViewAdapter.ItemListener() {
            @Override
            public void onclickRoom(int position, String[] data, String url) {
                sendVodUrl(data, position, url);
//                getActivity().finish();
            }
        });

        fourthVodRecyclerViewAdapter.setItemListener(new VodRecyclerViewAdapter.ItemListener() {
            @Override
            public void onclickRoom(int position, String[] data, String url) {
                sendVodUrl(data, position,url);
            }
        });

        //spinner的监听事件
        three_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                three_textView.setText(threeSpinnerData[position]);
                thirdVodRecyclerViewAdapter.notifyAdapter("third/" + threeSpinnerData[position] + "/");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        four_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                four_textView.setText(fourSpinnerData[position]);
                fourthVodRecyclerViewAdapter.notifyAdapter("fourth/" + fourSpinnerData[position] + "/");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //tab监听事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.equals(three)){
                    three_textView.setText(threeSpinnerData[0]);
                    three_spinner.setVisibility(View.VISIBLE);
                }else if (tab.equals(four)){
                    four_textView.setText(fourSpinnerData[0]);
                    four_spinner.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.equals(three)){
                    three_textView.setText(R.string.health_tab3);
                    three_spinner.setVisibility(View.GONE);
                }else if (tab.equals(four)){
                    four_textView.setText(R.string.health_tab4);
                    four_spinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void sendVodUrl(String[] data, int position, String url){
        Intent intent = new Intent();
        intent.setClass(getActivity(), VideoActivity.class);
        intent.putExtra("key", "1");
        intent.putExtra("vodURlOne", url + data[position]);
        startActivity(intent);
    }

}