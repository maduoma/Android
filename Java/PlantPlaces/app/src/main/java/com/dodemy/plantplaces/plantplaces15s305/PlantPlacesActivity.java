package com.dodemy.plantplaces.plantplaces15s305;

import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.dodemy.plantplaces.R;


public abstract class PlantPlacesActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gpsaplant, menu);

        int currentMenuId = getCurrentMenuId();
        // if we have a menu ID, remove that from our menu.
        if (currentMenuId != 0) {
            menu.removeItem(currentMenuId);
        }

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

    /**
     * This method is invoked when the user clicks the GPS A Plant Menu option.
     * @param menuItem
     */
    public void gpsAPlantClicked(MenuItem menuItem) {
        Intent gpsAPlantIntent = new Intent(this, GPSAPlant.class);
        startActivity(gpsAPlantIntent);
    }

    /**
     *
     * @param menuItem
     */
    public void searchByColorClicked(MenuItem menuItem) {
        Intent searchByColorIntent = new Intent(this, ColorCaptureActivity.class);
        startActivity(searchByColorIntent);
    }

    public abstract int getCurrentMenuId();
}
