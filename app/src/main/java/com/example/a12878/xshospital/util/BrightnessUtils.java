package com.example.a12878.xshospital.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * Created by Yang on 2018/7/11.
 */

public class BrightnessUtils {

    // 判断是否开启了自动亮度调节

    public static boolean IsAutoBrightness(Context context) {

        boolean IsAutoBrightness = false;

        try {

            IsAutoBrightness = Settings.System.getInt(
                    context.getContentResolver(),

                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;

        }

        catch (Settings.SettingNotFoundException e)

        {

            e.printStackTrace();

        }

        return IsAutoBrightness;
    }

    // 获取当前屏幕的亮度

    public static int getScreenBrightness(Context context) {

        int nowBrightnessValue = 0;

        ContentResolver resolver = context.getContentResolver();

        try {

            nowBrightnessValue = android.provider.Settings.System.getInt(
                    resolver, Settings.System.SCREEN_BRIGHTNESS);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return nowBrightnessValue;
    }

    // 设置亮度
    // 程序退出之后亮度失效

    public static void setCurWindowBrightness(Context context, int brightness) {

        // 如果开启自动亮度，则关闭。否则，设置了亮度值也是无效的
        if (IsAutoBrightness(context)) {
            stopAutoBrightness(context);
        }

        // context转换为Activity
        Activity activity = (Activity) context;
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();

        // 异常处理
        if (brightness < 1) {
            brightness = 1;
        }

        // 异常处理
        if (brightness > 255) {
            brightness = 255;
        }

        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);

        activity.getWindow().setAttributes(lp);

    }

    // 设置系统亮度
    // 程序退出之后亮度依旧有效
    public static void setSystemBrightness(Context context, int brightness) {
        // 异常处理
        if (brightness < 1) {
            brightness = 1;
        }
        // 异常处理
        if (brightness > 255) {
            brightness = 255;
        }
        saveBrightness(context, brightness);
        //android6.0以后用下面的代码
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!Settings.System.canWrite(context)) {
//                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
//                intent.setData(Uri.parse("package:" + context.getPackageName()));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            } else {
//                saveBrightness(context, brightness);
//            }
//        }
    }

    // 停止自动亮度调节
    public static void stopAutoBrightness(Context context) {

        Settings.System.putInt(context.getContentResolver(),

                Settings.System.SCREEN_BRIGHTNESS_MODE,

                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    // 开启亮度自动调节
    public static void startAutoBrightness(Context context) {

        Settings.System.putInt(context.getContentResolver(),

                Settings.System.SCREEN_BRIGHTNESS_MODE,

                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);

    }

    // 保存亮度设置状态
    public static void saveBrightness(Context context, int brightness) {

        Uri uri = android.provider.Settings.System
                .getUriFor(Settings.System.SCREEN_BRIGHTNESS);

        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, brightness);

        context.getContentResolver().notifyChange(uri, null);
    }

}
