package com.example.msi.gaodemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.msi.gaodemap.mapactivity.MapAcitivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button yj,wx,bz;
    MapView mapView = null;
    AMap aMap;
    MyLocationStyle myLocationStyle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPermission.getPermission(this);
        yj = (Button) findViewById(R.id.yj_button);
        wx = (Button) findViewById(R.id.wx_button);
        bz = (Button) findViewById(R.id.bz_button);
        yj.setOnClickListener(this);
        wx.setOnClickListener(this);
        bz.setOnClickListener(this);
        GetPermission.getPermission(this);

        //定义了一个地图view
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        BluePoint();

    }

    private void BluePoint() {
        //实现定位蓝点：
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        //定位蓝点展现模式 一共有8种模式
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。
        //开启室内地图方法
        aMap.showIndoorMap(true);   //true：显示室内地图；false：不显示；
        aMap.setTrafficEnabled(true);//显示实时路况图层，aMap是地图控制器对象

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yj_button:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图，aMap是地图控制器对象。
                break;
            case R.id.wx_button:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式，aMap是地图控制器对象。
                break;
            case R.id.bz_button:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);//矢量地图模式
                break;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

}
