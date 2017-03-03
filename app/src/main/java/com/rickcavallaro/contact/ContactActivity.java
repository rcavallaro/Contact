package com.rickcavallaro.contact;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ContactActivity extends SingleFragmentActivity {
    public static final String EXTRA_CONTACT_ID = "com.rickcavallaro.contacts.contact_id";

    public static Intent newIntent(Context packageContext, UUID contactID) {
        Intent intent = new Intent(packageContext, ContactActivity.class);
        intent.putExtra(EXTRA_CONTACT_ID, contactID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID contactID = (UUID) getIntent().getSerializableExtra(EXTRA_CONTACT_ID);
        return ContactFragment.newInstance(contactID);
    }
}


