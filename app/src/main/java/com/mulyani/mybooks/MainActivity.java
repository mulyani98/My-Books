package com.mulyani.mybooks;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

//menampilkan dasaran
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation =  findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        BottomNavigationHelper.disableShiftMode(navigation);
        navigation.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

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
