package Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.Locale;

import Fragments.DataSectionFragment;
import Fragments.DummySectionFragment;
import Fragments.HRVSectionFragment;
import Fragments.SPO2SectionFragment;
import pllab.tcmobile.R;

/**
 * Created by charlie on 2017/10/25.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    // END_INCLUDE (fragment_pager_adapter)
    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context nContext) {
        super(fm);
        context = nContext;
    }

    // BEGIN_INCLUDE (fragment_pager_adapter_getitem)
    /**
     * Get fragment corresponding to a specific position. This will be used to populate the
     * contents of the {@link ViewPager}.
     *
     * @param position Position to fetch fragment for.
     * @return Fragment for specified position.
     */
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        Fragment fragment = new DataSectionFragment();
        Bundle args = new Bundle();
        switch (position){
            case 0:
                fragment = new HRVSectionFragment(context);
                break;
            case 1:
                fragment = new SPO2SectionFragment(context);
                break;
            case 2:
                fragment = new DummySectionFragment();
                break;
            case 3:
                fragment = new DummySectionFragment();
                break;
        }
        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);
        return fragment;
    }
    // END_INCLUDE (fragment_pager_adapter_getitem)

    // BEGIN_INCLUDE (fragment_pager_adapter_getcount)
    /**
     * Get number of pages the {@link ViewPager} should render.
     *
     * @return Number of fragments to be rendered as pages.
     */
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }
    // END_INCLUDE (fragment_pager_adapter_getcount)

    // BEGIN_INCLUDE (fragment_pager_adapter_getpagetitle)
    /**
     * Get title for each of the pages. This will be displayed on each of the tabs.
     *
     * @param position Page to fetch title for.
     * @return Title for specified page.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.title_section1);
            case 1:
                return context.getString(R.string.title_section2);
            case 2:
                return context.getString(R.string.title_section3);
            case 3:
                return context.getString(R.string.title_section4);
        }
        return null;
    }
    // END_INCLUDE (fragment_pager_adapter_getpagetitle)
}
