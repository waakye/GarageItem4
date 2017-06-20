package com.waakye.garageitem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup button to open EditorActivity
        Button addUsedItemButton = (Button)findViewById(R.id.add_used_item_button);
        addUsedItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        displaydatabaseInfo();
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

        // Perform this raw SQL query "SELECT * FROM used_items_inventory" to get a Cursor that
        // contains all rows from the used_items_inventory table
        Cursor cursor = db.rawQuery("SELECT * FROM " + UsedItemContract.UsedItemEntry.TABLE_NAME,
                null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // used_items_inventory table in the database).
            TextView displayView = (TextView)findViewById(R.id.text_view_used_item);
            displayView.setText("Number of rows in the used items inventory database table: "
                + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it.  This releases all resources
            // and makes it invalid
            cursor.close();
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
                // Do nothing for now
                return true;
            // Respond to a click on the "Delete All Entries"
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
