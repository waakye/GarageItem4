package com.waakye.garageitem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.waakye.garageitem.data.UsedItemContract;
import com.waakye.garageitem.data.UsedItemDbHelper;

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


    /**
     * Get user input from editor and save new used_item into database
     */
    private void insertUsedItemFromEditorActivity() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        String quantityString = mQuantityEditText.getText().toString().trim();
        // TODO: Need to add string for image uri
        int price = Integer.parseInt(priceString);
        int quantity = Integer.parseInt(quantityString);

        // Create database helper
        UsedItemDbHelper mDbHelper = new UsedItemDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys, and used_item
        // attributes from the editor are the values
        ContentValues values = new ContentValues();
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_NAME, nameString);
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_PRICE, price);
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_QUANTITY, quantity);
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_IMAGE_URI, "");

        // Insert a new row for the used_item in the database, returning the ID of that new row
        long newRowId = db.insert(UsedItemContract.UsedItemEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion
            Toast.makeText(this, "Error with saving used_item", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID
            Toast.makeText(this, "Used item with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // User clicked on a menu option in the app bar overflow menu
        switch(item.getItemId()){
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save used item to database
                insertUsedItemFromEditorActivity();
                // Exit activity
                finish();
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
