package com.example.mathjax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ImageButtonActivity extends AppCompatActivity implements View.OnTouchListener {


    private View btn;
    private TextView quizTv;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button_actvity);

        btn = findViewById(R.id.btn);
        quizTv = (TextView) findViewById(R.id.tv_quiz_option_text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageButtonActivity.this, "hello", Toast.LENGTH_SHORT).show();
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
            btn.performClick();
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            int top = 0;
            int bot = quizTv.getLineBounds(quizTv.getLineCount() - 1, null);
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


