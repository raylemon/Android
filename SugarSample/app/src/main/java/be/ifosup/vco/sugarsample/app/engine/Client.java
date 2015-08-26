package be.ifosup.vco.sugarsample.app.engine;

import android.os.Parcel;
import android.os.Parcelable;
import com.orm.StringUtil;
import com.orm.SugarRecord;

/**
 * Created by cedric on 21-05-15.
 * POJO to be persist in a sugar DB
 * Describe any Client
 */
public class Client extends SugarRecord<Client> implements Parcelable {

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
    //private long id;  //Managed by Sugar
    private String firstName;
    private String lastName;
    private String address;
    private City city;

    public Client() {
    }

    /* MANAGED BY SUGAR !!
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

    private Client(Parcel in) {
        this.id = in.readLong();
        this.lastName = in.readString();
        this.firstName = in.readString();
        this.address = in.readString();
        this.city = in.readParcelable(City.class.getClassLoader());
    }

    public static Creator<Client> getCREATOR() {
        return CREATOR;
    }

    /**
     * Quicly create a client from parameters
     *
     * @param firstName first name of client
     * @param lastName  last name of client
     * @param address   address of client
     * @param city      {@link City} of client
     * @return {@link Client}
     */
    public static Client newClient(String firstName, String lastName, String address, City city) {
        Client mClient = new Client();
        mClient.setFirstName(firstName);
        mClient.setLastName(lastName);
        mClient.setAddress(address);
        mClient.setCity(city);
        return mClient;
    }

    /**
     * getter
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter
     * @return {@link City}
     */
    public City getCity() {
        return city;
    }

    /**
     * setter
     * @param city
     */
    public void setCity(City city) {
        this.city = city;
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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * Static class for columns names
     */
    static class Entries {
        public static final String ID = StringUtil.toSQLName("id");
        //public static final String TABLE_NAME = "T_CLIENTS"; //NO NEEDED ANYMORE
        public static final String COL_FIRST_NAME = StringUtil.toSQLName("firstName");
        public static final String COL_LAST_NAME = StringUtil.toSQLName("lastName");
        public static final String COL_ADDRESS = StringUtil.toSQLName("address");
        public static final String COL_CITY = StringUtil.toSQLName("city");
    }
}
