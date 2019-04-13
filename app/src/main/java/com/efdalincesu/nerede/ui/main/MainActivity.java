package com.efdalincesu.nerede.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.efdalincesu.nerede.R;
import com.efdalincesu.nerede.ui.main.location.LocationFragment;
import com.efdalincesu.nerede.util.RelationFragmentRefreshListener;
import com.efdalincesu.nerede.common.ViewPagerAdapter;
import com.efdalincesu.nerede.databinding.ActivityMainBinding;
import com.efdalincesu.nerede.ui.account.login.LoginActivity;
import com.efdalincesu.nerede.ui.main.friend.FriendFragment;
import com.efdalincesu.nerede.ui.main.request.BottomDialogFragment;
import com.efdalincesu.nerede.ui.search.SearchActivity;
import com.efdalincesu.nerede.util.SessionManager;
import com.efdalincesu.nerede.util.Snack;

public class MainActivity extends AppCompatActivity implements LifecycleOwner, RelationFragmentRefreshListener {

    ActivityMainBinding binding;
    MainViewModel viewModel;
    SessionManager manager;
    ViewPagerAdapter adapter;
    BottomDialogFragment bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        manager = new SessionManager(this);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        isLogin();

        binding.setUser(manager.getUser());

        setupUI();


    }

    @Override
    protected void onResume() {
        super.onResume();
        isLogin();
    }

    public void isLogin(){
        viewModel.isLogin(manager.getUserId(), manager.getUser().getPlayerId()).observe(this, isLogin -> {
            if (!isLogin) {
                Snack.onFailed(binding.containerLayout,getString(R.string.isLogin_error));
                manager.destroySession();
                goLoginActivity();
            }
        });


        if (!manager.isLogin())
            goLoginActivity();

    }

    private void setupUI() {

        setSupportActionBar(binding.include.toolbar);
        setupViewPager();
        binding.mainTabs.setupWithViewPager(binding.mainViewpager);
        bottomSheet=new BottomDialogFragment();
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FriendFragment(), getString(R.string.tab_child));
        if (manager.getType() == 1)
            adapter.addFragment(new LocationFragment(), getString(R.string.tab_location));
        binding.mainViewpager.setAdapter(adapter);
        binding.mainViewpager.setOffscreenPageLimit(2);
    }

    public void logout(View v) {
        viewModel.logout(manager.getUserId(), manager.getUser().getPlayerId()).observe(this, isLogout -> {
            if (isLogout) {
                manager.destroySession();
                goLoginActivity();
            } else {
                Snack.onFailed(binding.containerLayout, getString(R.string.error));
            }
        });
    }


    public void goLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        int type = manager.getType();
        if (type == 0)
            inflater.inflate(R.menu.main_search_menu, menu);
        else
            inflater.inflate(R.menu.main_request_menu,menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_search:
                startActivity(new Intent(this, SearchActivity.class));
                finish();
                break;
            case R.id.menu_request:
                bottomSheet.show(getSupportFragmentManager(),"Custom Bottom Sheet");
                break;
        }
        return true;
    }

    @Override
    public void onRelationRefresh() {
        Fragment fragment=adapter.getItem(0);
        ((FriendFragment) fragment).refresh();
    }
}
