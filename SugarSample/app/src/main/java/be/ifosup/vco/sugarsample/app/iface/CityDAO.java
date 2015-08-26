package be.ifosup.vco.sugarsample.app.iface;

import be.ifosup.vco.sugarsample.app.engine.City;

import java.util.List;

/**
 * Created by cedric on 21-05-15.
 * Global methods to manage any {@link City} in database
 * Not very usefull for this example
 */
public interface CityDAO {
    /**
     * Check if storage already contains the city
     *
     * @param city the {@link City} to find
     * @return true if city are in storage, or else if not
     */
    boolean contains(City city);

    /**
     * Save a city in storage
     *
     * @param city the {@link City} to store
     * @return the cit id
     */
    long addCity(City city);

    /**
     * List all cities in storage
     *
     * @return a list of {@link City}
     */
    List<City> getCities();
}
