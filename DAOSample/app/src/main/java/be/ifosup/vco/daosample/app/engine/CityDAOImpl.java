package be.ifosup.vco.daosample.app.engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import be.ifosup.vco.daosample.app.iface.CityDAO;

import java.util.ArrayList;
import java.util.List;

import static be.ifosup.vco.daosample.app.engine.City.Entries.*;

/**
 * Created by cedric on 22-05-15.
 */
public class CityDAOImpl extends SqlDAO implements CityDAO {

    public CityDAOImpl(Context context) {
        super(context);
    }


    @Override
    public boolean contains(City city) {
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_CITY + " = '" + city.getCity() + "'", null);
        c.moveToFirst();
        if (c.getCount()==0) return false;
        if (c.getLong(c.getColumnIndexOrThrow(ID)) == city.getId()) return true;
        Log.d("VCO-DB", "contains city");
        return false;
    }

    @Override
    public long addCity(City city) {

        ContentValues values = new ContentValues();
        values.put(COL_CITY, city.getCity());
        values.put(COL_ZIPCODE, city.getZipCode());
        Log.d("VCO-DB", "add city");
        return db.insertOrThrow(TABLE_NAME,null,values);
    }

    @Override
    public List<City> getCities() {

        String[] projectionIn = new String[]{
                ID, COL_CITY, COL_ZIPCODE
        };
        String order = COL_CITY + " ASC";
        Cursor cursor = db.query(TABLE_NAME,projectionIn,null,null,null,null,order);
        List<City> cities = new ArrayList<>();
        if (cursor.getCount() >=0) {
            cursor.moveToFirst();
            do {
                City city = new City();

                city.setId(cursor.getLong(cursor.getColumnIndexOrThrow(City.Entries.ID)));
                city.setCity(cursor.getString(cursor.getColumnIndexOrThrow(City.Entries.COL_CITY)));
                city.setZipCode(cursor.getString(cursor.getColumnIndexOrThrow(City.Entries.COL_ZIPCODE)));

                city.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID)));
                cities.add(city);

            } while (cursor.moveToNext());
            Log.d("VCO-DB", "list cities");
        }

        return cities;
    }
}
