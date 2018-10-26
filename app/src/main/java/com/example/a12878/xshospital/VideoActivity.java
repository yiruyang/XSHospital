package com.example.a12878.xshospital;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.a12878.xshospital.base.BaseActivity;
import com.example.a12878.xshospital.base.VideoItem;
import com.example.a12878.xshospital.util.HXUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuangyuan.ycj.videolibrary.video.ManualPlayer;
import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;


public class VideoActivity extends BaseActivity {

    @BindView(R.id.health_playerView)
    VideoPlayerView playerView;

    public static final String TAG = "VideoActivity";
    private String mVideoUrl = Environment.getExternalStorageDirectory().getPath()+"/video/";
    //判断宣教播放器是否可见
    private boolean isPlayerViewVisible = false;
    private Context mContext;
    private ManualPlayer exoPlayerManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        play();
        //初始化环信
        HXUtil.init(mContext);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initListener();
    }

    private void initListener() {
        //监听回调
        HXUtil.setMEssageToExoPlay(new HXUtil.MessageToExoPlay() {
            @Override
            public void getMessage(final String[] message) {
                Log.d("MainActivitygetINfo", "回调执行到MainActivity");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (exoPlayerManager.isPlaying()){
                            exoPlayerManager.setStartOrPause(false);
                        }
                        AlertDialog alertDialog = new AlertDialog.Builder(VideoActivity.this)
                                .setTitle(R.string.alertDialogTitle)
                                .setCancelable(false)
                                .setNegativeButton(R.string.alertDialogNegative, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.cancel();
                                        exoPlayerManager.setStartOrPause(true);
                                    }
                                })
                                .setPositiveButton(R.string.alertDialogPositive, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int position) {
                                        sendVodUrl(message);
                                    }
                                })
                                .create();

                        if (!VideoActivity.this.isFinishing())//xActivity即为本界面的Activity
                        {
                            alertDialog.show();
                        }

                    }
                });
            }
        });
    }

    private void play() {
        //手势播放控制器创建
        exoPlayerManager = new VideoPlayerManager.Builder(VideoPlayerManager.TYPE_PLAY_MANUAL,
                playerView).create();
        exoPlayerManager.setPlayerGestureOnTouch(true);
        //获取主界面传过来的key值
        String key = getIntent().getStringExtra("key");
        Log.d(TAG, key);
        if (key != null) {
            if ("1".equals(key)) {
                String videoUrl = getIntent().getStringExtra("vodURlOne");
                playVideoOne(videoUrl);
            }
        }
    }


    private void playVideoOne(String videoUrl) {
        exoPlayerManager.setPlayUri(videoUrl);
        exoPlayerManager.startPlayer();
        isPlayerViewVisible = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        exoPlayerManager.onPause();
        //移除消息监听
        HXUtil.removeMessageListener();
    }

    @Override
    protected void onDestroy() {
        exoPlayerManager.onDestroy();
        isPlayerViewVisible = false;
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //横竖屏切换
        exoPlayerManager.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    public void sendVodUrl(String[] data){
        Intent intent = new Intent();
        intent.setClass(VideoActivity.this, FromServerVideoActivity.class);
        intent.putExtra("key", "2");
        intent.putExtra("vodURl", data);
        startActivity(intent);
        finish();
    }
}
