package g3_2.open_channel.Channels;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import g3_2.open_channel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] {"tab1", "tab2", "tab3"} ;
    private Context mContext;
    private int tabCount = tabTitles.length;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm){
        super(fm);
        this.mContext = context;
//        this.tabTitles = new String[] {"tab1", "tab2", "tab3"};
    }
//    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm, String[] tabTitles) {
//        super(fm);
//        this.mContext = context;
//        this.tabTitles = tabTitles;
//    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SubFragmentDescription();
        } else if (position == 1){
            return new SubFragmentDocuments();
        } else if (position == 2){
            return new SubFragmentActions();
        } else {
            return new SubFragmentAdmin();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.description);
            case 1:
                return mContext.getString(R.string.documents);
            case 2:
                return mContext.getString(R.string.actions);
            case 3:
                return mContext.getString(R.string.admin);
            default:
                return null;
        }
    }

}