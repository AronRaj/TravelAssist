package com.app.travelassist.database;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;


public class ShopProvider extends ContentProvider {

    private static final String TAG = ShopProvider.class.getSimpleName();
    private static final boolean DEBUG = true;

    public static final String DATABASE_NAME = "travelassist.db";
    public static final String TABLE_SHOP = "shop_table";
    public static final String TABLE_MENU_ITEM = "menu_item_table";
    public static final String TABLE_CURRENT_LOCATION = "current_location";
    public static final String TABLE_SHOP_RATING = "shop_rating";
    private static final int DATABASE_VERSION = 1;

    public static final String AUTHORITY = "com.app.travelassist.database.ShopProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri CONTENT_URI_SHOP_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_SHOP);
    public static final Uri CONTENT_URI_MENU_ITEM_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_MENU_ITEM);
    public static final Uri CONTENT_URI_CURRENT_LOCATION_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_CURRENT_LOCATION);
    public static final Uri CONTENT_URI_SHOP_RATING_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_SHOP_RATING);

    public DatabaseHelper mDbHelper;
    private static final UriMatcher sUriMatcher;


    public interface SHOP_COLUMNS {
        String SHOP_ID = "shop_id";
        String SHOP_NAME = "shop_name";
        String SHOP_TYPE = "shop_type";
        String SHOP_CUISINE = "shop_cuisine";
        String SHOP_LATITUDE = "shop_lat";
        String SHOP_LONGITUDE = "shop_long";
        String SHOP_DISTANCE = "shop_distance";
        String SHOP_RATING = "shop_rating";
        String SHOP_TOTAL_RATED = "shop_total_rated";
        String SHOP_STATUS = "shop_status";
        String SHOP_ADDRESS = "shop_address";
        String SHOP_MOBILE = "shop_mobile";
        String SHOP_IMAGE_URL = "shop_image_url";
        String SHOP_INFO_PROCESSED = "shop_info_processed";
        int SHOP_LOCATION_UN_PROCESSED = 0;
        int SHOP_LOCATION_PROCESSED = 1;
    }

    public interface MENU_ITEM_COLUMNS {
        String ITEM_ID = "item_id";
        String ITEM_NAME = "item_name";
        String ITEM_CATEGORY = "item_category";
        String ITEM_PRICE = "item_price";
        String SHOP_ID = "shop_id";
    }

    public interface LOCATION_COLUMNS {
        String CURRENT_LATITUDE = "current_latitude";
        String CURRENT_LONGITUDE = "current_longitude";
    }

    public interface RATING_COLUMNS {
        String SHOP_ID = "shop_id";
        String RATING_ITEM = "rating_item";
        String RATING_COMMENT = "rating_comment";
    }


    private static final String CREATE_SHOP_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SHOP + "("
            + SHOP_COLUMNS.SHOP_ID + " TEXT PRIMARY KEY ," + SHOP_COLUMNS.SHOP_NAME + " TEXT," +
            SHOP_COLUMNS.SHOP_TYPE + " TEXT," + SHOP_COLUMNS.SHOP_CUISINE + " TEXT," + SHOP_COLUMNS.SHOP_RATING + " TEXT," + SHOP_COLUMNS.SHOP_TOTAL_RATED + " TEXT," +
            SHOP_COLUMNS.SHOP_LATITUDE + " DOUBLE," + SHOP_COLUMNS.SHOP_LONGITUDE + " DOUBLE," + SHOP_COLUMNS.SHOP_ADDRESS + " TEXT," + SHOP_COLUMNS.SHOP_IMAGE_URL + " TEXT," +
            SHOP_COLUMNS.SHOP_MOBILE + " TEXT,"+SHOP_COLUMNS.SHOP_STATUS + " TEXT,"+SHOP_COLUMNS.SHOP_DISTANCE + " INTEGER," + SHOP_COLUMNS.SHOP_INFO_PROCESSED + " INTEGER DEFAULT 0)";

    private static final String CREATE_MENU_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_MENU_ITEM + "("
            + MENU_ITEM_COLUMNS.ITEM_ID + " TEXT PRIMARY KEY ," + MENU_ITEM_COLUMNS.ITEM_NAME + " TEXT," +
            MENU_ITEM_COLUMNS.ITEM_CATEGORY + " TEXT," + MENU_ITEM_COLUMNS.ITEM_PRICE + " TEXT,"+
            MENU_ITEM_COLUMNS.SHOP_ID + " TEXT)";

    private static final String CREATE_CURRENT_LOCATION_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CURRENT_LOCATION + "("
            + LOCATION_COLUMNS.CURRENT_LATITUDE + " DOUBLE,"+ LOCATION_COLUMNS.CURRENT_LONGITUDE + " DOUBLE)";

    private static final String CREATE_SHOP_RATING_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SHOP_RATING + "("
            + RATING_COLUMNS.SHOP_ID + " TEXT,"+ RATING_COLUMNS.RATING_COMMENT + " TEXT,"
            + RATING_COLUMNS.RATING_ITEM + " DOUBLE)";

    private static final int CASE_SHOP_TABLE = 1;
    private static final int CASE_MENU_ITEM_TABLE = 2;
    private static final int CASE_CURRENT_LOCATION_TABLE = 3;
    private static final int CASE_SHOP_RATING_TABLE = 4;
    private static final int CASE_DEFAULT = 5;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, TABLE_SHOP, CASE_SHOP_TABLE);
        sUriMatcher.addURI(AUTHORITY, TABLE_MENU_ITEM, CASE_MENU_ITEM_TABLE);
        sUriMatcher.addURI(AUTHORITY, TABLE_CURRENT_LOCATION, CASE_CURRENT_LOCATION_TABLE);
        sUriMatcher.addURI(AUTHORITY, TABLE_SHOP_RATING, CASE_SHOP_RATING_TABLE);
        sUriMatcher.addURI(AUTHORITY, "/*", CASE_DEFAULT);
    }


    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case CASE_SHOP_TABLE:
                return AUTHORITY + "/" + TABLE_SHOP;
            case CASE_MENU_ITEM_TABLE:
                return AUTHORITY + "/" + TABLE_MENU_ITEM;
            case CASE_CURRENT_LOCATION_TABLE:
                return AUTHORITY + "/" + TABLE_CURRENT_LOCATION;
            case CASE_SHOP_RATING_TABLE:
                return AUTHORITY + "/" + TABLE_SHOP_RATING;
            case CASE_DEFAULT:
                return AUTHORITY + "/*";
            default:
                return null;
        }
    }


    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(CREATE_SHOP_TABLE);
        db.execSQL(CREATE_MENU_ITEM_TABLE);
        db.execSQL(CREATE_CURRENT_LOCATION_TABLE);
        db.execSQL(CREATE_SHOP_RATING_TABLE);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor lCursor = null;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CASE_SHOP_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CASE_MENU_ITEM_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CASE_CURRENT_LOCATION_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CASE_SHOP_RATING_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                break;
        }
        return lCursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase lDb = mDbHelper.getWritableDatabase();
        Uri lInsertedUri = null;
        long lRowId = 0;
        switch (sUriMatcher.match(uri)) {
            case CASE_SHOP_TABLE:
                try {
                    lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                } catch (Exception e) {

                }
                break;
            case CASE_MENU_ITEM_TABLE:
                try {
                    lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                } catch (Exception e) {

                }
                break;
            case CASE_CURRENT_LOCATION_TABLE:
                try {
                    lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                } catch (Exception e) {

                }
                break;
            case CASE_SHOP_RATING_TABLE:
                try {
                    lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                } catch (Exception e) {

                }
                break;
            default:
                break;

        }
        if (lRowId > 0) {
            lInsertedUri = ContentUris.withAppendedId(uri, lRowId);
        }
        if (DEBUG) Log.d(TAG, "inserted uri is " + lInsertedUri);
        return lInsertedUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CASE_SHOP_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            case CASE_MENU_ITEM_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            case CASE_CURRENT_LOCATION_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            case CASE_SHOP_RATING_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int lCount = 0;
        SQLiteDatabase lDb = mDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CASE_SHOP_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            case CASE_MENU_ITEM_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            case CASE_CURRENT_LOCATION_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            case CASE_SHOP_RATING_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        if (DEBUG) Log.d(TAG, "updated lCount  " + lCount);
        return lCount;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

