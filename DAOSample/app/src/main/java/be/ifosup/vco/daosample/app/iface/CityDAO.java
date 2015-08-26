package be.ifosup.vco.daosample.app.iface;

import be.ifosup.vco.daosample.app.engine.City;

import java.util.List;

/**
 * Created by cedric on 21-05-15.
 */
public interface CityDAO {
    boolean contains(City city);

    long addCity(City city);

    List<City> getCities();
}
