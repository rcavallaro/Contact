package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/23/2017.
 */

import android.support.v4.app.Fragment;

public class AddressBookActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AddressBookFragment();
    }

}
