package be.ifosup.vco.daosample.app.engine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.text.MessageFormat;

/**
 * Created by cedric on 22-05-15.
 */
public class ClientOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "clients.db";
    public static final int DB_VERSION = 1;

    private static ClientOpenHelper INSTANCE = null;

    private ClientOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    
    public static ClientOpenHelper getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = new ClientOpenHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  createCity = MessageFormat.format("CREATE TABLE {0} ( {1} INTEGER PRIMARY KEY, {2} TEXT, {3} TEXT )",
                City.Entries.TABLE_NAME, City.Entries.ID, City.Entries.COL_CITY, City.Entries.COL_ZIPCODE);
        db.execSQL(createCity);

        String createClient = MessageFormat.format("CREATE TABLE {0} ( {1} INTEGER PRIMARY KEY, {2} TEXT, {3} TEXT, {4} TEXT, {5} INTEGER, FOREIGN KEY({5}) REFERENCES {6}( {7} ))",
            Client.Entries.TABLE_NAME, Client.Entries.ID, Client.Entries.COL_FIRST_NAME, Client.Entries.COL_LAST_NAME, Client.Entries.COL_ADDRESS, Client.Entries.COL_CITY, City.Entries.TABLE_NAME, City.Entries.ID
        );

        db.execSQL(createClient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SQLiteStatement delete = db.compileStatement("DROP TABLE ?");
        delete.bindString(1, City.Entries.TABLE_NAME);
        delete.execute();
        delete.bindString(1, City.Entries.TABLE_NAME);
        delete.execute();
        onCreate(db);
    }

}
