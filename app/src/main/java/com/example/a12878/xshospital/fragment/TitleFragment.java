package com.example.a12878.xshospital.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12878.xshospital.MainActivity;
import com.example.a12878.xshospital.R;
import com.example.a12878.xshospital.util.BrightnessUtils;
import com.example.a12878.xshospital.util.LanguageUtil;

import java.util.Locale;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangYiRu on 2018/7/9.
 */

public class TitleFragment extends Fragment {

    @BindView(R.id.id_title_left_btn)
    ImageButton titleLeftBtn;
    @BindView(R.id.brightness)
    ImageButton brightness;
    @BindView(R.id.language)
    Switch language;

    private LayoutInflater mInflater;
    private Context mContext;

    private SeekBar mySeekBar;
    private PopupWindow mPopupWindow;
    private int mBrightness = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.title_fragment, container, false);
        mContext = view.getContext();
        ButterKnife.bind(this,view);
        init();

        return view;
    }

    private void init() {

        if (isZh()){
            language.setChecked(false);
        }else {
            language.setChecked(true);
        }
        brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBrightness();
            }
        });

        language.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    LanguageUtil.set(isChecked,mContext);
                }else{
                    LanguageUtil.set(false,mContext);
                }
                refreshSelf();
            }
        });
    }
    private void initBrightness() {
        View contentView = getPopupWindowContentView();
        // 创建PopupWindow时候指定高宽时showAsDropDown能够自适应(能够根据剩余空间自动选中向上向下弹出)
        // 如果设置为wrap_content,showAsDropDown会认为下面空间一直很充足（我以认为这个Google的bug）
        // 备注如果PopupWindow里面有ListView,ScrollView时，一定要动态设置PopupWindow的大小
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        // popupWindow.showAsDropDown(mButton1);  // 默认在mButton1的左下角显示
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOffset = brightness.getWidth() / 2 - contentView.getMeasuredWidth() / 2;
        mPopupWindow.showAsDropDown(brightness, xOffset, 0);    // 在mButton1的中间显示
    }

    private View getPopupWindowContentView() {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.popupwindow;   // 布局ID
        View contentView = LayoutInflater.from(mContext).inflate(layoutId, null);
        mySeekBar = contentView.findViewById(R.id.mySeekBar_light);
        mySeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImp());
        mBrightness = BrightnessUtils.getScreenBrightness(mContext);
        mySeekBar.setProgress(mBrightness);
        return contentView;
    }

    // 进度条值改变时，调节屏幕亮度
    private class OnSeekBarChangeListenerImp implements
            SeekBar.OnSeekBarChangeListener {

        // 触发操作，拖动
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            mBrightness = seekBar.getProgress();
            BrightnessUtils.setSystemBrightness(mContext, mBrightness);
        }

        // 表示进度条刚开始拖动，开始拖动时候触发的操作
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        // 停止拖动时候
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }
    }

    //刷新当前的语言环境
    public  void refreshSelf(){
        getActivity().finish();
        Intent refresh = new Intent(getActivity(), MainActivity.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().startActivity(refresh);
    }

    private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }
}