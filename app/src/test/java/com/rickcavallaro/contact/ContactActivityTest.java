package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/16/2017.
 */

import android.content.Context;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
//import org.mockito.Mockito.when;


public class ContactActivityTest {
    private static final String PACKAGE_NAME = "com.rickcavallaro.contact";

    @Mock
    Context mContext;

    @Test
    public void getPackageTest() {
        //when(mContext.getPackageName()).thenReturn(PACKAGE_NAME);
        //ContactActivity activity = new ContactActivity();
        //assertEquals(PACKAGE_NAME, activity.getPackage(mContext));
    }
}
