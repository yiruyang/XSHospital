package com.example.a12878.xshospital.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.example.a12878.xshospital.MainActivity;

import java.util.Locale;

/**
 * Created by 12878 on 2018/7/12.
 */

public class LanguageUtil {
    /**
     * @param isEnglish true  ：点击英文，把中文设置未选中
     *                  false ：点击中文，把英文设置未选中
     */
    public static void set(boolean isEnglish,Context context) {

        Configuration configuration = context.getResources().getConfiguration();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (isEnglish) {
            //设置英文
            configuration.locale = Locale.ENGLISH;
        } else {
            //设置中文
            configuration.locale = Locale.SIMPLIFIED_CHINESE;
        }
        //更新配置
        context.getResources().updateConfiguration(configuration, displayMetrics);
    }

}
