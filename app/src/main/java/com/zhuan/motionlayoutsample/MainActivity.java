package com.zhuan.motionlayoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * 参考：
 * https://www.jianshu.com/p/5203cf11d943
 */
public class MainActivity extends AppCompatActivity {
    private MotionLayout mMotionLayout;
    private Button btnToStartScene;
    private Button btnToEndScene;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMotionLayout = findViewById(R.id.motionLayout);
        btnToStartScene = findViewById(R.id.btnToStartScene);
        btnToEndScene = findViewById(R.id.btnToEndScene);
        mSeekBar = findViewById(R.id.seekBar);

        mMotionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
                Log.e("MotionLayout", "onTransitionStarted: " + motionLayout);
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
                Log.e("MotionLayout", "onTransitionChange: startId = " + startId + ",endId = " + endId + ",progress = " + progress);
                mSeekBar.setProgress((int) (progress * 100));
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                Log.e("MotionLayout", "onTransitionCompleted: currentId = " + currentId);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
                Log.e("MotionLayout", "onTransitionTrigger: " + v);
            }
        });

//        代码来手动执行过渡动画
        btnToStartScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换到 Start 场景
                mMotionLayout.transitionToStart();
            }
        });

        btnToEndScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换到 End 场景
                mMotionLayout.transitionToEnd();
            }
        });

        // 支持手动调整过渡动画的播放进度，  0.0 ~ 1.0

        mSeekBar.setMax(0);
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMotionLayout.setProgress((float) (progress * 0.01));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
