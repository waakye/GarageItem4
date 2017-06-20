package com.waakye.garageitem;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Allows user to create a new used item or edit an existing one
 * Created by lesterlie on 6/17/17.
 */

public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the used_item's name */
    private EditText mNameEditText;

    /** EditText field to enter the used_item's price */
    private EditText mPriceEditText;

    /** EditText field to enter the used_item's quantity */
    private EditText mQuantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText)findViewById(R.id.edit_used_item_name);
        mPriceEditText = (EditText)findViewById(R.id.edit_used_item_price);
        mQuantityEditText = (EditText)findViewById(R.id.edit_used_item_quantity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file
        // This adds menu items to the app bar
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // User clicked on a menu option in the app bar overflow menu
        switch(item.getItemId()){
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Do nothing for now
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
