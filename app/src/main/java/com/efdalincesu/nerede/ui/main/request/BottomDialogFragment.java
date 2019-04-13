package com.efdalincesu.nerede.ui.main.request;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efdalincesu.nerede.R;
import com.efdalincesu.nerede.databinding.FragmentRequestBinding;
import com.efdalincesu.nerede.util.RelationFragmentRefreshListener;
import com.efdalincesu.nerede.util.SessionManager;
import com.efdalincesu.nerede.util.Snack;

public class BottomDialogFragment extends BottomSheetDialogFragment implements RequestAdapter.RequestListener {


    FragmentRequestBinding binding;
    RequestViewModel viewModel;
    SessionManager manager;
    RequestAdapter adapter;
    RelationFragmentRefreshListener listener;

    public static BottomSheetDialogFragment getInstance() {
        return new BottomDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(RequestViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_request, container, false);
        manager = new SessionManager(getContext());
        adapter = new RequestAdapter(R.layout.main_request_list_item, this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getRequest();

    }

    public void getRequest() {
        viewModel.getFriendRequest(manager.getUserId()).observe(this, userList -> {
            boolean isShow = userList.size() > 0;
            binding.setIsShow(isShow);
            if (isShow) {
                binding.requestRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.requestRecycler.setHasFixedSize(true);
                adapter.submitList(userList);
                binding.requestRecycler.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= (RelationFragmentRefreshListener) getActivity();
    }

    @Override
    public void onAccept(String parentId) {
        viewModel.acceptFriendRequest(parentId, manager.getUserId()).observe(this::getLifecycle, response -> {
            if (response)
                Snack.onSuccess(binding.coordinatorLayout, "Kabul Edildi.");
            else
                Snack.onFailed(binding.coordinatorLayout, "Hata");
        });
        listener.onRelationRefresh();
    }

    @Override
    public void onReject(String parentId) {
        viewModel.rejectFriendRequest(parentId, manager.getUserId()).observe(this::getLifecycle, response -> {
            if (response) {
                Snack.onFailed(binding.coordinatorLayout, "Reddedildi.");
            }
        });
    }


}