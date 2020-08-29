package com.dodemy.emojimaster;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

import java.io.OutputStreamWriter;

/**
 * A fragment of SettingsActivity
 */
public class SettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    /**
     * Called when SettingsFragment is created
     * @param savedInstanceState The currently saved state of the program
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        Preference recentClearPref = (Preference) findPreference(SettingsActivity.KEY_PREF_RECENT_CLEAR);
        recentClearPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                openRecentClearPrompt();
                return true;
            }
        });
        Preference customClearPref = (Preference) findPreference(SettingsActivity.KEY_PREF_CUSTOM_CLEAR);
        customClearPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                openCustomClearPrompt();
                return true;
            }
        });
        Preference sendFeedbackPref = (Preference) findPreference(SettingsActivity.KEY_PREF_SEND_FEEDBACK);
        sendFeedbackPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                composeEmail();
                return true;
            }
        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            Preference darkThemePref = (Preference) findPreference(SettingsActivity.KEY_PREF_BG_DARK);
            darkThemePref.setSummary(getResources().getString(R.string.pref_background_dark_summ_cp));
        }
    }

    /**
     * Called when a settings preference is changed
     * @param sharedPref The shared preference values
     * @param key The preference that was changed
     */
    public void onSharedPreferenceChanged(SharedPreferences sharedPref,
                                          String key) {
        if (key.equals(SettingsActivity.KEY_PREF_BG_DARK)){
            MainActivity.mActivity.recreate();
            getActivity().recreate();
        }
    }


    /**
     * Called when the activity is resumed (i.e. when entering the settings activity)
     */
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * Called when the activity is paused (i.e. when leaving the settings activity)
     */
    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Opens the prompt to clear the list of recent emoticons.
     */
    private void openRecentClearPrompt() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View promptView = inflater.inflate(R.layout.recent_clear_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        clearRecent();
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Clears the internal file that holds the recently used emoticons.
     */
    private void clearRecent() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput("recent.txt", Context.MODE_PRIVATE));
            writer.write("");
            writer.close();
            MainActivity.numRecent = 0;
            Snackbar snackbar = Snackbar.make(getView(), "Recently used emoticons have been reset.", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Opens the prompt to clear the list of custom emoticons.
     */
    private void openCustomClearPrompt() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View promptView = inflater.inflate(R.layout.custom_clear_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        clearCustom();
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Clears the internal file that holds the custom emoticons.
     */
    private void clearCustom() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput("custom.txt", Context.MODE_PRIVATE));
            writer.write("");
            writer.close();
            Snackbar snackbar = Snackbar.make(getView(), "Custom emoticons have been reset.", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Opens the default email app (if it exists) and sets up the fields to send an email to
     * a recipient (i.e. the developer)
     */
    private void composeEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","a.lone.boar@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Emoji Master Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            if (getView() != null){
                Snackbar snackbar = Snackbar.make(getView(), "No email app is available.", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        }
    }
}
