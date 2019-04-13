package com.efdalincesu.nerede.ui.search;

import com.efdalincesu.nerede.common.BaseAdapter;
import com.efdalincesu.nerede.databinding.SearchListItemBinding;
import com.efdalincesu.nerede.model.Member;

public class SearchAdapter extends BaseAdapter<SearchListItemBinding, Member> {


    RowClickListener listener;

    public SearchAdapter(int layoutRes, RowClickListener listener) {
        super(layoutRes);
        this.listener = listener;
    }

    @Override
    public void onBind(int pos) {
        binding.setUser(list.get(pos));
        binding.executePendingBindings();

        binding.btnRequest.setOnClickListener(v -> {
            listener.onRowClicked(list.get(pos));
        });
    }

    public interface RowClickListener {
        void onRowClicked(Member user);
    }

}
