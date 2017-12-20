package com.mulyani.mybooks;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.mulyani.mybooks.Helper.BottomNavigationHelper;

//menampilkan dasaran
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation =  findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationHelper.disableShiftMode(navigation);

        Fragment newFragment;
        newFragment = HomeFragment.newInstance();

        // Memberi nilai Awal fragment(posisi fragment)
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, newFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment newFragment;

            switch (item.getItemId()){
                case R.id.navigation_home:
                    newFragment = HomeFragment.newInstance();
                    fragmentTransaction.replace(R.id.content, newFragment);
                    fragmentTransaction.commit();
                    return true;
//                    break;
                case R.id.navigation_favorite:
                    newFragment = FavoritFragment.newInstance();
                    fragmentTransaction.replace(R.id.content, newFragment);
                    fragmentTransaction.commit();
                    return true;
//                    break;
                case R.id.navigation_about:
                    newFragment = AboutFragment.newInstance();
                    fragmentTransaction.replace(R.id.content, newFragment);
                    fragmentTransaction.commit();
                    return true;
//                    break;
            }
            return false;
        }

    };
}
