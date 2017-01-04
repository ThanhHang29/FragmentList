package com.training.huutho.fragmentstatedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.training.huutho.fragmentstatedemo.R;
import com.training.huutho.fragmentstatedemo.model.ListContactData;

import java.util.ArrayList;

/**
 * Created by HuuTho on 1/4/2017.
 */

public class ListContactAdapter extends RecyclerView.Adapter<ListContactAdapter.ViewHolder> {
    private ArrayList<ListContactData> datas;
    private ArrayList<ListContactData> newContact = new ArrayList<>();// không nên new luôn ở đây

//truyền newContact ở contructor
    public ListContactAdapter(ArrayList<ListContactData> datas) {
        this.datas = datas;
        // this.newContact = ....
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        holder.checkBox.setChecked(datas.get(position).isCheck());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                datas.get(position).setCheck(!isChecked);
                if (!datas.get(position).isCheck()){
                    newContact.add(datas.get(position));
                }
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
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            checkBox = (CheckBox) itemView.findViewById(R.id.ckb_choose);
        }
    }
    public ArrayList<ListContactData> getNewContact(){
        return newContact;
    }
}
