package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.R;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {
    // 1. add switch to change the page and modifty the getItem and getCount method
    private static final int [] TAB_TITLES =
            new int[]{R.string.tab_neighbour_title,R.string.tab_favorites_title};
    private final Context mContext;

    ListNeighbourPagerAdapter(Context context,FragmentManager fm) {

     super(fm);
     mContext = context;
    }

    public ListNeighbourPagerAdapter(FragmentManager supportFragmentManager, Context mContext) {
        super(supportFragmentManager);
        this.mContext = mContext;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NeighbourFragment.newInstance();
            case 1:
                return FavoritesFragment.newInstance();

            default:
                return null;}
        }

        public CharSequence getPageTitle(int position){
        return
                mContext.getResources().getString(TAB_TITLES[position]);
        }

        /**
         * get the number of pages
         * @return
         */

    @Override
    public int getCount() {
        // Show 2 total pager
        return 2;
    }
}