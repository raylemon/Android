package be.ifosup.vco.daosample.app.engine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cedric on 21-05-15.
 */
public class Client implements Parcelable {

    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private City city;

    public Client() {}

    public static Client newClient(String firstName, String lastName, City address ){
        Client mClient = new Client();
        mClient.setFirstName(firstName);
        mClient.setLastName(lastName);
        mClient.setCity(address);
        mClient.setId(-1L);
        return mClient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public static Creator<Client> getCREATOR() {
        return CREATOR;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.lastName);
        dest.writeString(this.firstName);
        dest.writeString(this.address);
        dest.writeParcelable(this.city, 0);
    }

    private Client(Parcel in) {
        this.id = in.readLong();
        this.lastName = in.readString();
        this.firstName = in.readString();
        this.address = in.readString();
        this.city = in.readParcelable(City.class.getClassLoader());
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    static class Entries {
        public static final String ID = "ID";
        public static final String TABLE_NAME = "T_CLIENTS";
        public static final String COL_FIRST_NAME = "FIRSTNAME";
        public static final String COL_LAST_NAME = "LASTNAME";
        public static final String COL_ADDRESS = "ADDRESS";
        public static final String COL_CITY = "FK_CITY";
    }
}
