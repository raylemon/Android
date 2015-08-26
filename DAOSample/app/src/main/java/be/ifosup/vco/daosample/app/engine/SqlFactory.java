package be.ifosup.vco.daosample.app.engine;

import android.content.Context;
import be.ifosup.vco.daosample.app.iface.CityDAO;
import be.ifosup.vco.daosample.app.iface.ClientDAO;

/**
 * Created by cedric on 22-05-15.
 */
public final class SqlFactory {

    private final Context context;
    private CityDAOImpl cityDaoImpl;
    private ClientDAOImpl clientDaoImpl;
    public SqlFactory(Context context) {
        this.context = context;
    }

    public CityDAO getCityDAO(){
        if (cityDaoImpl == null) {
            cityDaoImpl = new CityDAOImpl(context);
        }
        return cityDaoImpl;
    }

    public ClientDAO getClientDAO(){

        if (clientDaoImpl == null) {
            clientDaoImpl = new ClientDAOImpl(context);
        }
        return clientDaoImpl;
    }

    public void close(){
        cityDaoImpl.close();
        clientDaoImpl.close();
    }
}
