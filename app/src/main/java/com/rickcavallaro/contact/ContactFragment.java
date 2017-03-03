package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/9/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


public class ContactFragment extends Fragment {
    private Contact mContact;
    private EditText mNameField;
    private EditText mEmailField;
    private CheckBox mFavoriteCheckBox;
    private static final String ARG_CONTACT_ID = "contact_id";

    public static ContactFragment newInstance(UUID contactID) {
        ContactFragment contactFragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT_ID, contactID);
        contactFragment.setArguments(args);
        return contactFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID contactID = (UUID) getArguments().getSerializable(ARG_CONTACT_ID);

        mContact = AddressBook.get().getContact(contactID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        mNameField = (EditText) v.findViewById(R.id.contact_name);
        mNameField.setText(mContact.getName());

        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //No new behavior
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                mContact.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                //No new behavior
            }
        });

        mEmailField = (EditText) v.findViewById(R.id.contact_email);
        mEmailField.setText(mContact.getEmail());
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //No new behavior
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                mContact.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                //No new behavior
            }
        });

        mFavoriteCheckBox = (CheckBox) v.findViewById(R.id.contact_favorite);
        mFavoriteCheckBox.setChecked(mContact.isFavorite());
//        mFavorite.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            //public void onCheckedChanged(CompoundButton)
//        });

        return v;

    }
}
