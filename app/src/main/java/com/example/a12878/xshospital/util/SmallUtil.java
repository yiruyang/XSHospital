package com.example.a12878.xshospital.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.a12878.xshospital.FromServerVideoActivity;
import com.example.a12878.xshospital.MainActivity;
import com.example.a12878.xshospital.R;

import java.util.TreeSet;

/**
 * Created by 12878 on 2018/8/2.
 */

public class SmallUtil {

    //去除环信发过来的重复数据
    public static String[] getNewArrayString(String[] oldString){
        TreeSet<String> tr = new TreeSet<String>();
        System.out.print("====处理前=======");
        for(int i=0;i<oldString.length;i++){
            System.out.print(oldString[i]+" ");
            tr.add(oldString[i]);
        }
        String[] s2= new String[tr.size()];
        System.out.println("=====处理后======");
        for(int i=0;i<s2.length;i++){
            s2[i]=tr.pollFirst();//从TreeSet中取出元素重新赋给数组
            System.out.print(s2[i]+" ");
        }
        return s2;
    }


}
