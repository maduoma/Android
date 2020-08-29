package com.dodemy.emojimaster;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link } that will provide
     * fragments for each of the sections.
     * It uses a {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private final Context context = this;

    /**
     * Static variables used by fragments to reference this activity
     */
    public static MainActivity mActivity;
    public static int numRecent = 0;

    /**
     * Called when the app starts.
     * This should only ever be called once in a single lifespan of the app.
     * @param savedInstanceState The currently saved state of the program
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initializing internal files and static variables upon startup
        initializeRecent();
        countRecentEntries();
        mActivity = this;

        // Apply the proper app theme based on the current preference setting
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean bgDarkPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_BG_DARK, false);
        setTheme(bgDarkPref ? R.style.AppThemeNoActionBar : R.style.AppThemeLightNoActionBar);

        // Build the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();

        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(0);  // clear all scroll flags
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the four
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // Set up floating action button to add custom emoticons.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddPrompt();
            }
        });
    }

    /**
     * Called when the activity is resumed (i.e. when leaving the settings activity)
     */
    @Override
    public void onResume() {
        super.onResume();
        // Apply the new settings to each fragment
        if (getHomeSettingsListener() != null) {
            getHomeSettingsListener().applyNewSettings();
        }
        if (getFacesSettingsListener()!= null){
            getFacesSettingsListener().applyNewSettings();
        }
        if (getOtherSettingsListener() != null) {
            getOtherSettingsListener().applyNewSettings();
        }
        if (getCustomSettingsListener() != null) {
            getCustomSettingsListener().applyNewSettings();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get the id of the pressed item
        int id = item.getItemId();

        // Do something depending on the item that was pressed
        if (id == R.id.action_settings) { // User pressed settings
            // Start the settings activity
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Returns the current tab
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new FacesFragment();
                case 2:
                    return new OtherFragment();
                case 3:
                    return new CustomFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public String getPageTitle(int position) {
            // Returns the title of the current tab
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "FACES";
                case 2:
                    return "CLASSICS";
                case 3:
                    return "CUSTOM";
                default:
                    return null;
            }
        }
    }

    /**
     * Opens a prompt to add a user-inputed emoticon to the custom fragment.
     * Called when the user presses the floating action button.
     */
    private void openAddPrompt() {
        // Build the dialog box and use add_prompt as the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        final View promptView = inflater.inflate(R.layout.add_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        // Add functionality to the add button
        alertDialogBuilder.setPositiveButton("Add",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // The text entered by the user will be added to the custom fragment
                        Dialog dialogView = (Dialog) dialog;
                        EditText userInput = (EditText) dialogView.findViewById(R.id.add_user_input);
                        String textToAdd = userInput.getText().toString();
                        // Go directly to the custom fragment to update
                        if (getCustomUpdateListener() != null){
                            getCustomUpdateListener().update(textToAdd);
                        }
                    }
                });

        // Add functionality to the cancel button
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // Always show the soft keyboard when the dialog is created
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        alertDialog.show();
    }

    // Listener used to update the list of custom emoticons.
    // Used when preset emoticons are added to custom.
    public static CustomUpdateListener customUpdateListener;

    public interface CustomUpdateListener {
        void update(String toAdd);
    }

    public static CustomUpdateListener getCustomUpdateListener() {
        return customUpdateListener;
    }

    public static void setCustomUpdateListener(CustomUpdateListener newListener) {
        customUpdateListener = newListener;
    }

    // Listener used to update the list of recently used emoticons.
    // Used when an emoticon is copied to the clipboard via button tap.
    public static RecentUpdateListener recentUpdateListener;

    public interface RecentUpdateListener {
        void update(String recentlyUsed);
    }

    public static RecentUpdateListener getRecentUpdateListener() {
        return recentUpdateListener;
    }

    public static void setRecentUpdateListener(RecentUpdateListener newListener) {
        recentUpdateListener = newListener;
    }

    // Listener used to update HomeFragment when exiting SettingsActivity.
    private HomeSettingsListener homeSettingsListener;

    public interface HomeSettingsListener {
        void applyNewSettings();
    }

    public HomeSettingsListener getHomeSettingsListener() {
        return homeSettingsListener;
    }

    public void setHomeSettingsListener(HomeSettingsListener homeSettingsListener) {
        this.homeSettingsListener = homeSettingsListener;
    }

    // Listener used to update FacesFragment when exiting SettingsActivity.
    private FacesSettingsListener facesSettingsListener;

    public interface FacesSettingsListener {
        void applyNewSettings();
    }

    public FacesSettingsListener getFacesSettingsListener() {
        return facesSettingsListener;
    }

    public void setFacesSettingsListener(FacesSettingsListener facesSettingsListener) {
        this.facesSettingsListener = facesSettingsListener;
    }

    // Listener used to update OtherFragment when exiting SettingsActivity.
    private OtherSettingsListener otherSettingsListener;

    public interface OtherSettingsListener {
        void applyNewSettings();
    }

    public OtherSettingsListener getOtherSettingsListener() {
        return otherSettingsListener;
    }

    public void setOtherSettingsListener(OtherSettingsListener otherSettingsListener) {
        this.otherSettingsListener = otherSettingsListener;
    }

    // Listener used to update CustomFragment when exiting SettingsActivity.
    private CustomSettingsListener customSettingsListener;

    public interface CustomSettingsListener {
        void applyNewSettings();
    }

    public CustomSettingsListener getCustomSettingsListener() {
        return customSettingsListener;
    }

    public void setCustomSettingsListener(CustomSettingsListener customSettingsListener) {
        this.customSettingsListener = customSettingsListener;
    }

    private void initializeRecent() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(this.openFileOutput("recent.txt", Context.MODE_APPEND));
            writer.close();
        } catch (Exception e) {
            Toast.makeText(this, "Exception: "+e.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void countRecentEntries() {
        String line;
        try {
            InputStream inputStream = this.openFileInput("recent.txt");
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                while ((line = reader.readLine()) != null) {
                    numRecent++;
                }
                inputStream.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Exception: "+e.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }
}
