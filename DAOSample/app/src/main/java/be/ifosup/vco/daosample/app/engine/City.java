package be.ifosup.vco.daosample.app.engine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cedric on 21-05-15.
 */
public class City implements Parcelable {
    private long id;
    private String city;
    private String zipCode;

    public City() {}

    public static City newInstance(String city, String zipCode) {
        City mCity = new City();
        mCity.setCity(city);
        mCity.setZipCode(zipCode);
        mCity.setId(-1L);

        return mCity;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setZipCode(String zipCode) {
        if (zipCode.matches("[0-9]{4}"))this.zipCode = zipCode;
        else throw new IllegalArgumentException("Invalid zip code");
    }

    public String getZipCode() {
        return zipCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.city);
        dest.writeString(this.zipCode);
    }

    private City(Parcel in) {
        this.id = in.readLong();
        this.city = in.readString();
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
        return zipCode + " " + city;
    }

    static class Entries {
        public static final String ID = "_ID";
        public static final String COL_CITY = "CITY";
        public static final String COL_ZIPCODE = "ZIPCODE";
        public static final String TABLE_NAME = "T_CITY";
    }
}
