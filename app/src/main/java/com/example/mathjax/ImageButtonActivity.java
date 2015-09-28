package com.example.mathjax;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ImageButtonActivity extends AppCompatActivity implements View.OnTouchListener {


    private ViewGroup btn;
    private TextView quizTv;
    private GestureDetector gestureDetector;
    public final static int SCROLL_BOTTOM_PADDING = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button_actvity);

        btn = (ViewGroup) findViewById(R.id.btn);
        quizTv = (TextView) findViewById(R.id.tv_quiz_option_text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageButtonActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });


        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(ImageButtonActivity.this, "long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        quizTv.setOnTouchListener(this);

        gestureDetector = new GestureDetector(this, new MyGestureDetector());

        quizTv.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
                "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
                "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
                "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. " +
                "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
                "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. " +
                "Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. " +
                "Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, " +
                "sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus." +
                " Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo." +
                " Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if(btn.isClickable())
                btn.performClick();
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            if(btn.isClickable())
                btn.performLongClick();
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            Log.d("Text View","Pad bot: "+quizTv.getPaddingBottom());
            Log.d("Text View","Pad top: "+quizTv.getPaddingTop());


            Rect bounds = new Rect();

            int first_line_bot = quizTv.getLineBounds(0, bounds);
            int first_line_top = bounds.top;

            Log.d("Text View","First line bot: "+ first_line_bot);
            Log.d("Text View","First line top: "+ first_line_top);

            int top = bounds.top;
            int bot = quizTv.getLineBounds(quizTv.getLineCount() - 1, null) + SCROLL_BOTTOM_PADDING;
            int actual_ht = bot - top;
            int visible_ht = Math.min(quizTv.getHeight() - quizTv.getPaddingBottom() - quizTv.getPaddingTop(), actual_ht);

            int scrollY = (int) Math.max(top - quizTv.getScrollY(), distanceY);
            scrollY = Math.min(actual_ht - quizTv.getScrollY() - visible_ht, scrollY);

            quizTv.scrollBy(0, scrollY);

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}


