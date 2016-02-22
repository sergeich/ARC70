package com.sergeich.autoradioclub.messages.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sergeich.autoradioclub.R;
import com.sergeich.autoradioclub.app.model.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder>
        implements View.OnClickListener {

    public interface OnMessageClickListener {
        void onMessageClick(Message message);
    }

    private List<Message> mMessages = new ArrayList<>();
    private OnMessageClickListener mOnMessageClickListener;

    public MessageListAdapter() {
    }

    public void setMessages(List<Message> messages) {
        mMessages = messages;
        notifyDataSetChanged();
    }

    public void setOnMessageClickListener(OnMessageClickListener l) {
        mOnMessageClickListener = l;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Message message = mMessages.get(position);

        vh.name.setText(message.url);

        vh.itemView.setTag(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.item_message, parent, false);
        ViewHolder vh = new ViewHolder(v);

        vh.itemView.setOnClickListener(this);

        return vh;
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();

        if (mOnMessageClickListener != null && position != null) {
            mOnMessageClickListener.onMessageClick(mMessages.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mMessages != null) ? mMessages.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name)
        TextView name;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}

