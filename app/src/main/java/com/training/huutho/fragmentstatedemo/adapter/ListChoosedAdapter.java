package com.training.huutho.fragmentstatedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.training.huutho.fragmentstatedemo.R;
import com.training.huutho.fragmentstatedemo.model.ListContactData;

import java.util.ArrayList;

/**
 * Created by HuuTho on 1/4/2017.
 */

public class ListChoosedAdapter extends RecyclerView.Adapter<ListChoosedAdapter.ViewHolder> {
    private ArrayList<ListContactData> datas;

    public interface RecyclerViewItemClick{
        void onItemClick(View view, int postion);
    }

    private RecyclerViewItemClick itemClick ;
    public ListChoosedAdapter(ArrayList<ListContactData> datas, RecyclerViewItemClick itemClick) {
        this.datas = datas;
        this.itemClick = itemClick ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_choosed_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (datas == null || datas.size() == 0) {
            return 0;
        }
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

}
