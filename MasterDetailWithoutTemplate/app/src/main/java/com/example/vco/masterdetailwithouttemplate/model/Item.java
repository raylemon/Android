package com.example.vco.masterdetailwithouttemplate.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * ACAPATH 1
 * création d'un item basique à afficher comme liste
 * ne pas tenir compte de l'implements au départ
 */
public class Item implements Parcelable {
    private String title;
    private String body;
    private float rating;

    public Item(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getDropCap() {
        return String.valueOf(title.charAt(0));
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("First item", "the first item"));
        items.add(new Item("Second item", "a second item"));
        items.add(new Item("Third item", "another one"));
        return items;
    }

    /* ACAPATH 12
        Colisation
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeFloat(this.rating);
    }

    private Item(Parcel in) {
        this.title = in.readString();
        this.body = in.readString();
        this.rating = in.readFloat();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
