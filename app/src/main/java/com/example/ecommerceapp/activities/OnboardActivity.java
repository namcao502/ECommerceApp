package com.example.ecommerceapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapters.SliderAdapter;

public class OnboardActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button buttonNext;


    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewBinding();

        addDot(0);

        listener();

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //getSupportActionBar().hide();
    }

    private void listener(){
        ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDot(position);
                if (position == 0 || position == 1){
                    buttonNext.setVisibility(View.INVISIBLE);
                }
                else {
                    animation = AnimationUtils.loadAnimation(OnboardActivity.this, R.anim.slide_animation);
                    buttonNext.setAnimation(animation);
                    buttonNext.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        viewPager.addOnPageChangeListener(changeListener);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnboardActivity.this, RegistrationActivity.class));
                finish();
            }
        });
    }

    private void viewBinding(){
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        buttonNext = findViewById(R.id.get_started_btn);
    }

    private void addDot(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        int length = dots.length;
        for (int i=0; i<length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0 ){
            dots[position].setTextColor(getResources().getColor(R.color.pink));
        }
    }

}