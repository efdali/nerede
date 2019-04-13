package com.efdalincesu.nerede.ui.main.request;

import com.efdalincesu.nerede.common.BaseAdapter;
import com.efdalincesu.nerede.databinding.MainRequestListItemBinding;
import com.efdalincesu.nerede.model.Member;
import com.efdalincesu.nerede.model.User;

public class RequestAdapter extends BaseAdapter<MainRequestListItemBinding, Member> {

    RequestListener listener;

    public RequestAdapter(int layoutRes,RequestListener listener) {
        super(layoutRes);
        this.listener=listener;
    }

    @Override
    public void onBind(int pos) {
        Member user=list.get(pos);
        binding.setUser(user);
        binding.executePendingBindings();

        binding.btnAccept.setOnClickListener(v -> {
            listener.onAccept(user.getUserId());
            list.remove(user);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, list.size());
        });

        binding.btnReject.setOnClickListener(v->{
            listener.onReject(user.getUserId());
            list.remove(user);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, list.size());
        });

    }

    interface RequestListener {
        void onAccept(String parentId);

        void onReject(String parentId);
    }
}
