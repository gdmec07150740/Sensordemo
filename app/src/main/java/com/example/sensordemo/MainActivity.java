package com.example.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mOrientetion;
    private Sensor mlight;
    private TextView tAccelerometer;
    private TextView tOrientetion;
    private TextView tLight;

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //对加速度传感器注册传感器监听器
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        //对方向传感器注册传感器监听器
        mSensorManager.registerListener(this,mOrientetion,SensorManager.SENSOR_DELAY_NORMAL);
        //对光线传感器注册传感器监听器
        mSensorManager.registerListener(this,mlight,SensorManager.SENSOR_DELAY_NORMAL);

   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tAccelerometer= (TextView) this.findViewById(R.id.accelerometer);
        tOrientetion= (TextView) this.findViewById(R.id.orientation);
        tLight= (TextView) this.findViewById(R.id.light);

        //获得传感器管理器
        mSensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        //获得加速度传感器
        mAccelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //获得方向传感器
        mOrientetion=mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //获得光线传感器
        mlight=mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x=event.values[SensorManager.DATA_X];
        float y=event.values[SensorManager.DATA_Y];
        float z=event.values[SensorManager.DATA_Z];
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tAccelerometer.setText("方位："+x+","+y+","+z);
        }
        //获得加速度的值
        else if (event.sensor.getType()==Sensor.TYPE_LIGHT){
            tLight.setText("光线："+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //在此方法中，编写某个传感器的精度发生变化时执行相应的操作
    }
}