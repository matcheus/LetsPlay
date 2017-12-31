package com.xpmets.letsplay.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpmets.letsplay.Controller.AmigosPageAdapter;
import com.xpmets.letsplay.R;

public class Amigos extends Fragment {

    private View view;
    FragmentPagerAdapter adapterViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.fragment_viewpager_amigos, null);
        ViewPager pager = view.findViewById(R.id.pager);
        adapterViewPager = new AmigosPageAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapterViewPager);

        TabLayout tabAmigos = view.findViewById(R.id.tab_amigos);
        tabAmigos.setupWithViewPager(pager);
        return view;
    }
}
