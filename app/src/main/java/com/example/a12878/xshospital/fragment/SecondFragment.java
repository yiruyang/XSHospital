package com.example.a12878.xshospital.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a12878.xshospital.R;
import com.example.a12878.xshospital.VideoActivity;

/**
 * Created by 12878 on 2018/8/2.
 */

public class SecondFragment extends Fragment{

    private String mVideoUrl = Environment.getExternalStorageDirectory().getPath()+"/video/second/";
    private Context mContext;
    private LayoutInflater mInflater;

    private Button secondSecondButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        mContext = view.getContext();
        mInflater = inflater;
        init(view);
        return view;
    }

    private void init(View view) {

        secondSecondButton = view.findViewById(R.id.second_second_button);
        secondSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVodUrl("复杂腰椎退行性疾病.mp4");
            }
        });
    }

    public void sendVodUrl(String name){
        Intent intent = new Intent();
        intent.setClass(getActivity(), VideoActivity.class);
        intent.putExtra("key", "1");
        intent.putExtra("vodURlOne", mVideoUrl + name);
        startActivity(intent);
    }
}
