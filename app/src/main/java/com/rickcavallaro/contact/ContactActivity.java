package com.rickcavallaro.contact;

import android.support.v4.app.Fragment;

public class ContactActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ContactFragment();
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new ContactFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
*/

//    public String getPackage(Context context) {
//        //return context.
//    }
}


