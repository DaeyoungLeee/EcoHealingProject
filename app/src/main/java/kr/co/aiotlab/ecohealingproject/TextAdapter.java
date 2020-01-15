package kr.co.aiotlab.ecohealingproject;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TextAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    private String[] tabTitles;

    TextAdapter(FragmentManager fm, List<Fragment> list, String[] tabTitlesList) {
        super(fm);
        this.fragmentList = list;
        this.tabTitles = tabTitlesList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
