package com.onthego.onthego.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.onthego.onthego.R;
import com.onthego.onthego.api.APIRequest;
import com.onthego.onthego.database.viewmodels.FoodViewModel;
import com.onthego.onthego.database.viewmodels.PlaceViewModel;
import com.onthego.onthego.fragments.OrderFragment;
import com.onthego.onthego.fragments.PlaceListFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private APIRequest apiRequest;
    private FoodViewModel foodViewModel;
    private PlaceViewModel placeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View loadingLayout = findViewById(R.id.loadingLayout);

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        placeViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
        apiRequest = new APIRequest(this, loadingLayout, foodViewModel, placeViewModel);

        setupToolbar();
        setupDrawer();

        refresh();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void setupDrawer() {
        switchFragment(new PlaceListFragment());

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawerPlaces:
                        switchFragment(new PlaceListFragment());
                        break;
                    case R.id.drawerOrder:
                        switchFragment(new OrderFragment());
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

    private void login() {
        String user = "edward";
        String password = "uca";
        apiRequest.getPlaces();
        //apiRequest.login(user, password);
        //apiRequest.signUp("test2", "pass");
    }

    private void refresh() {
        apiRequest.refresh();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
