package com.xpmets.letsplay.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xpmets.letsplay.View.ContentAmigos;
import com.xpmets.letsplay.View.ContentPedidoAmizade;

public class AmigosPageAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;

    public AmigosPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ContentAmigos();
            case 1:
                return new ContentPedidoAmizade();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Amigos";
            case 1:
                return "Pedidos de Amizade";
            default:
                return null;
        }
    }
}
