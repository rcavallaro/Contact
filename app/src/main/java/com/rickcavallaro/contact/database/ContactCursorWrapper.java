package com.rickcavallaro.contact.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rickcavallaro.contact.Contact;

import java.util.UUID;

import static com.rickcavallaro.contact.database.ContactDbSchema.*;

/*
 * Created by Rick on 4/11/2017.
 */

public class ContactCursorWrapper extends CursorWrapper {
    public ContactCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    private String getColumnData(String columnName) {
        return getString(getColumnIndex(columnName));
    }
    public Contact getContact() {
        String uuid = getColumnData(ContactTable.Cols.UUID);
        String name = getColumnData(ContactTable.Cols.NAME);
        String email = getColumnData(ContactTable.Cols.EMAIL);
        String favorite = getColumnData(ContactTable.Cols.FAVORITE);
        String address = getColumnData(ContactTable.Cols.ADDRESS);
        byte[] imageData = getBlob(getColumnIndex(ContactTable.Cols.IMAGE));
        // convert the byte array into a Bitmap
        Bitmap image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        Contact contact = new Contact(UUID.fromString(uuid));
        contact.setName(name);
        contact.setEmail(email);
        contact.setFavorite(favorite.equals("true"));
        contact.setImage(image);  //Need to implement last weeks image from camera

        return contact;
    }
}
