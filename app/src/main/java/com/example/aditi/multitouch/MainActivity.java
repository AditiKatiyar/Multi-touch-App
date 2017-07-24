package com.example.aditi.multitouch;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    HashMap<Integer, View> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        map = new HashMap<>();
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int index = motionEvent.getActionIndex();
                int id = motionEvent.getPointerId(index);

                int event = motionEvent.getActionMasked();

                if (event == MotionEvent.ACTION_DOWN)
                {
                    float x = motionEvent.getX(index);
                    float y = motionEvent.getY(index);
                    View mView = new View(MainActivity.this);
                    mView.setLayoutParams(new LinearLayoutCompat.LayoutParams
                            (200, 200));
                    mView.setBackgroundColor(Color.BLACK);
                    mView.setX(x - 100);
                    mView.setY(y - 100);
                    map.put(id, mView);
                    relativeLayout.addView(mView);
                    Log.i("tag ", "ACTION_DOWN "+id);
                }
                else if (event == MotionEvent.ACTION_POINTER_DOWN)
                {
                    float x = motionEvent.getX(index);
                    float y = motionEvent.getY(index);
                    View mView = new View(MainActivity.this);
                    mView.setLayoutParams(new LinearLayoutCompat.LayoutParams
                            (200, 200));
                    mView.setBackgroundColor(Color.BLACK);
                    mView.setX(x - 100);
                    mView.setY(y - 100);
                    map.put(id, mView);
                    relativeLayout.addView(mView);
                    Log.i("tag ", "ACTION_POINTER_DOWN "+id);
                }
                else if (event == MotionEvent.ACTION_POINTER_UP)
                {
                    View viewR = map.get(id);
                    map.remove(id);
                    relativeLayout.removeView(viewR);
                    Log.i("tag " , "ACTION_POINTER_UP "+id);
                }
                else if (event == MotionEvent.ACTION_UP)
                {
                    View viewR = map.get(id);
                    map.remove(id);
                    relativeLayout.removeView(viewR);
                    Log.i("tag " , "ACTION_UP"+id);
                }
                else if (event == MotionEvent.ACTION_MOVE)
                {
                    for (Integer key : map.keySet()) {
                            View viewM = map.get(key);
                            int ind = motionEvent.findPointerIndex(key);
                            float x = motionEvent.getX(ind);
                            float y = motionEvent.getY(ind);
                            viewM.setX(x - 100);
                            viewM.setY(y - 100);

                    }
                }

                return true;
            }
        });
    }
}
