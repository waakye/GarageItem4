package com.waakye.garageitem.data;

import android.provider.BaseColumns;

/**
 * Created by lesterlie on 6/20/17.
 */

public final class UsedItemContract {

    // To prevent someone from accidentally instantiating the contract class, give it an empty
    // constructor
    private UsedItemContract(){}

    /**
     * Inner class that defines constant values for the garage_inventory database table.
     * Each entry in the table represents a single used_item
     */
    public static final class UsedItemEntry implements BaseColumns {

        /** Name of database table for used items */
        public final static String TABLE_NAME = "garage_inventory";

        /**
         * Unique ID number for the used_item (only for use in the database table)
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the used_item
         * Type: TEXT
         */
        public final static String COLUMN_USED_ITEM_NAME = "name";

        /**
         * Price of the used_item
         * Type: INTEGER
         */
        public final static String COLUMN_USED_ITEM_PRICE = "price";

        /**
         * Quantity of the used_item
         * Type: INTEGER
         */
        public final static String COLUMN_USED_ITEM_QUANTITY = "quantity";

        /**
         * Image URI of the used_item
         * Type: TEXT
         */
        public final static String COLUMN_USED_ITEM_IMAGE_URI = "image_uri";
    }
}
