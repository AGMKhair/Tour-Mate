package com.xcoders.tourmate;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button signInBtn, signUpBtn;

    private ViewPager viewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        initialization();
        onClick();

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null) {
            Intent gotoMain = new Intent(HomeActivity.this, MainActivity.class);
            gotoMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(gotoMain);
        }

    }

    private void onClick()
    {
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SignUpActivity.class));
                finish();
            }
        });
    }

    private void initialization()
    {
        signInBtn = findViewById(R.id.signInBtnId);
        signUpBtn = findViewById(R.id.singUpBtnId);
        viewPager = findViewById(R.id.viewPage);
        mDotLayout = findViewById(R.id.linearLayout);
       // Theme_one = findViewById(R.id.singInBTNId);
       // Theme_two = findViewById(R.id.singUpBTNId);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);



    }

    private void goToMain()
    {
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
        finish();

    }

    private void restartApp()
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addDotsIndicator(int postion)
    {
        // mDots =null;
        mDots = new TextView[4];
        mDotLayout.removeAllViews();
        for(int i = 0 ; i<mDots.length ; i++)
        {
            mDots[i]  = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length >0)
        {
            mDots[postion].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i)
        {

        }
    };


}
