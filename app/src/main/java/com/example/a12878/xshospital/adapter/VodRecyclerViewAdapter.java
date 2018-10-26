package com.example.a12878.xshospital.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a12878.xshospital.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/17.
 */

public class VodRecyclerViewAdapter extends RecyclerView.Adapter<VodRecyclerViewAdapter.RecyclerViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] medialData;
    private String[] medialAllData;
    private ItemListener mListener;
    private String vodUrl;
    public static final String directory = Environment.getExternalStorageDirectory()+ "/videoHealth/";

    public interface ItemListener{
        void onclickRoom(int position, String[] data, String url);
    }

    public VodRecyclerViewAdapter(Context context, String url) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        vodUrl = directory + url;
        getData(vodUrl);
    }

    public void notifyAdapter(String url) {
        vodUrl = directory + url;
        getData(vodUrl);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(mLayoutInflater.inflate(R.layout.item, parent, false));
    }

    public void setItemListener(ItemListener listener){
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.videoName.setText(medialData[position]);
        if (mListener != null){
            holder.videoName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onclickRoom(position, medialAllData, vodUrl);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (medialData != null){
            if (0 == medialData.length ){
                Toast.makeText(mContext, "没有视频", Toast.LENGTH_SHORT);
            }else {
                return medialData.length;
            }
        }
        return 0;
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.video_name)
        TextView videoName;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void getData(String url){
        File path = new File(url);
        Log.d("FifFragment", "文件路径为：" + path);
        if (path != null){
            File[] files=path.listFiles();
            if(files != null) {
                medialData = new String[files.length];
                medialAllData = new String[files.length];
                for (int i = 0 ; i<files.length; i++){
                    medialAllData[i] = String.valueOf(files[i].getName());
                    medialData[i] = String.valueOf(files[i].getName()).split("\\.")[0];
                }
            }else {
                Toast.makeText(mContext, "文件不存在", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
