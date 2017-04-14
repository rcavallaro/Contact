package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/9/2017.
 */

import android.graphics.Bitmap;
import android.util.Log;

import java.util.UUID;

public class Contact {
    private static final String TAG = Contact.class.getSimpleName();
    private UUID mID;
    private String mName;
    private String mEmail;
    private boolean mFavorite;
    private String mAddress;
    private Bitmap mImage;

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    public Contact() {
        this(UUID.randomUUID());
    }
    public Contact(UUID id) {
        mID = id;
    }

    public UUID getID() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        Log.i(TAG, "New name: " + name);
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        Log.i(TAG, "New Email: " + email);
        mEmail = email;
    }

    public void setFavorite(boolean favorite) {
        Log.i(TAG, "New Favorite: " + favorite);
        mFavorite = favorite;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}

