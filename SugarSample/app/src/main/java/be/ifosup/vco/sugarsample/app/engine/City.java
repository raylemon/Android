package be.ifosup.vco.sugarsample.app.engine;

import android.os.Parcel;
import android.os.Parcelable;
import com.orm.StringUtil;
import com.orm.SugarRecord;

/**
 * Created by cedric on 21-05-15.
 * POJO to be persist in a sugar DB
 * Describe any City
 */
public class City extends SugarRecord<City> implements Parcelable {
    // private long id; //MANAGE BY SUGAR
    private String town;
    private String zipCode;

    public City() {}

    /**
     * Quickly generate a city from Parameter
     * (Factory pattern)
     * @param city the city name
     * @param zipCode the city zipcode
     * @return {@link City}
     */
    public static City newCity(String city, String zipCode) {
        City mCity = new City();
        mCity.setTown(city);
        mCity.setZipCode(zipCode);
        //mCity.setId(-1L);

        return mCity;
    }

    /**
     * setter
     * @param town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * getter
     * @return town name
     */
    public String getTown() {
        return town;
    }

    /**
     * setter
     * Verify if zipcode are formatted (4 digits)
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        if (zipCode.matches("[0-9]{4}"))this.zipCode = zipCode;
        else throw new IllegalArgumentException("Invalid zip code");
    }

    /**
     * getter
     * @return zipcode
     */
    public String getZipCode() {
        return zipCode;
    }

    /* MANAGED BY SUGAR
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.town);
        dest.writeString(this.zipCode);
    }

    private City(Parcel in) {
        this.id = in.readLong();
        this.town = in.readString();
        this.zipCode = in.readString();
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        public City[] newArray(int size) {
            return new City[size];
        }
    };


    @Override
    public String toString() {
        return zipCode + " " + town;
    }

    /**
     * Entries to use Colums names
     */
    static class Entries {
        public static final String ID = StringUtil.toSQLName("id");
        public static final String COL_TOWN = StringUtil.toSQLName("town");
        public static final String COL_ZIPCODE = StringUtil.toSQLName("zipCode");
        //public static final String TABLE_NAME = "T_CITY"; //NO NEEDED ANYMORE
    }

}
