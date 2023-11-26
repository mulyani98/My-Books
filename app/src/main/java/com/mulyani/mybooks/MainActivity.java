package com.mulyani.mybooks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        Fragment fragment;
        fragment = HomeFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment;

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        fragment = HomeFragment.newInstance();
                        fragmentTransaction.replace(R.id.content, fragment);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.navigation_favorite:
                        fragment = FavoriteFragment.newInstance();
                        fragmentTransaction.replace(R.id.content, fragment);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.navigation_about:
                        fragment = AboutFragment.newInstance();
                        fragmentTransaction.replace(R.id.content, fragment);
                        fragmentTransaction.commit();
                        return true;

                }
                return false;
            };
}
