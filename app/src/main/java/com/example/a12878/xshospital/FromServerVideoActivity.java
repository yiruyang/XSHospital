package com.example.a12878.xshospital;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Environment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.a12878.xshospital.base.BaseActivity;
import com.example.a12878.xshospital.base.VideoItem;
import com.example.a12878.xshospital.util.HXUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuangyuan.ycj.videolibrary.video.ManualPlayer;
import chuangyuan.ycj.videolibrary.video.VideoPlayerManager;
import chuangyuan.ycj.videolibrary.widget.VideoPlayerView;

public class FromServerVideoActivity extends BaseActivity {

    @BindView(R.id.server_playerView)
    VideoPlayerView playerView;

    public static final String TAG = "VideoActivity";
    private String mVideoUrl = Environment.getExternalStorageDirectory().getPath()+"/video/";
    //判断宣教播放器是否可见
    private boolean isPlayerViewVisible = false;
    private ManualPlayer exoPlayerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_server_video);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void init() {
        ButterKnife.bind(this);
        String[] videoUrl = getIntent().getStringArrayExtra("vodURl");
        playVideoMany(videoUrl);
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
                        AlertDialog alertDialog = new AlertDialog.Builder(FromServerVideoActivity.this)
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
                        alertDialog.show();
                    }
                });
            }
        });
    }

    private void playVideoMany(String[] videoUrl) {
        List<VideoItem> mVideoList = new ArrayList<>();
        //手势播放控制器创建
        exoPlayerManager = new VideoPlayerManager.Builder(VideoPlayerManager.TYPE_PLAY_MANUAL,
                playerView).create();
        exoPlayerManager.setPlayerGestureOnTouch(true);
        if (videoUrl.length > 1){
            for (int i = 0; i<videoUrl.length-1; i++){
                String str = videoUrl[i].replace("\"", "");
                mVideoList.add(new VideoItem(mVideoUrl + str));
            }
            //当a.length大于1的时候为多选
            for (int i = 0 ;i < mVideoList.size(); i++){
                Log.d("MainActivitygetINfotc", "播放路径" + mVideoList.get(i).getVideoUri());
            }
            exoPlayerManager.setPlayUri(mVideoList);
            exoPlayerManager.startPlayer();
            isPlayerViewVisible = true;
        }else {
            //单个视频播放
            String str = videoUrl[0].replace("\"", "");
            exoPlayerManager.setPlayUri(mVideoUrl + str);
            exoPlayerManager.startPlayer();
            isPlayerViewVisible = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        exoPlayerManager.onPause();
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
        intent.setClass(FromServerVideoActivity.this, FromServerVideoActivity.class);
        intent.putExtra("key", "2");
        intent.putExtra("vodURl", data);
        startActivity(intent);
        finish();
    }

}
