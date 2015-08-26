package be.ifosup.vco.daosample.app.engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import be.ifosup.vco.daosample.app.iface.ClientDAO;

import java.util.ArrayList;
import java.util.List;

import static be.ifosup.vco.daosample.app.engine.Client.Entries.*;

/**
 * Created by cedric on 22-05-15.
 */
public class ClientDAOImpl extends SqlDAO implements ClientDAO {
    public ClientDAOImpl(Context context) {
        super(context);
    }

    @Override
    public long AddClient(Client client) {

        ContentValues values = new ContentValues();
        values.put(COL_FIRST_NAME, client.getFirstName());
        values.put(COL_LAST_NAME, client.getLastName());
        values.put(COL_ADDRESS, client.getAddress());
        values.put(COL_CITY,client.getCity().getId());
        Log.d("VCO-DB", "add client");
        return db.insertOrThrow(TABLE_NAME,null,values);
    }

    @Override
    public int updateClient(Client client) {
        ContentValues values = new ContentValues();
        values.put(COL_FIRST_NAME, client.getFirstName());
        values.put(COL_LAST_NAME, client.getLastName());
        values.put(COL_ADDRESS, client.getAddress());
        values.put(COL_CITY,client.getCity().getId());
        Log.d("VCO-DB", "update client");
        return db.update(TABLE_NAME,values,"WHERE ? = ?", new String[] {ID, String.valueOf(client.getId())});
    }

    @Override
    public int deleteClient(Client client) {
        Log.d("VCO-DB", "delete client");
        return db.delete(TABLE_NAME,"WHERE ? = ?", new String[] {ID, String.valueOf(client.getId())});
    }

    @Override
    public List<Client> getClients() {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_NAME + "," + City.Entries.TABLE_NAME);

        String[] projectionIn = new String[]{
                ID, COL_FIRST_NAME, COL_LAST_NAME, COL_ADDRESS, City.Entries.ID, City.Entries.COL_CITY, City.Entries.COL_ZIPCODE
        };
        String order = COL_FIRST_NAME + " ASC";
        builder.appendWhere(COL_CITY + " = " + City.Entries.ID);
        Cursor cursor = builder.query(db,projectionIn,null,null,null,null,order);
        List<Client> clients = new ArrayList<>();
        if (cursor.getCount() !=0) {
            cursor.moveToFirst();
            do{
                Client client = new Client();
                City city = new City();

                city.setId(cursor.getLong(cursor.getColumnIndexOrThrow(City.Entries.ID)));
                city.setCity(cursor.getString(cursor.getColumnIndexOrThrow(City.Entries.COL_CITY)));
                city.setZipCode(cursor.getString(cursor.getColumnIndexOrThrow(City.Entries.COL_ZIPCODE)));

                client.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID)));
                client.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(COL_FIRST_NAME)));
                client.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COL_LAST_NAME)));
                client.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)));
                client.setCity(city);

                clients.add(client);

            } while (cursor.moveToNext()) ;
        }
        Log.d("VCO-DB", "list client");
        return clients;
    }


    @Override
    public int count() {
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM ?",new String[] {TABLE_NAME});
        c.moveToFirst();
        Log.d("VCO-DB", "count client");
        return c.getInt(0);
    }

}
