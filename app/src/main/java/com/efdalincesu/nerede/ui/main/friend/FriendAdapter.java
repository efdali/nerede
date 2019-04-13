package com.efdalincesu.nerede.ui.main.friend;

import com.efdalincesu.nerede.common.BaseAdapter;
import com.efdalincesu.nerede.databinding.MainFriendsListItemBinding;
import com.efdalincesu.nerede.model.Member;

public class FriendAdapter extends BaseAdapter<MainFriendsListItemBinding, Member> {
    LocationRequestListener listener;
    int type;
    public FriendAdapter(int layoutRes, LocationRequestListener listener, int type) {
        super(layoutRes);
        this.listener=listener;
        this.type=type;
    }

    @Override
    public void onBind(int pos) {
        Member user=list.get(pos);
        binding.setUser(user);
        binding.setIsChild(type==1);
        binding.executePendingBindings();

        binding.locationBtn.setOnClickListener(v -> {
            listener.onLocationRequest(user.getUserId());
        });

        binding.rejectBtn.setOnClickListener(v->{
            list.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, list.size());
            listener.onReject(user.getUserId());
        });

    }

    interface LocationRequestListener{
        void onLocationRequest(String memberId);

        void onReject(String memberId);
    }

}
