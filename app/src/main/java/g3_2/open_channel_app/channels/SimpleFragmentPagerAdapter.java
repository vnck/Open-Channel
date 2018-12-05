package g3_2.open_channel_app.channels;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import g3_2.open_channel_app.MyChannelFragment;
import g3_2.open_channel_app.R;
import g3_2.open_channel_app.channels.survey.SubFragmentActions;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] {"tab1", "tab2", "tab3"} ;
    private Context mContext;
    private Bundle details;
    private String description;
    private int tabCount = tabTitles.length;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm,Bundle details){
        super(fm);
        this.mContext = context;
        this.details = details;
        this.description = (String) details.get("description");
    }


    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SubFragmentDescription.newInstance(description);
        } else if (position == 1){
            return new SubFragmentDocuments();
        } else {
            return new SubFragmentActions();
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