package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/9/2017.
 */

import android.util.Log;

import java.util.UUID;

public class Contact {
    private static final String TAG = Contact.class.getSimpleName();
    private UUID mID;
    private String mName;
    private String mEmail;
    private boolean mFavorite;

    public boolean isFavorite() {
        return mFavorite;
    }

    public Contact() {
        mID = UUID.randomUUID();
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
}
