package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/23/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;

public class AddressBookActivity extends SingleFragmentActivity
        implements AddressBookFragment.Callbacks {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_listdetail;
    }

    @Override
    protected Fragment createFragment() {
        return new AddressBookFragment();
    }

    @Override
    public void onContactSelected(Contact contact) {
        // when viewed on a phone
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = ContactPagerActivity.newIntent(this, contact.getID());
            startActivity(intent);
        }
        // when viewed on a tablet
        else {
            Fragment newDetail = ContactFragment.newInstance(contact.getID());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}
