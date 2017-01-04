package com.training.huutho.fragmentstatedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.training.huutho.fragmentstatedemo.fragment.ListContactFragment;

public class MainActivity extends AppCompatActivity {
    private ListContactFragment listContactFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            listContactFragment = (ListContactFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, "list.contact.fragment");
        } else {
            listContactFragment = new ListContactFragment();
        }

        replaceFragment(R.id.main_layout, listContactFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "list.contact.fragment", listContactFragment);
    }

    public void replaceFragment(int layoutId, Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(layoutId, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }
    public void addFragment(int layoutId, Fragment fragment){
        String tag = fragment.getClass().getSimpleName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(layoutId, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }
}

