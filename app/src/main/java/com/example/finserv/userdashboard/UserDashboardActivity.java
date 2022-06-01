package com.example.finserv.userdashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.finserv.ProfileActivity;
import com.example.finserv.R;
import com.example.finserv.helperclass.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UserDashboardActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    AccountsFragment accountsFragment;
    TransactionsFragment transactionsFragment;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        sp = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);
        viewPager = findViewById(R.id.delivery_dashboard_tab_viewpager);
        tabLayout = findViewById(R.id.delivery_dashboard_tab_layout);

        accountsFragment = new AccountsFragment();
        transactionsFragment = new TransactionsFragment();
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(transactionsFragment,"");
        viewPagerAdapter.addFragment(accountsFragment,"");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.transaction_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.account_icon);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(tabLayout.getSelectedTabPosition() == 0){
                    getSupportActionBar().setTitle("Transactions");
                }
                if(tabLayout.getSelectedTabPosition() == 1){
                    getSupportActionBar().setTitle("Accounts");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    public void onBackPressed() {

        int tabposition=tabLayout.getSelectedTabPosition();
        if(tabposition==1){
            viewPager.setCurrentItem(0);

        }

        else{

            super.onBackPressed();

        }


    }



    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>(); //store fragments in this list
        private final List<String> fragmentTitle = new ArrayList<>();//titles of fragments in this list

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            fragmentTitle.add(title);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_dashboard_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.profile){
            sp.edit().clear().commit();
            Intent intent = new Intent(UserDashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }




        return super.onOptionsItemSelected(item);
    }
}