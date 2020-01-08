package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.test.BlueToothUtils;
import com.richpath.RichPath;
import com.richpath.RichPathView;
import com.richpathanimator.RichPathAnimator;

import java.util.UUID;

public class Main3Activity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    String play = "M 3,2 L 7,5 L7,5 L3,5z M 3,8 L7,5 L7,5 L3,5z";
    String pause = "M 2,2 L 8,2 L8,4 L2,4z M 2,8 L8,8 L8,6 L2,6z";
    public RichPath playgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_edit_text);


        TextInputEditText mac = findViewById(R.id.mac);
        TextInputEditText uuid = findViewById(R.id.uuid);
        ImageView imageView = findViewById(R.id.imageView1);
        RichPathView richPathView = findViewById(R.id.imageView2);
        RichPath richPath = richPathView.findRichPathByName("play");
        playgroup = richPathView.findRichPathByName("playgroup");
//        imageView.setVisibility(View.INVISIBLE);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                changeData(mac, uuid);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                    BlueToothUtils.getInstance().isOpen(null);
//                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeData(mac, uuid);

//                BlueToothUtils.getInstance().createTvListenSocket();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troditionalSVGAnimation(imageView);

                codeSVGAnimate(richPath);

//                changeData(mac, uuid);
//                BlueToothUtils.getInstance().sendData("  hello bluetooth".getBytes());
            }
        });
    }

    private void codeSVGAnimate(RichPath richPath) {
        RichPathAnimator.animate(richPath)
                .rotation(0, 90)
                .scale(0.5f,1)
                .duration(500)
                .andAnimate(richPath)
                .fillColor(Color.RED)
                .pathData(play,pause)
                .duration(500)
                .start();

    }

    private void troditionalSVGAnimation(ImageView imageView) {
        Toast.makeText(Main3Activity.this, "点击", Toast.LENGTH_SHORT).show();
        imageView.setVisibility(View.VISIBLE);
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void changeData(TextInputEditText mac, TextInputEditText uuid) {
        if (!TextUtils.isEmpty(mac.getText())) {
            BlueToothUtils.MA = mac.getText().toString();
        }
        if (!TextUtils.isEmpty(uuid.getText())) {
            BlueToothUtils.UUID = UUID.fromString(uuid.getText().toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BlueToothUtils.getInstance().cancle();
        LogUtils.log("BlueToothUtils", " 界面销毁=================");
    }
}
