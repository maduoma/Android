package com.dodemy.emojimaster;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The fragment used to display the home message to the user
 * and host the list of recently used emoticons.
 */
public class HomeFragment extends Fragment {

    // Local variables
    private ArrayList<String> data = new ArrayList<>();
    private GridAdapter gridAdapter;
    private Spinner spinner;
    private String selectedText;
    private View rootView;
    private AutoGridView gridView;
    private TextView textView;

    /**
     * Called when the fragment needs to be created.
     * This is called when the app starts and whenever the fragment is
     * discarded and rebuilt at a later time.
     * @param inflater Used to inflate the layout of the fragment
     * @param container The container to inflate the layout in
     * @param savedInstanceState The currently saved state of the program
     * @return The newly created fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view to display it and gain access to views and widgets within the view
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Use the spinner to label the home tab and make it clear that the
        // list is recently used emoticons.
        // TODO: Find a better way to do this
        spinner = (Spinner) rootView.findViewById(R.id.faces_spinner);
        String[] label = new String[]{"Recently used"};
        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, label);
        spinner.setEnabled(false);
        spinner.setClickable(false);
        spinner.setAdapter(optionsAdapter);
        // Initialize the grid view used to host the list of recently used emoticons
        createGrid(rootView);
        // Get the text view used to display a message to the user
        textView = (TextView) rootView.findViewById(R.id.home_textview);
        // Show tab content based on current settings and number of recently used emoticons
        updateTabContent();
        // Set the listener to update the list of recently used emoticons
        ((MainActivity)getActivity()).setRecentUpdateListener(new MainActivity.RecentUpdateListener() {
            @Override
            public void update(String recentlyUsed) {
                // Save the copied emoticon and update the grid adapter's data
                if (getActivity() != null){
                    saveToRecent(recentlyUsed);
                    updateTabContent();
                }
            }
        });

        // Set the listener to update HomeFragment when exiting SettingActivity
        ((MainActivity)getActivity()).setHomeSettingsListener(new MainActivity.HomeSettingsListener() {
            @Override
            public void applyNewSettings() {
                updateTabContent();
            }
        });
        return rootView;
    }

    /**
     * Called when the content in HomeFragment needs to be updated
     */
    private void updateTabContent() {
        // Check if the preference for recently used emoticons is enabled
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Boolean recentPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_RECENT, false);
        if (recentPref){ // Recently used emoticons enabled
            if (fileIsEmpty("recent.txt")){ // Show message to tell user that there are no recently used emoticons.
                // Clear the grid adapter's data and update the display
                data.clear();
                updateGrid(rootView);
                // Remove the label
                spinner.setVisibility(View.GONE);
                // Show the message
                String message = getResources().getString(R.string.no_recent_message);
                SpannableString editedMessage = new SpannableString(message);
                editedMessage.setSpan(new RelativeSizeSpan(1.2f), 0, 37, 0);
                editedMessage.setSpan(new RelativeSizeSpan(2.5f), 39, 46, 0);
                editedMessage.setSpan(new ForegroundColorSpan(Color.GRAY), 49, 151, 0);
                textView.setText(editedMessage);
                textView.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
            } else { // Display recently used emoticons
                spinner.setVisibility(View.VISIBLE);
                // Update the grid adapter with new data
                readRecent();
                // Check if the preference for grid view is enabled
                Boolean gridviewPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_GRIDVIEW, false);
                if (gridviewPref){ // Grid view enabled
                    // Check the current orientation of the phone
                    int orientation = getResources().getConfiguration().orientation;
                    if (orientation == Configuration.ORIENTATION_PORTRAIT){ // Portrait: show 2 columns
                        gridView.setNumColumns(2);
                    } else { // Landscape: show 3 columns
                        gridView.setNumColumns(3);
                    }
                } else { // Grid view disabled: show 1 column
                    gridView.setNumColumns(1);
                }
                // Update the grid display
                updateGrid(rootView);
                // Remove the message
                textView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
            }
        } else { // Recently used emoticons disabled
            // Clear the grid adapter's data and update the display
            data.clear();
            updateGrid(rootView);
            // Remove the label
            spinner.setVisibility(View.GONE);
            // Show the starting screen message
            String message;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                message = getResources().getString(R.string.home_message);
            } else{
                message = getResources().getString(R.string.home_message_cp);
            }
            SpannableString editedMessage = new SpannableString(message);
            editedMessage.setSpan(new RelativeSizeSpan(1.2f), 0, 39, 0);
            editedMessage.setSpan(new RelativeSizeSpan(2.5f), 41, 52, 0);
            editedMessage.setSpan(new ForegroundColorSpan(Color.GRAY), 55, 177, 0);
            textView.setText(editedMessage);
            textView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }
    }

    /**
     * Initialize the grid view with an adapter that contains the data of recently used emotiocns.
     * @param rootView The view containing the grid view
     */
    private void createGrid(View rootView) {
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_recent);
        // Check if the preference for dark theme is enabled
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Boolean bgDarkPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_BG_DARK, false);
        if (bgDarkPref){ // Dark theme enabled
            gridAdapter = new GridAdapter(getActivity(), R.layout.grid_item, data);
        } else { // Dark theme disabled
            gridAdapter = new GridAdapter(getActivity(), R.layout.grid_item_light, data);
        }
        // Attach the adapter
        gridView.setAdapter(gridAdapter);
    }

    /**
     * Notify the adapter that the dataset has changed.
     * This will update the grid display on the screen.
     * @param rootView The view containing the grid view
     */
    private void updateGrid(View rootView) {
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_recent);
        ArrayAdapter adapter = ((ArrayAdapter)gridView.getAdapter());
        adapter.notifyDataSetChanged();
    }

    /**
     * Helper function to check if the file is empty
     * @param file The name of the file
     * @return true if empty, false otherwise
     */
    private boolean fileIsEmpty(String file) {
        try {
            InputStream inputStream = getActivity().openFileInput(file);
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                if(reader.readLine() == null) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {}
        return true;
    }

    /**
     * Saves the given text to the internal file that stores recently used emoticons.
     * @param textToAdd The text to add to the internal file
     */
    public void saveToRecent(String textToAdd) {
        // Get system-independent newline
        String ls = System.getProperty("line.separator");
        try {
            // Open recent.txt for appending
            OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput("recent.txt", Context.MODE_APPEND));
            // Necessary to maintain multi-line strings
            textToAdd = textToAdd.replaceAll("[\r\n]+", "ø");
            // Write to file
            writer.write(textToAdd + ls);
            writer.close();
            // Update the number of recently used emoticons
            MainActivity.numRecent++;
        } catch (Exception e) {
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Reads the internal file that stores recently used emoticons to update the adapter's data.
     */
    private void readRecent() {
        String line;
        String ls = System.getProperty("line.separator");
        try {
            // Open recent.txt for reading
            InputStream inputStream = getActivity().openFileInput("recent.txt");
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                // Clear the adapter's data to prepare for new data
                data.clear();
                while ((line = reader.readLine()) != null) { // Read entire file line by line
                    line = line.replaceAll("[ø]", ls); // Necessary to maintain multi-line strings
                    data.add(line); // Add the read line to data
                }
                inputStream.close();
                // Reverse the arraylist so that the most recently used emoticons shows first in the list
                Collections.reverse(data);
                // Show only the 12 most recent
                int currentSize = data.size();
                if ( currentSize > 12 ){
                    data.subList(12, currentSize).clear();
                }
                // Update the internal file so it doesn't store too much irrelevant data
                if (MainActivity.numRecent >= 30 && data.size() >= 12) {
                    updateRecent();
                }
            }
        } catch (Exception e) {
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Used to clean up the internal file that stores recently used emoticons.
     */
    private void updateRecent() {
        String ls = System.getProperty("line.separator");
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput("recent.txt", Context.MODE_PRIVATE));
            Collections.reverse(data);
            for (int i = 0; i < 12; i++){
                writer.write(data.get(i) + ls);
            }
            Collections.reverse(data);
            writer.close();
            MainActivity.numRecent = 12;
        } catch (Exception e) {
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * The custom adapter that will store the data of recently used emoticons.
     */
    private class GridAdapter extends ArrayAdapter<String> {
        private int layout;
        private GridAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                // Get the view's button
                viewHolder.button = (Button) convertView.findViewById(R.id.grid_item);
                // Set the listener for when the button is tapped
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button button = view.findViewById(R.id.grid_item);
                        // Get the button text
                        String toCopy = button.getText().toString();
                        // Copy the text to the clipboard
                        setClipboard(getActivity(), toCopy);
                        // Display a toast to the user to indicate the item that was copied
                        Toast toast = Toast.makeText(getActivity(), button.getText() +
                                " \nhas been copied!", Toast.LENGTH_SHORT);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (tv != null) {
                            tv.setGravity(Gravity.CENTER); // Center the toast text
                        }
                        toast.show();
                    }
                });
                // Set the listener for when the button is long pressed
                viewHolder.button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Button button = view.findViewById(R.id.grid_item);
                        // Get the button text
                        selectedText = button.getText().toString();
                        // Check if the preference for disabling confirmations is enabled
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        Boolean disableConfirmPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_CONFIRMATION, false);
                        if (disableConfirmPref) { // Disable confirmation pref enabled
                            // Update the list of custom emoticons with the selected emoticon
                            if (MainActivity.getCustomUpdateListener() != null){
                                MainActivity.getCustomUpdateListener().update(selectedText);
                            }
                        } else { // Disable confirmation pref disabled
                            // Open the prompt to add the selected emoticon to custom
                            openAddCustomPrompt();
                        }
                        return true;
                    }
                });
                convertView.setTag(viewHolder);
            }
            mainViewHolder = (ViewHolder) convertView.getTag();
            // Check if the preference for centering emoticons is enabled
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Boolean centerPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_CENTER, false);
            if (centerPref){ // Center pref enabled
                mainViewHolder.button.setGravity(Gravity.CENTER); // Center the text
            } else { // Center pref disabled
                mainViewHolder.button.setGravity(Gravity.START); // Left-justify the text
            }
            mainViewHolder.button.setTransformationMethod(null); // Stop text auto-capitalization
            mainViewHolder.button.setText(getItem(position)); // Set the button text
            mainViewHolder.button.setTag(position); // Save the position of the button

            return convertView;
        }

        private class ViewHolder {
            Button button;
        }

        /**
         * Saves the text to the clipboard.
         * @param context The context where this function was called
         * @param text The text to be copied to the clipboard
         */
        private void setClipboard(Context context, String text) {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(text);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                clipboard.setPrimaryClip(clip);
            }
        }

        /**
         * Opens the prompt to add the selected text to the custom list.
         */
        private void openAddCustomPrompt() {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            final View promptView = inflater.inflate(R.layout.add_custom_prompt, null);

            TextView tv = (TextView) promptView.findViewById(R.id.add_custom_prompt_subheader);
            tv.setText(selectedText);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setView(promptView);

            // Add functionality to the OK button
            alertDialogBuilder.setPositiveButton("Add",
                    new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            if (MainActivity.getCustomUpdateListener() != null){
                                MainActivity.getCustomUpdateListener().update(selectedText);
                            }
                        }
                    });

            // Add functionality to the Cancel button
            alertDialogBuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            alertDialog.show();
        }
    }
}
