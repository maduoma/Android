package com.dodemy.android.roomwithjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<User> mUserList;

    public UserAdapter(List<User> usersList) {
        mUserList = usersList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mUserList != null && mUserList.size() > 0) {
            return mUserList.size();
        } else {
            return 0;
        }
    }

    public interface Callback {
        void onDeleteClick(User mUser);
    }

    public void addItems(List<User> userList) {
        mUserList = userList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.imageViewDelete)
        ImageView imageViewDelete;

        @BindView(R.id.textViewDisplayName)
        TextView textViewName;

        @BindView(R.id.textViewEmail)
        TextView textViewEmail;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        protected void clear() {
            textViewEmail.setText("");
            textViewName.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final User mUser = mUserList.get(position);


            if (mUser.getFirstName() != null) {
                textViewName.setText(String.format("%s %s", mUser.getFirstName(), mUser.getLastName()));
            }

            if (mUser.getEmail() != null) {
                textViewEmail.setText(mUser.getEmail());
            }
            imageViewDelete.setOnClickListener(v -> mCallback.onDeleteClick(mUser));
        }
    }


}