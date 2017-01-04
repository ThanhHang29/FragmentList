package com.training.huutho.fragmentstatedemo.fragment;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.training.huutho.fragmentstatedemo.MainActivity;
import com.training.huutho.fragmentstatedemo.R;
import com.training.huutho.fragmentstatedemo.adapter.ListContactAdapter;
import com.training.huutho.fragmentstatedemo.model.ListContactData;

import java.util.ArrayList;
import java.util.Collection;


public class ListContactFragment extends Fragment implements View.OnClickListener {
    private static final String BUNDLE_LIST_CONTACTS = "bundle.list.contacts";
    private Button next;
    private RecyclerView viewContacts;
    private ListContactAdapter adapter;
    private ArrayList<ListContactData> datas;
    private RecyclerView.LayoutManager layoutManager;
    private ContentResolver resolver;

    public static ListContactFragment newInstance() {
        return new ListContactFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData(savedInstanceState);
        initView(view);
        initSetData();
        next.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                ((MainActivity) getActivity()).addFragment(R.id.main_layout, ListChoosedContact.newInstance(adapter.getNewContact()));
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("huutho", "pause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_LIST_CONTACTS, datas);
        Log.e("huutho", "onSaveInstanceState: " + "saveintance fragment");
    }

    private void initSetData() {
        viewContacts.setLayoutManager(layoutManager);
        viewContacts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        viewContacts = (RecyclerView) view.findViewById(R.id.rv_contacts);
        next = (Button) view.findViewById(R.id.btn_next);
    }

    private void initData(Bundle saveInstance) {
        datas = new ArrayList<>();

        if (saveInstance != null) {
            Log.e("huutho", "onViewCreated: " + "restore" );
            datas.addAll((Collection<? extends ListContactData>) saveInstance.getSerializable(BUNDLE_LIST_CONTACTS));
        }else {
            resolver = getActivity().getContentResolver();
            datas.addAll(getContactsData(resolver));
        }

        adapter = new ListContactAdapter(datas);
        layoutManager = new LinearLayoutManager(getActivity());
    }

    public ArrayList<ListContactData> getContactsData(ContentResolver resolver) {
        ArrayList<ListContactData> datas = new ArrayList<>();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String xxx = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            datas.add(new ListContactData(name, number, xxx));
            cursor.moveToNext();
        }
        cursor.close();
        return datas;
    }
}
