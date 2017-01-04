package com.training.huutho.fragmentstatedemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.training.huutho.fragmentstatedemo.R;
import com.training.huutho.fragmentstatedemo.model.ListContactData;


public class DetailContact extends Fragment {
    public static final String BUNDLE_DETAIL_CONTACTS = "bundle.detail.contacts";
    private TextView id,name,phone;
    private ListContactData data ;

    public static DetailContact newInstance(ListContactData data) {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DETAIL_CONTACTS,data);
        DetailContact fragment = new DetailContact();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDataBundle(savedInstanceState);

        id = (TextView) view.findViewById(R.id.tv_id);
        name = (TextView) view.findViewById(R.id.tv_name);
        phone = (TextView) view.findViewById(R.id.tv_phone);

        id.setText(data.getId());
        name.setText(data.getName());
        phone.setText(data.getPhone());
    }

    private void getDataBundle(Bundle savedInstanceState){

        if(savedInstanceState == null){
            Log.v("huutho","null");
        }

        Bundle bundle = getArguments();
        if (bundle !=null){
            data = (ListContactData) bundle.getSerializable(BUNDLE_DETAIL_CONTACTS);
        }else {
            data = new ListContactData("default","default","default");
        }
    }
}
