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


public class ContactFragment extends Fragment {
    private Contact mContact;
    private EditText mNameField;
    private EditText mEmailField;
    private CheckBox mFavorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContact = new Contact();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        mNameField = (EditText) v.findViewById(R.id.contact_name);
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

//        mFavorite = (CheckBox) v.findViewById(R.id.contact_favorite);
//        mFavorite.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            //public void onCheckedChanged(CompoundButton)
//        });

        return v;

    }
}
