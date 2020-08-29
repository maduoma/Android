package com.dodemy.emojimaster;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The fragment used to host the list of faces.
 */
public class FacesFragment extends Fragment {

    // Local variables
    private ArrayList<String> data = new ArrayList<>();
    private GridAdapter gridAdapter;
    private String[] dataToAdd;
    private String selectedText;
    private AutoGridView gridView;
    private View rootView;

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
        rootView = inflater.inflate(R.layout.fragment_faces, container, false);
        // Use the spinner to sort faces by category
        Spinner spinner = (Spinner) rootView.findViewById(R.id.faces_spinner);
        String[] options = {"Joy", "Love", "Embarrassment", "Sympathy",
                            "Indifference", "Confusion", "Doubt", "Surprise",
                            "Sadness", "Dissatisfaction", "Anger", "Pain", "Fear"};
        // Use an adapter to store the list of categories in the spinner
        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, options);
        // Attach the adapter
        spinner.setAdapter(optionsAdapter);
        // Set the listener for when a category is selected
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position) {
                    case 0:
                        generateJoyListContent();
                        updateGrid(rootView);
                        break;
                    case 1:
                        generateLoveListContent();
                        updateGrid(rootView);
                        break;
                    case 2:
                        generateEmbarrassmentListContent();
                        updateGrid(rootView);
                        break;
                    case 3:
                        generateSympathyListContent();
                        updateGrid(rootView);
                        break;
                    case 4:
                        generateIndifferenceListContent();
                        updateGrid(rootView);
                        break;
                    case 5:
                        generateConfusionListContent();
                        updateGrid(rootView);
                        break;
                    case 6:
                        generateDoubtListContent();
                        updateGrid(rootView);
                        break;
                    case 7:
                        generateSurpriseListContent();
                        updateGrid(rootView);
                        break;
                    case 8:
                        generateSadnessListContent();
                        updateGrid(rootView);
                        break;
                    case 9:
                        generateDissatisfactionListContent();
                        updateGrid(rootView);
                        break;
                    case 10:
                        generateAngerListContent();
                        updateGrid(rootView);
                        break;
                    case 11:
                        generatePainListContent();
                        updateGrid(rootView);
                        break;
                    case 12:
                        generateFearListContent();
                        updateGrid(rootView);
                        break;
                    default:
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        // Initialize the grid view used to host the list of faces
        createGrid(rootView);
        // Show tab contents based on current settings
        applyCurrentSettings();
        // Set the listener to update FacesFragment when leaving SettingsActivity
        ((MainActivity)getActivity()).setFacesSettingsListener(new MainActivity.FacesSettingsListener() {
            @Override
            public void applyNewSettings() {
                // Check if the preference for grid view is enabled
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                Boolean gridviewPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_GRIDVIEW, false);
                if (gridviewPref){ // Grid view enabled
                    int orientation = getResources().getConfiguration().orientation;
                    if (orientation == Configuration.ORIENTATION_PORTRAIT){ // Portrait: set columns to 2
                        gridView.setNumColumns(2);
                    } else { // Landscape: set columns to 3
                        gridView.setNumColumns(3);
                    }
                } else { // Grid view disabled
                    gridView.setNumColumns(1);
                }
                updateGrid(rootView);
            }
        });
        return rootView;
    }

    /**
     * Apply current shared preferences that affect the view of the fragment.
     * Called when the fragment is initialized.
     */
    private void applyCurrentSettings() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Boolean gridviewPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_GRIDVIEW, false);
        if (gridviewPref){
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT){
                gridView.setNumColumns(2);
            } else {
                gridView.setNumColumns(3);
            }
        } else {
            gridView.setNumColumns(1);
        }
    }

    /**
     * Initialize the grid view with an adapter that contains the data of faces.
     * @param rootView The view containing the grid view
     */
    private void createGrid(View rootView) {
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_faces);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Boolean bgDarkPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_BG_DARK, false);
        if (bgDarkPref){
            gridAdapter = new GridAdapter(getActivity(), R.layout.grid_item, data);
        } else {
            gridAdapter = new GridAdapter(getActivity(), R.layout.grid_item_light, data);
        }
        gridView.setAdapter(gridAdapter);
    }

    /**
     * Notify the adapter that the dataset has changed.
     * This will update the grid display on the screen.
     * @param rootView The view containing the grid view
     */
    private void updateGrid(View rootView) {
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_faces);
        ArrayAdapter adapter = ((ArrayAdapter)gridView.getAdapter());
        adapter.notifyDataSetChanged();
    }

    // Functions to update the adapter's data based on the selected category
    private void generateJoyListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.joy);
        } else{
            dataToAdd = getResources().getStringArray(R.array.joy_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateLoveListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.love);
        } else{
            dataToAdd = getResources().getStringArray(R.array.love_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateEmbarrassmentListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.embarrassment);
        } else{
            dataToAdd = getResources().getStringArray(R.array.embarrassment_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateSympathyListContent() {
        dataToAdd = getResources().getStringArray(R.array.sympathy);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateIndifferenceListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.indifference);
        } else{
            dataToAdd = getResources().getStringArray(R.array.indifference_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateConfusionListContent() {
        dataToAdd = getResources().getStringArray(R.array.confusion);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateDoubtListContent() {
        dataToAdd = getResources().getStringArray(R.array.doubt);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateSurpriseListContent() {
        dataToAdd = getResources().getStringArray(R.array.surprise);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateSadnessListContent() {
        dataToAdd = getResources().getStringArray(R.array.sadness);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateDissatisfactionListContent() {
        dataToAdd = getResources().getStringArray(R.array.dissatisfaction);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateAngerListContent() {
        dataToAdd = getResources().getStringArray(R.array.anger);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generatePainListContent() {
        dataToAdd = getResources().getStringArray(R.array.pain);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateFearListContent() {
        dataToAdd = getResources().getStringArray(R.array.fear);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }

    /**
     * The custom adapter that will store the data of faces.
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
                        // Update the list of recently used emoticons
                        if (MainActivity.getRecentUpdateListener() != null){
                            MainActivity.getRecentUpdateListener().update(toCopy);
                        }
                        // Display a toast to the user to indicate the item that was copied
                        Toast toast = Toast.makeText(getActivity(), button.getText() +
                                " \nhas been copied!", Toast.LENGTH_SHORT);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (tv != null) {
                            tv.setGravity(Gravity.CENTER);
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
