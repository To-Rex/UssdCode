package com.ussd.ussdcode.adapters;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.ussd.ussdcode.Fragments.FragmentInternetToplamlar;

public class InternetToplamAdapter extends FragmentStateAdapter {
    private String[] data;

    public InternetToplamAdapter(@NonNull FragmentActivity fragmentActivity,String[] data) {
        super(fragmentActivity);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new FragmentInternetToplamlar();
        Bundle bundle = new Bundle();
        bundle.putString("key", data[position]);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
