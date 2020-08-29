package com.dodemy.mvp.View;

import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dodemy.mvp.R;
import com.dodemy.mvp.View.Fragments.ArrayListFragment;
import com.dodemy.mvp.View.Fragments.ArraysFragment;
import com.dodemy.mvp.View.Fragments.BooleanFragment;
import com.dodemy.mvp.View.Fragments.ByteFragment;
import com.dodemy.mvp.View.Fragments.CalendarFragment;
import com.dodemy.mvp.View.Fragments.CharacterFragment;
import com.dodemy.mvp.View.Fragments.DoubleFragment;
import com.dodemy.mvp.View.Fragments.FloatFragment;
import com.dodemy.mvp.View.Fragments.IntegerFragment;
import com.dodemy.mvp.View.Fragments.LongFragment;
import com.dodemy.mvp.View.Fragments.ShortFragment;
import com.dodemy.mvp.View.Fragments.StringBuilderFragment;
import com.dodemy.mvp.View.Fragments.StringsFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements
        ArraysFragment.OnFragmentInteractionListener,
        StringsFragment.OnFragmentInteractionListener,
        IntegerFragment.OnFragmentInteractionListener,
        ArrayListFragment.OnFragmentInteractionListener,
        BooleanFragment.OnFragmentInteractionListener,
        ByteFragment.OnFragmentInteractionListener,
        CalendarFragment.OnFragmentInteractionListener,
        CharacterFragment.OnFragmentInteractionListener,
        DoubleFragment.OnFragmentInteractionListener,
        FloatFragment.OnFragmentInteractionListener,
        LongFragment.OnFragmentInteractionListener,
        ShortFragment.OnFragmentInteractionListener,
        StringBuilderFragment.OnFragmentInteractionListener
{

    /**
     * The {@link //android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link //android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:

                    return new StringsFragment(); //ChildFragment1 at position 0
                case 1:
                    return new IntegerFragment(); //ChildFragment2 at position 1
                case 2:
                    return new ArraysFragment(); //ChildFragment3 at position 2
                case 3:

                    return new StringBuilderFragment(); //ChildFragment1 at position 0
                case 4:
                    return new CalendarFragment(); //ChildFragment2 at position 1
                case 5:
                    return new ShortFragment(); //ChildFragment3 at position 2
                case 6:

                    return new ByteFragment(); //ChildFragment1 at position 0
                case 7:
                    return new BooleanFragment(); //ChildFragment2 at position 1
                case 8:
                    return new LongFragment(); //ChildFragment3 at position 2
                case 9:

                    return new DoubleFragment(); //ChildFragment1 at position 0
                case 10:
                    return new FloatFragment(); //ChildFragment2 at position 1
                case 11:
                    return new CharacterFragment(); //ChildFragment3 at position 2
                case 12:
                    return new ArrayListFragment(); //ChildFragment3 at position 2
            }
            return null; //does not happen

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 13;
        }
    }
}
