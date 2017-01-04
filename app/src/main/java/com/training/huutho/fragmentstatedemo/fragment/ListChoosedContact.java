package com.training.huutho.fragmentstatedemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.huutho.fragmentstatedemo.MainActivity;
import com.training.huutho.fragmentstatedemo.R;
import com.training.huutho.fragmentstatedemo.adapter.ListChoosedAdapter;
import com.training.huutho.fragmentstatedemo.model.ListContactData;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListChoosedContact extends Fragment implements ListChoosedAdapter.RecyclerViewItemClick {
    private static final String BUNDLE_CONTACTS = "bundle.contact";
    private RecyclerView viewContacts;
    private ListChoosedAdapter adapter;
    private ArrayList<ListContactData> datas;

    public static ListChoosedContact newInstance(ArrayList<ListContactData> datas) {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_CONTACTS, datas);
        ListChoosedContact fragment = new ListChoosedContact();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_choosed_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewContacts = (RecyclerView) view.findViewById(R.id.rv_choosed);

        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        datas.addAll((Collection<? extends ListContactData>) bundle.getSerializable(BUNDLE_CONTACTS));
        adapter = new ListChoosedAdapter(datas,this);

        viewContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewContacts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int postion) {
        ((MainActivity)getActivity()).addFragment(R.id.main_layout,DetailContact.newInstance(datas.get(postion)));
    }
}
