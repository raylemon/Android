package be.ifosup.vco.daosample.app.engine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by cedric on 22-05-15.
 */
public abstract class SqlDAO {;
    protected SQLiteDatabase db;
    private Context context;

    public SqlDAO(Context context) {
        this.context = context;
        this.db = ClientOpenHelper.getInstance(context).getWritableDatabase();
    }

    public void close(){
        db.close();
    }
}
