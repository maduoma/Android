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
 * The fragment used to host the list of other.
 */
public class OtherFragment extends Fragment {

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
     * @param inflater USed to inflate the layout of the activity
     * @param container The container to inflate the layout in
     * @param savedInstanceState The currently saved state of the program
     * @return The newly created fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view to display it and gain access to views and widgets within the view
        rootView = inflater.inflate(R.layout.fragment_other, container, false);
        // Use the spinner to sort other by category
        Spinner spinner = (Spinner) rootView.findViewById(R.id.other_spinner);
        String[] options = {"Greeting", "Hugging", "Winking", "Apologizing",
                            "Hiding", "Writing", "Running", "Sleeping",
                            "Friendship", "Music",
                            "Flipping tables", "Memes", "Animals"};
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
                        generateGreetingListContent();
                        updateGrid(rootView);
                        break;
                    case 1:
                        generateHuggingListContent();
                        updateGrid(rootView);
                        break;
                    case 2:
                        generateWinkingListContent();
                        updateGrid(rootView);
                        break;
                    case 3:
                        generateApologizingListContent();
                        updateGrid(rootView);
                        break;
                    case 4:
                        generateHidingListContent();
                        updateGrid(rootView);
                        break;
                    case 5:
                        generateWritingListContent();
                        updateGrid(rootView);
                        break;
                    case 6:
                        generateRunningListContent();
                        updateGrid(rootView);
                        break;
                    case 7:
                        generateSleepingListContent();
                        updateGrid(rootView);
                        break;
                    case 8:
                        generateFriendshipListContent();
                        updateGrid(rootView);
                        break;
                    case 9:
                        generateMusicListContent();
                        updateGrid(rootView);
                        break;
                    case 10:
                        generateTableFlipListContent();
                        updateGrid(rootView);
                        break;
                    case 11:
                        generateMemeListContent();
                        updateGrid(rootView);
                        break;
                    case 12:
                        generateAnimalListContent();
                        updateGrid(rootView);
                        break;
                    default:
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        // Initialize the grid view used to host the list of other
        createGrid(rootView);
        // Show tab contents based on current settings
        applyCurrentSettings();
        // Set the listener to update OtherFragment when leaving SettingsActivity
        ((MainActivity)getActivity()).setOtherSettingsListener(new MainActivity.OtherSettingsListener() {
            @Override
            public void applyNewSettings() {
                // Check if the preference for gridview is enabled
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
     * Initialize the grid view with an adapter that contains the data of other.
     * @param rootView The view containing the grid view
     */
    private void createGrid(View rootView) {
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_other);
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
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_other);
        ArrayAdapter adapter = ((ArrayAdapter)gridView.getAdapter());
        adapter.notifyDataSetChanged();
    }

    // Functions to update the adapter's data based on the selected category
    private void generateGreetingListContent() {
        dataToAdd = getResources().getStringArray(R.array.greeting);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateHuggingListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.hugging);
        } else{
            dataToAdd = getResources().getStringArray(R.array.hugging_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateWinkingListContent() {
        dataToAdd = getResources().getStringArray(R.array.winking);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateApologizingListContent() {
        dataToAdd = getResources().getStringArray(R.array.apologizing);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateHidingListContent() {
        dataToAdd = getResources().getStringArray(R.array.hiding);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateWritingListContent() {
        dataToAdd = getResources().getStringArray(R.array.writing);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateRunningListContent() {
        dataToAdd = getResources().getStringArray(R.array.running);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateSleepingListContent() {
        dataToAdd = getResources().getStringArray(R.array.sleeping);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateFriendshipListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.friendship);
        } else{
            dataToAdd = getResources().getStringArray(R.array.friendship_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateMusicListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.music);
        } else{
            dataToAdd = getResources().getStringArray(R.array.music_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateTableFlipListContent() {
        dataToAdd = getResources().getStringArray(R.array.table_flipping);
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateMemeListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.memes);
        } else{
            dataToAdd = getResources().getStringArray(R.array.memes_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }
    private void generateAnimalListContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            dataToAdd = getResources().getStringArray(R.array.animals);
        } else{
            dataToAdd = getResources().getStringArray(R.array.animals_cp);
        }
        data.clear();
        Collections.addAll(data, dataToAdd);
    }

    /**
     * The custom adapter that will store the data of other.
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
                viewHolder.button = (Button) convertView.findViewById(R.id.grid_item);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button button = view.findViewById(R.id.grid_item);
                        String toCopy = button.getText().toString();
                        setClipboard(getActivity(), toCopy);
                        if (MainActivity.getRecentUpdateListener() != null){
                            MainActivity.getRecentUpdateListener().update(toCopy);
                        }
                        Toast toast = Toast.makeText(getActivity(), button.getText() +
                                " \nhas been copied!", Toast.LENGTH_SHORT);
                        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (tv != null) {
                            tv.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                    }
                });
                viewHolder.button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Button button = view.findViewById(R.id.grid_item);
                        selectedText = button.getText().toString();
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        Boolean disableConfirmPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_CONFIRMATION, false);
                        if (disableConfirmPref) {
                            if (MainActivity.getCustomUpdateListener() != null){
                                MainActivity.getCustomUpdateListener().update(selectedText);
                            }
                        } else {
                            openAddCustomPrompt();
                        }
                        return true;
                    }
                });
                convertView.setTag(viewHolder);
            }
            mainViewHolder = (ViewHolder) convertView.getTag();
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Boolean centerPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_CENTER, false);
            if (centerPref){
                mainViewHolder.button.setGravity(Gravity.CENTER);
            } else {
                mainViewHolder.button.setGravity(Gravity.START);
            }
            mainViewHolder.button.setTransformationMethod(null);
            mainViewHolder.button.setText(getItem(position));
            mainViewHolder.button.setTag(position);

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
