package sg.edu.rp.c346.id20018354.mywatchedlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "watchlist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ITEM = "ITEM";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createItemTableSql = "CREATE TABLE " + TABLE_ITEM + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_CATEGORY + " TEXT )";
        db.execSQL(createItemTableSql);
        Log.i("info", createItemTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
//        onCreate(db);
    }

    public long insertWatched(String title, String description, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_CATEGORY, category);
        long result = db.insert(TABLE_ITEM, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<WatchList> getAllList() {
        ArrayList<WatchList> watchlists = new ArrayList<WatchList>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + "," + COLUMN_DESCRIPTION + ","
                + COLUMN_CATEGORY + " FROM " + TABLE_ITEM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String category = cursor.getString(3);

                WatchList newitem = new WatchList(id, title, description, category);
                watchlists.add(newitem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return watchlists;
    }
    public int updateItem(WatchList data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        values.put(COLUMN_CATEGORY, data.getCategory());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_ITEM, values, condition, args);
        db.close();
        return result;
    }


    public int deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_ITEM, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getcategory() {
        ArrayList<String> codes = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_CATEGORY};

        Cursor cursor;
        cursor = db.query(true, TABLE_ITEM, columns, null, null, null, null, null, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                codes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return codes;
    }

    public ArrayList<WatchList> getAllItemByCategory(int categoryFilter) {
        ArrayList<WatchList> watchlists = new ArrayList<WatchList>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_CATEGORY};
        String condition = COLUMN_CATEGORY + "= ?";
        String[] args = {String.valueOf(categoryFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_ITEM, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String category = cursor.getString(3);

                WatchList newitem = new WatchList(id, title, description, category);
                watchlists.add(newitem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return watchlists;
    }
}
