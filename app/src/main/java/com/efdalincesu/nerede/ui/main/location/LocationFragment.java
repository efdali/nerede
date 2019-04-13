package com.efdalincesu.nerede.ui.main.location;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efdalincesu.nerede.R;
import com.efdalincesu.nerede.databinding.FragmentLocationBinding;
import com.efdalincesu.nerede.util.SessionManager;

public class LocationFragment extends Fragment{

    FragmentLocationBinding binding;
    LocationViewModel viewModel;
    SessionManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(LocationViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_location, container, false);
        manager = new SessionManager(getContext());

        binding.requestSwipe.setOnRefreshListener(() -> {

            binding.requestSwipe.setRefreshing(false);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



}
