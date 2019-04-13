package com.efdalincesu.nerede.ui.main.friend;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efdalincesu.nerede.R;
import com.efdalincesu.nerede.databinding.FragmentRelationBinding;
import com.efdalincesu.nerede.util.SessionManager;
import com.efdalincesu.nerede.util.Snack;

public class FriendFragment extends Fragment implements FriendAdapter.LocationRequestListener {

    FragmentRelationBinding binding;
    FriendViewModel viewModel;
    SessionManager manager;
    FriendAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(FriendViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_relation, container, false);
        manager = new SessionManager(getContext());
        adapter = new FriendAdapter(R.layout.main_friends_list_item, this,manager.getType());

        binding.relationSwipe.setOnRefreshListener(() -> {
            getFriend();
            binding.relationSwipe.setRefreshing(false);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFriend();

    }

    public void getFriend() {
        binding.relationProgress.setVisibility(View.VISIBLE);
        viewModel.getFriends(manager.getUserId(), manager.getType() + "").observe(this, memberList -> {
            boolean isShow = memberList.size() > 0;
            binding.setIsShow(isShow);
            if (isShow) {
                binding.relationRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.relationRecycler.setHasFixedSize(true);
                adapter.submitList(memberList);
                binding.relationRecycler.setAdapter(adapter);
            }

            binding.relationProgress.setVisibility(View.GONE);
        });
    }

    public void refresh(){
        getFriend();
    }

    @Override
    public void onLocationRequest(String childId) {

        viewModel.sendLocationRequest(manager.getName(), manager.getUserId(), childId).observe(this::getLifecycle, response -> {
            if (response)
                Snack.onSuccess(binding.relationSwipe, "İstek Gönderildi.");
        });


    }

    @Override
    public void onReject(String chilId) {
        viewModel.removeFriend(manager.getUserId(),chilId).observe(this::getLifecycle,isOkey->{
            if (isOkey)
                Snack.onSuccess(binding.relationSwipe,"İlişki Bitirildi.");
            else
                Snack.onFailed(binding.relationSwipe,"Hata Oluştu");
        });
    }
}
