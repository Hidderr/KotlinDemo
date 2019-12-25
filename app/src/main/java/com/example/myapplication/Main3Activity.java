package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.test.BlueToothUtils;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_edit_text);



        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    BlueToothUtils.getInstance().isOpen(null);
                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlueToothUtils.getInstance().createTvListenSocket();
            }
        }); findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlueToothUtils.getInstance().sendData("  hello bluetooth".getBytes());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BlueToothUtils.getInstance().cancle();
        LogUtils.log("BlueToothUtils"," 界面销毁=================");
    }
}
