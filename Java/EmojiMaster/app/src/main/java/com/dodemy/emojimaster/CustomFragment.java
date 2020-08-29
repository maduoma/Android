package com.dodemy.emojimaster;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The fragment used to display the no custom message to the user
 * and host the list of custom emoticons.
 */
public class CustomFragment extends Fragment {

    // Local variables
    private ArrayList<String> data = new ArrayList<>();
    private GridAdapter gridAdapter;
    private String selectedText;
    private int selectedTextPosn;
    private AutoGridView gridView;
    private TextView tv;
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
        rootView = inflater.inflate(R.layout.fragment_custom, container, false);
        // Initialize the grid view used to host the list of custom emoticons
        createGrid(rootView);
        // Get the text view used to display a message to the user
        tv = (TextView) rootView.findViewById(R.id.custom_textview);
        // Initialize the message
        String message = getResources().getString(R.string.no_custom_message);
        SpannableString editedMessage = new SpannableString(message);
        editedMessage.setSpan(new RelativeSizeSpan(1.2f), 0, 38, 0);
        editedMessage.setSpan(new RelativeSizeSpan(1.8f), 40, 57, 0);
        editedMessage.setSpan(new ForegroundColorSpan(Color.GRAY), 60, 171, 0);
        tv.setText(editedMessage);
        // Show message if file is empty
        checkFileContents();
        // Show tab contents based on current settings
        applyCurrentSettings();
        // Set the listener to update the list of custom emoticons
        ((MainActivity)getActivity()).setCustomUpdateListener(new MainActivity.CustomUpdateListener() {
            @Override
            public void update(String toAdd) {
                if (getActivity() != null){
                    // Save the custom emoticon and update the grid adapter's data
                    saveToFile(toAdd);
                    readFile("custom.txt");
                    // Update grid view if necessary
                    applyCurrentSettings();
                    checkFileContents();
                    // Update grid content
                    updateGrid(rootView);
                }
            }
        });
        // Set the listener to update CustomFragment when exiting SettingActivity
        ((MainActivity)getActivity()).setCustomSettingsListener(new MainActivity.CustomSettingsListener() {
            @Override
            public void applyNewSettings() {
                // Update grid view if necessary
                applyCurrentSettings();
                // Check if file is empty
                checkFileContents();
                // Update grid content
                updateGrid(rootView);
            }
        });
        return rootView;
    }

    /**
     * Called when a context menu is created from a list item
     * @param menu The menu container
     * @param view The view where the context menu should be created from
     * @param menuInfo The info of the menu
     */
    @Override
    public void onCreateContextMenu (ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        if (view.getId() == R.id.grid_item){
            selectedText = ((Button)view).getText().toString();
            selectedTextPosn = (Integer)(view.getTag());
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_custom, menu);
        }
    }

    /**
     * Change behaviour based on the context menu item choice
     * @param item The selected menu item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.edit:
                openEditPrompt();
                break;
            case R.id.delete:
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                Boolean disableConfirmPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_CONFIRMATION, false);
                if (disableConfirmPref) {
                    deleteFromFile("custom.txt");
                    readFile("custom.txt");
                    updateGrid(rootView);
                    if (fileIsEmpty("custom.txt")){
                        tv.setVisibility(View.VISIBLE);
                        gridView.setVisibility(View.GONE);
                    } else{
                        tv.setVisibility(View.GONE);
                        gridView.setVisibility(View.VISIBLE);
                    }
                } else {
                    openDeletePrompt();
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Apply current shared preferences that affect the view of the fragment.
     * Called when the fragment is initialized.
     */
    private void applyCurrentSettings() {
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
    }

    /**
     * Checks if the file is empty and updates views accordingly
     */
    private void checkFileContents() {
        if (fileIsEmpty("custom.txt")){
            tv.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
            data.clear();
        } else{
            tv.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            readFile("custom.txt");
        }
    }

    /**
     * Opens the prompt to edit the selected text from the custom list.
     */
    private void openEditPrompt() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View promptView = inflater.inflate(R.layout.edit_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setPositiveButton("Add",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog toSave = (Dialog) dialog;
                        EditText userInput = (EditText) toSave.findViewById(R.id.edit_user_input);
                        String editedText = userInput.getText().toString();
                        editFile("custom.txt", editedText);
                        readFile("custom.txt");
                        updateGrid(rootView);
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
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        alertDialog.show();
    }

    /**
     * Opens the prompt to delete the selected text from the custom list.
     */
    private void openDeletePrompt() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View promptView = inflater.inflate(R.layout.delete_prompt, null);
        TextView tv = (TextView) promptView.findViewById(R.id.delete_prompt_subheader);
        tv.setText(selectedText);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        deleteFromFile("custom.txt");
                        readFile("custom.txt");
                        updateGrid(rootView);
                        TextView tv = (TextView) rootView.findViewById(R.id.custom_textview);
                        if (fileIsEmpty("custom.txt")){
                            tv.setVisibility(View.VISIBLE);
                            gridView.setVisibility(View.GONE);
                        } else{
                            tv.setVisibility(View.GONE);
                            gridView.setVisibility(View.VISIBLE);
                        }
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
     * Helper function to check if the internal file with custom emoticons is empty or not
     * @param file The name of the file to check
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
     * Saves the given string to the internal file with custom emoticons.
     * @param textToAdd The text to add to the internal file
     */
    private void saveToFile(String textToAdd) {
        String originalText = textToAdd;
        String ls = System.getProperty("line.separator");
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput("custom.txt", Context.MODE_APPEND));
            textToAdd = textToAdd.replaceAll("[\r\n]+", "ø");
            writer.write(textToAdd + ls);
            writer.close();
            Toast toast = Toast.makeText(getActivity(), originalText + "\nadded to Custom!", Toast.LENGTH_SHORT);
            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
            if (tv != null) {
                tv.setGravity(Gravity.CENTER);
            }
            toast.show();
        } catch (Exception e) {
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Reads the given file and updates the grid adapter's data
     * @param file The file to read
     */
    private void readFile(String file) {
        String line;
        String ls = System.getProperty("line.separator");
        try {
            InputStream inputStream = getActivity().openFileInput(file);
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                data.clear();
                while ((line = reader.readLine()) != null) {
                    line = line.replaceAll("[ø]", ls);
                    data.add(line);
                }
                inputStream.close();
            }
        } catch (Exception e) {
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Edits an entry in the internal file with custom emoticons
     * @param file The file to edit
     * @param editedText The text that will replace the target text in the file
     */
    private void editFile(String file, String editedText){
        String line;
        String ls = System.getProperty("line.separator");
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput("temp.txt", Context.MODE_PRIVATE));
            InputStream inputStream = getActivity().openFileInput(file);
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                int lineToOverwrite = 0;
                while ((line = reader.readLine()) != null) {
                    if (selectedTextPosn != lineToOverwrite){
                        writer.write(line + ls);
                        lineToOverwrite++;
                    } else {
                        editedText = editedText.replaceAll("[\r\n]+", "ø");
                        writer.write(editedText + ls);
                        lineToOverwrite++;
                    }
                }
                reader.close();
            }
            writer.close();
            OutputStreamWriter newWriter = new OutputStreamWriter(getActivity().openFileOutput("custom.txt", Context.MODE_PRIVATE));
            InputStream newInputStream = getActivity().openFileInput("temp.txt");
            if (newInputStream != null){
                InputStreamReader newInputStreamReader = new InputStreamReader(newInputStream);
                BufferedReader newReader = new BufferedReader(newInputStreamReader);
                while ((line = newReader.readLine()) != null) {
                    newWriter.write(line + ls);
                }
                newReader.close();
            }
            newWriter.close();
            Snackbar snackbar = Snackbar.make(getView(),"Entry edited!", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Deletes an entry from the internal file with custom emoticons
     * @param file The file to delete from
     */
    private void deleteFromFile(String file) {
        String line;
        String ls = System.getProperty("line.separator");
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput("temp.txt", Context.MODE_PRIVATE));
            InputStream inputStream = getActivity().openFileInput(file);
            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                int lineToDelete = 0;
                while ((line = reader.readLine()) != null) {
                    if (selectedTextPosn != lineToDelete){
                        writer.write(line + ls);
                        lineToDelete++;
                    } else {
                        lineToDelete++;
                    }
                }
                reader.close();
            }
            writer.close();
            OutputStreamWriter newWriter = new OutputStreamWriter(getActivity().openFileOutput("custom.txt", Context.MODE_PRIVATE));
            InputStream newInputStream = getActivity().openFileInput("temp.txt");
            if (newInputStream != null){
                InputStreamReader newInputStreamReader = new InputStreamReader(newInputStream);
                BufferedReader newReader = new BufferedReader(newInputStreamReader);
                while ((line = newReader.readLine()) != null) {
                    newWriter.write(line + ls);
                }
                newReader.close();
            }
            newWriter.close();
            Snackbar snackbar = Snackbar.make(getView(),"Entry deleted!", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Exception: "+e.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Initialize the grid view with an adapter that contains the data of custom emoticons.
     * @param rootView The view containing the grid view
     */
    private void createGrid(View rootView) {
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_custom);
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
        gridView = (AutoGridView) rootView.findViewById(R.id.grid_custom);
        ArrayAdapter adapter = ((ArrayAdapter)gridView.getAdapter());
        adapter.notifyDataSetChanged();
    }

    /**
     * The custom adapter that will store the data of custom emoticons.
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
            registerForContextMenu(mainViewHolder.button); // Enable creation of context menu on long press of button
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
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(text);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                clipboard.setPrimaryClip(clip);
            }
        }
    }
}
