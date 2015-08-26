package be.ifosup.vco.sugarsample.app.engine;

import be.ifosup.vco.sugarsample.app.iface.CityDAO;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import static be.ifosup.vco.sugarsample.app.engine.City.Entries.*;


/**
 * Created by Cedric on 27/05/2015.
 * Implementation of {@link CityDAO}
 */
public class CityDAOImpl implements CityDAO {
    @Override
    public boolean contains(City city) {
        long count = Select //Using Select method from Sugar to filter
                .from(City.class)
                .where(Condition.prop(COL_TOWN).like(city.getTown()))
                .and(Condition.prop(COL_ZIPCODE).eq(city.getZipCode()))
                .count();
        return (count > 0);
    }

    @Override
    public long addCity(City city) {

        return city.save();
    }

    @Override
    public List<City> getCities() {
        return City.listAll(City.class);
    }
}
