package com.example.msi.gaodemap;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by 庞小西 on 2017/7/23.
 */

public class GetPermission {

    private static  String[] arr_permissions = {
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.CHANGE_WIFI_STATE",
            "android.permission.INTERNET",
            "android.permission.READ_PHONE_STATE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",
            "android.permission.BLUETOOTH",
            "android.permission.BLUETOOTH_ADMIN"
    };
    private static  int version = 0;

    public static  void getPermission(Activity activity){
        //获得sdk版本号
        version = Build.VERSION.SDK_INT;
        Log.e("version",version+"");
        if (version>=23){
            int permission = ActivityCompat.checkSelfPermission(
                    activity.getApplicationContext(),
                    arr_permissions[0]);
            Log.e("int",permission+"");
            //判断是否拥有权限
            if (permission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity,arr_permissions,1);
            }
        }
    }

}
