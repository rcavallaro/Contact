package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/23/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class AddressBookActivity extends SingleFragmentActivity {
//public class AddressBookActivity extends AppCompatActivity {
    @Override
    protected Fragment createFragment() {
        return new AddressBookFragment();
    }

}
