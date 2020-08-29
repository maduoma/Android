package com.dodemy.emojimaster;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    // Static variables containing the keys of each preference
    public static final String KEY_PREF_GRIDVIEW = "pref_key_gridview";
    public static final String KEY_PREF_CENTER = "pref_key_center";
    public static final String KEY_PREF_BG_DARK = "pref_key_background_dark";
    public static final String KEY_PREF_CONFIRMATION = "pref_key_confirmation";
    public static final String KEY_PREF_RECENT = "pref_key_recent";
    public static final String KEY_PREF_RECENT_CLEAR = "pref_key_recent_clear";
    public static final String KEY_PREF_CUSTOM_CLEAR = "pref_key_custom_clear";
    public static final String KEY_PREF_SEND_FEEDBACK = "pref_key_send_feedback";

    /**
     * Called when SettingsActivity is created
     * @param savedInstanceState The currently saved state of the program
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean bgDarkPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_BG_DARK, false);
        setTheme(bgDarkPref ? R.style.AppTheme : R.style.AppThemeLight);
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
