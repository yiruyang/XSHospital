package com.example.a12878.xshospital;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a12878.xshospital.base.BaseActivity;
import com.example.a12878.xshospital.fragment.FifthFragment;
import com.example.a12878.xshospital.fragment.FirstFragment;
import com.example.a12878.xshospital.fragment.FourthFragment;
import com.example.a12878.xshospital.fragment.SecondFragment;
import com.example.a12878.xshospital.fragment.SixFragment;
import com.example.a12878.xshospital.fragment.ThirdFragment;
import com.example.a12878.xshospital.fragment.TitleFragment;
import com.example.a12878.xshospital.util.HXUtil;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @descriptoin 主界面
 * @author   Mr Yang
 * @date 2018/9/1 10:53
*/

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.bottom_first)
    ImageButton bottomFirst;
    @BindView(R.id.bottom_second)
    ImageButton bottomSecond;
    @BindView(R.id.bottom_third)
    ImageButton bottomThird;
    @BindView(R.id.bottom_fourth)
    ImageButton bottomFourth;
    @BindView(R.id.bottom_fifth)
    ImageButton bottomFifth;
    @BindView(R.id.bottom_sixth)
    ImageButton bottomSixth;
    @BindView(R.id.bottom_second_hot)
    ImageView bottomSecondHot;
    @BindView(R.id.bottom_fifth_hot)
    ImageView bottomFifthHot;
    @BindView(R.id.bottom_third_hot)
    ImageView bottomThirdHot;
    @BindView(R.id.bottom_fourth_hot)
    ImageView bottomFourthHot;
    @BindView(R.id.bottom_sixth_hot)
    ImageView bottomSixthHot;

    private static final String TAG = "MainActivity";
    public static Context mContext;

    //统计底部除了第一个图标的点击次数 来显示热点图标
    private int secondCount, thirdCount, fourthCount, fifthCount, sixthCount;
    public static final int maxCount = 5;//底部点击次数显示热点图标
    //用stack存放热点图标
    private Stack<ImageView> mCountStack = new Stack<>();

    //AlertDialog显示的TextView
    private TextView mOffTextView;
    //以下三个AlertDialog倒计时线程切换用到
    private Timer mOffTime;
    private TimerTask mTimerTask;
    private Handler mOffHandler;

    //六大界面Fragment
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;
    private FifthFragment fifthFragment;
    private SixFragment sixFragment;

    //顶部Fragment
    private TitleFragment titleFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//初始化butterKnife
        mContext = getApplicationContext();
        initOnClick();



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        //初始化环信
        HXUtil.init(mContext);
        //监听回调
        HXUtil.setMEssageToExoPlay(new HXUtil.MessageToExoPlay() {
            @Override
            public void getMessage(final String[] message) {
                Log.d("MainActivitygetINfo", "回调执行到MainActivity");
                mOffTextView = new TextView(MainActivity.this);
                mOffTextView.setTextSize(15);
                mOffTextView.setGravity(Gravity.CENTER);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.alertDialogTitle)
                                .setMessage(R.string.alertDialogMessage)
                                .setView(mOffTextView)
                                .setCancelable(false)
                                .setNegativeButton(R.string.alertDialogNegative, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.cancel();
                                        mOffTime.cancel();
                                    }
                                })
                                .setPositiveButton(R.string.alertDialogPositive, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int position) {
                                        sendVodUrl(message);
                                        mOffTime.cancel();
                                        mOffTextView = null;
                                    }
                                })
                                .create();
                        alertDialog.show();

                        mOffHandler = new Handler() {
                            public void handleMessage(Message msg) {

                                if (msg.what > 0) {
                                    ////动态显示倒计时
                                    mOffTextView.setText("    即将关闭："+msg.what);
                                    //语音控制1为再来一次, 2为播放结束
                                } else {
                                    ////倒计时结束自动关闭
                                    alertDialog.dismiss();
                                    mOffTime.cancel();
                                }
                                super.handleMessage(msg);
                            }
                        };

                        mOffTime = new Timer(true);
                        mTimerTask = new TimerTask() {
                            int countTime = 10;
                            public void run() {
                                if (countTime > 0) {
                                    countTime--;
                                }
                                Message msg = new Message();
                                msg.what = countTime;
                                mOffHandler.sendMessage(msg);
                            }
                        };
                        mOffTime.schedule(mTimerTask, 1000, 1000);
                    }
                });
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initOnClick() {
        bottomFirst.setOnClickListener(this);
        bottomThird.setOnClickListener(this);
        bottomFourth.setOnClickListener(this);
        bottomFifth.setOnClickListener(this);
        bottomSecond.setOnClickListener(this);
        bottomSixth.setOnClickListener(this);

        //设置默认Fragment
        setDefaultFragment();
    }

    //设置第一个Fragment，为首页
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();//获取管理类的实例
        FragmentTransaction ft = fm.beginTransaction();//开启事务
        firstFragment = new FirstFragment();
        titleFragment = new TitleFragment();
        ft.replace(R.id.id_content, firstFragment);
        ft.replace(R.id.id_fragmentLayout_title, titleFragment);
        bottomFirst.setBackground(getResources().getDrawable(R.drawable.icon_1_selected));
        ft.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId())
        {
            case R.id.bottom_first:
                if (firstFragment == null)
                {
                    firstFragment = new FirstFragment();
                }
                if (titleFragment != null){
                    titleFragment = new TitleFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, firstFragment);
                transaction.replace(R.id.id_fragmentLayout_title, titleFragment);
                refresh(bottomFirst, R.drawable.icon_1_selected);
                break;
            case R.id.bottom_second:
                if (secondFragment == null)
                {
                    secondFragment = new SecondFragment();
                }
                transaction.replace(R.id.id_content, secondFragment);
                refresh(bottomSecond, R.drawable.icon_2_selected);
                secondCount++;
                refreshHot(secondCount, bottomSecondHot);
                break;
            case R.id.bottom_third:
                if (thirdFragment == null)
                {
                    thirdFragment = new ThirdFragment();
                }
                transaction.replace(R.id.id_content, thirdFragment);
                refresh(bottomThird, R.drawable.icon_3_selected);
                thirdCount++;
                refreshHot(thirdCount, bottomThirdHot);
                break;
            case R.id.bottom_fourth:
                if (fourthFragment == null)
                {
                    fourthFragment = new FourthFragment();
                }
                transaction.replace(R.id.id_content, fourthFragment);
                refresh(bottomFourth, R.drawable.icon_4_selected);
                fourthCount++;
                refreshHot(fourthCount, bottomFourthHot);
                break;
            case R.id.bottom_fifth:
                if (fifthFragment == null)
                {
                    fifthFragment = new FifthFragment();
                }
                transaction.replace(R.id.id_content, fifthFragment);
                refresh(bottomFifth, R.drawable.icon_5_selected);
                fifthCount++;
                refreshHot(fifthCount, bottomFifthHot);
                break;
            case R.id.bottom_sixth:
                if (sixFragment == null)
                {
                    sixFragment = new SixFragment();
                }
                transaction.replace(R.id.id_content, sixFragment);
                refresh(bottomSixth, R.drawable.icon_6_selected);
                sixthCount++;
                refreshHot(sixthCount, bottomSixthHot);
                break;
        }
        /**
         * 如果不添加addToBackStack(null);这一句代码，
         * 那么fragment只在首次进入的时候会走生命周期，以后都不会走生命周期了
         */
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //刷新bottomBar的图标,当前的Fragment图标为选中状态
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void refresh(ImageButton imageButton, int id){
        bottomSixth.setBackground(getResources().getDrawable(R.drawable.sixth_fragment_icon));
        bottomFifth.setBackground(getResources().getDrawable(R.drawable.fifth_fragment_icon));
        bottomFourth.setBackground(getResources().getDrawable(R.drawable.fourth_fragment_icon));
        bottomThird.setBackground(getResources().getDrawable(R.drawable.third_fragment_icon));
        bottomSecond.setBackground(getResources().getDrawable(R.drawable.second_fragment_icon));
        bottomFirst.setBackground(getResources().getDrawable(R.drawable.first_fragment_icon));

        imageButton.setBackground(getResources().getDrawable(id));
    }

    private void refreshHot(int count, ImageView imageView){
        ImageView compareImage;
        if (count == maxCount){
            mCountStack.add(imageView);
            if (mCountStack.size() == 3){
                compareImage = mCountStack.get(0);
                if (compareImage.equals( bottomThirdHot)) {
                    bottomThirdHot.setVisibility(View.GONE);
                    thirdCount = 0;
                }else if (compareImage.equals(bottomFourthHot)){
                    bottomFourthHot.setVisibility(View.GONE);
                    fourthCount = 0;
                }else if (compareImage.equals(bottomFifthHot)){
                    bottomFifthHot.setVisibility(View.GONE);
                    fifthCount = 0;
                }else if (compareImage.equals(bottomSixthHot)){
                    bottomSixthHot.setVisibility(View.GONE);
                    sixthCount = 0;
                }else if (compareImage.equals(bottomSecondHot)){
                    bottomSecondHot.setVisibility(View.GONE);
                    secondCount = 0;
                }
                mCountStack.remove(0);
            }
            if (mCountStack.size() > 0){
                for (ImageView i:mCountStack){
                    i.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //移除消息监听
        HXUtil.removeMessageListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * @descriptoin  向FromServerActivity传递数组
     * @author   Mr Yang
     * @date 2018/9/20 11:03
     * @param 
    */
    public void sendVodUrl(String[] data){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, FromServerVideoActivity.class);
        intent.putExtra("key", "2");
        intent.putExtra("vodURl", data);
        startActivity(intent);
    }
}