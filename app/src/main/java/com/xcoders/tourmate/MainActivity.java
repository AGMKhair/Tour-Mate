package com.xcoders.tourmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xcoders.tourmate.Fragment.BookingFragment;
import com.xcoders.tourmate.Fragment.EventFragment;
import com.xcoders.tourmate.Fragment.NearByFragment;
import com.xcoders.tourmate.Fragment.ProfileFragment;
import com.xcoders.tourmate.Fragment.WeatherFragment;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_event:
                    replaceFagment(new EventFragment());
                    return true;
                case R.id.navigation_nearby:
                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_weather:
                    replaceFagment(new WeatherFragment());
                    return true;
                case R.id.navigation_booking:
                    replaceFagment(new BookingFragment());
                    return true;
                case R.id.navigation_more:
                    replaceFagment(new ProfileFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFagment(new EventFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initialization();
    }

    private void initialization() {
        frameLayout=findViewById(R.id.frameLayaoutID);
    }

    public void replaceFagment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayaoutID,fragment);
        ft.commit();
    }

}
