package com.waakye.garageitem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.waakye.garageitem.data.UsedItemContract;
import com.waakye.garageitem.data.UsedItemDbHelper;

public class CatalogActivity extends AppCompatActivity {

    public static final String LOG_TAG = CatalogActivity.class.getSimpleName();

    /** Database helper that will provide us access to the database */
    private UsedItemDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup button to open EditorActivity
        Button addUsedItemButton = (Button) findViewById(R.id.add_used_item_button);
        addUsedItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper and pass the
        // context, which is the current activity
        mDbHelper = new UsedItemDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displaydatabaseInfo();
    }

    /**
     * Helper method to insert hardcoded used_item data into the database. For debugging purposes
     * only
     */
    private void insertUsedItemFromCatalogActivity() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys, and Used Socks's
        // attributes are the values
        ContentValues values = new ContentValues();
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_NAME, "Used Socks");
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_PRICE, 5);
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_QUANTITY, 3);
        values.put(UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_IMAGE_URI, "content://");

        // Insert a new row for Used Socks in the database, returning the ID of that new row.
        // The first argument for db.insert() is the used_items_inventory table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when there are no
        // values).The third argument is the ContentValues object containing the info for Used Socks
        long newRowId = db.insert(UsedItemContract.UsedItemEntry.TABLE_NAME, null, values);
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the used_items database
     */
    private void displaydatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLOpenHelper and pass the context
        // which is the current activity
        UsedItemDbHelper usedItemDbHelper = new UsedItemDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = usedItemDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query
        String[] projection = {
                UsedItemContract.UsedItemEntry._ID,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_NAME,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_PRICE,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_QUANTITY,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_IMAGE_URI};

        // Perform a query on the used_items table
        Cursor cursor = db.query(
                UsedItemContract.UsedItemEntry.TABLE_NAME,  // The table to query
                projection,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // Don't group the rows
                null,                                       // Don't filter by row groups
                null);                                      // The sort order


        // TODO: Need a projection for the current quantity of a specific used_item
        // Define a projection that specifies only the name column from the database
        String[] projectionSpecific = {
                UsedItemContract.UsedItemEntry._ID,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_NAME,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_PRICE,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_QUANTITY,
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_IMAGE_URI};

        String[] whereArgs = new String[] {"Used Socks"};

        // Perform a query on the used_items table
        Cursor cursorSpecific = db.query(
                UsedItemContract.UsedItemEntry.TABLE_NAME,  // The table to query
                projectionSpecific,                         // The columns to return
                UsedItemContract.UsedItemEntry.COLUMN_USED_ITEM_NAME, // The columns for the WHERE clause
                whereArgs,                                  // The values for the WHERE clause
                null,                                       // Don't group the rows
                null,
                null);

//        try {
//            // Display the number of rows in the Cursor (which reflects the number of rows in the
//            // used_items_inventory table in the database).
//            TextView displayView = (TextView)findViewById(R.id.text_view_used_item);
//            displayView.setText("Number of rows in the used items inventory database table: "
//                + cursor.getCount());
//        } finally {
//            // Always close the cursor when you're done reading from it.  This releases all resources
//            // and makes it invalid
//            cursor.close();
//        }

        try {
            // Display the number of rows in the Cursor
            TextView displayView1 = (TextView)findViewById(R.id.text_view_specific_used_item);
            displayView1.setText("Number of rows in the used items inventory db table: "
                    + cursorSpecific.getCount());
        } finally {
            cursorSpecific.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu options from the res/menu/menu_catalog.xml file
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertUsedItemFromCatalogActivity();
                displaydatabaseInfo();
                return true;
            // Respond to a click on the "Delete All Entries"
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
