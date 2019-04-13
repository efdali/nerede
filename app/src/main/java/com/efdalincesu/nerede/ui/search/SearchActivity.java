package com.efdalincesu.nerede.ui.search;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.efdalincesu.nerede.R;
import com.efdalincesu.nerede.databinding.ActivitySearchBinding;
import com.efdalincesu.nerede.model.Member;
import com.efdalincesu.nerede.ui.main.MainActivity;
import com.efdalincesu.nerede.util.SessionManager;
import com.efdalincesu.nerede.util.Snack;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.RowClickListener {

    ActivitySearchBinding binding;
    SearchViewModel viewModel;
    SearchAdapter userAdapter;
    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        manager = new SessionManager(this);
        setSupportActionBar(binding.toolbar);

        userAdapter = new SearchAdapter(R.layout.search_list_item,this);

        SearchView searchView = binding.searchView;
        searchView.setIconified(false);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() > 0) {
                    search(s);
                }else {

                }
                return false;
            }
        });

    }

    public void returnBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void search(String str) {
        viewModel.search(str, manager.getUserId()).observe(this, userList -> {
            if (userList.size()>0) {
                binding.userRecycler.setLayoutManager(new LinearLayoutManager(this));
                binding.userRecycler.setHasFixedSize(true);
                userAdapter.submitList(userList);
                binding.userRecycler.setAdapter(userAdapter);
            }
        });
    }

    @Override
    public void onRowClicked(Member member) {
        if (member.getState() == null) {
            sendRequest(member);
            member.setState("0");
        } else {
            removeFriend(member);
            member.setState(null);
        }
    }
    public void removeFriend(Member member){

        viewModel.removeFriend(manager.getUserId(),member.getUserId()).observe(this, response -> {
            showSnack(response,"İlişki bitirildi.");
        });

    }

    public void sendRequest(Member member) {
        String userName = manager.getName();
        String userId = manager.getUserId();
        viewModel.sendRequest(userName, userId, member.getUserId()).observe(this, response -> {
            showSnack(response,"İstek gönderildi.");
        });
    }

    public void showSnack(boolean response,String message){
        if (response) {
            Snack.onSuccess(binding.container, message);
        } else {
            Snack.onFailed(binding.container, "Bir hata oluştu.Daha sonra tekrar deneyin.");
        }
    }
}
