package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/20/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.rickcavallaro.contact.database.ContactBaseHelper;
import com.rickcavallaro.contact.database.ContactCursorWrapper;
import com.rickcavallaro.contact.database.ContactDbSchema;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddressBook {
    private static AddressBook sAddressBook;
    private SQLiteDatabase mDatabase;
    //private List<Contact> mContacts;

    private AddressBook() {
        //mContacts = new ArrayList<>();

//        Contact contact1 = new Contact();
//        contact1.setName("Brook");
//        contact1.setEmail("Brook@email.com");
//        contact1.setAddress("1931 Brookgreen Gardens Dr");
//        contact1.setFavorite(true);
//        this.add(contact1);
        //mContacts.add(contact1);

        Contact contact2 = new Contact();
        contact2.setName("Columbus State");
        contact2.setEmail("cscc@email.com");
        contact2.setAddress("550 E. Spring St, Columbus, OH 43215");
        contact2.setFavorite(false);
        this.add(contact2);
        //mContacts.add(contact2);

        Contact contact3 = new Contact();
        contact3.setName("Holly");
        contact3.setEmail("Holly@email.com");
        contact3.setAddress("1545 Elmwood Ave 43215");
        contact3.setFavorite(true);
        this.add(contact3);
        //boolean add = mContacts.add(contact3);

        Contact contact4 = new Contact();
        contact4.setName("Max");
        contact4.setEmail("Max@email.com");
        contact4.setAddress("1625 Kirkley Rd 43221");
        contact4.setFavorite(false);
        this.add(contact4);
        //mContacts.add(contact4);

        Contact contact5 = new Contact();
        contact5.setName("Rick");
        contact5.setEmail("Rick@email.com");
        contact5.setAddress("6705 McElwee Drive, New Albany, OH 43054");
        contact5.setFavorite(false);
        this.add(contact5);
        //mContacts.add(contact5);


//        for (int i=0; i<5; i++) {
//            Contact contact = new Contact();
//            contact.setName("Person " + i);
//            contact.setEmail("Person" + i + "@email.com");
//            contact.setAddress("550 E. Spring St, Columbus, OH 43215");
//
//            // set every 10th as a favorite
//            if (i % 2 == 0) {
//                contact.setFavorite(true);
//            }
//
//            mContacts.add(contact);

    }

    private AddressBook(Context context) {
        mDatabase = new ContactBaseHelper(context).getWritableDatabase();
    }
    public static AddressBook get(Context context) {
        if (sAddressBook == null) {
            sAddressBook = new AddressBook(context);
            Contact contact1 = new Contact();
            contact1.setName("Brook");
            contact1.setEmail("Brook@email.com");
            contact1.setAddress("1931 Brookgreen Gardens Dr");
            contact1.setFavorite(true);
            sAddressBook.add(contact1);
        }

        return sAddressBook;
    }

    public static AddressBook get() {
        if (sAddressBook == null) {
            sAddressBook = new AddressBook();
        }
    return sAddressBook;
    }

    public void add(Contact contact) {
        ContentValues values = getContentValues(contact);
        mDatabase.insert(ContactDbSchema.ContactTable.NAME, null, values);
    }

    public void updateContact(Contact contact) {
        String uuidString = contact.getID().toString();
        ContentValues values = getContentValues(contact);
        mDatabase.update(ContactDbSchema.ContactTable.NAME, values,
                ContactDbSchema.ContactTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(); //mContacts;
    }

    public Contact getContact(UUID id) {
    // get only contacts with matching UUID
        ContactCursorWrapper cursorWrapper = queryContacts(
                ContactDbSchema.ContactTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getContact();
        }
        finally {
            cursorWrapper.close();
        }
    }

    public List<Contact> getFavoriteContacts() {
        List<Contact> contacts = new ArrayList<>();
        // get only contacts with favorite == 1
        ContactCursorWrapper cursorWrapper = queryContacts(
                ContactDbSchema.ContactTable.Cols.FAVORITE + " = ?",
                new String[] { "true" }
        );
        try {
            if (cursorWrapper.getCount() == 0) {
                return contacts;
            }
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                contacts.add(cursorWrapper.getContact());
                cursorWrapper.moveToNext();
            }
        }
            finally {
            cursorWrapper.close();
        }
            return contacts;
        //return new ArrayList<>(); //favorites;
    }

    private static ContentValues getContentValues(Contact contact) {
        // convert image to a byte array for storage
        byte[] imageData = {};
            // Need last weeks stuff to get camera image
            if (contact.getImage() != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                contact.getImage().compress(Bitmap.CompressFormat.PNG, 0, stream);
                imageData = stream.toByteArray();
            }
        ContentValues values = new ContentValues();
        values.put(ContactDbSchema.ContactTable.Cols.UUID, contact.getID().toString());
        values.put(ContactDbSchema.ContactTable.Cols.NAME, contact.getName());
        values.put(ContactDbSchema.ContactTable.Cols.EMAIL, contact.getEmail());
        values.put(ContactDbSchema.ContactTable.Cols.FAVORITE, contact.isFavorite() ? "true" : "false"
        );
        values.put(ContactDbSchema.ContactTable.Cols.ADDRESS, contact.getAddress());
        values.put(ContactDbSchema.ContactTable.Cols.IMAGE, imageData);
        return values;
    }

    private Cursor queryContacts(String whereClause, String[] whereArgs) {
        return mDatabase.query(ContactDbSchema.ContactTable.NAME, null, whereClause, whereArgs,
                null, null, null, null);
    }

    private ContactCursorWrapper queryContacts(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(ContactDbSchema.ContactTable.NAME, null, whereClause, whereArgs,
                null, null, null, null);
        return new ContactCursorWrapper(cursor);
    }
}
