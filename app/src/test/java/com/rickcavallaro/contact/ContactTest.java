package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/16/2017.
 */

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

//import org.mockito.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class ContactTest {
    //@Mock
    //Log mLog;

    @Test
    public void contactNameTest() {
        String name = "TestName";
        Contact contact = new Contact();
        contact.setName(name);
        assertEquals(name, contact.getName());
    }
    @Test
    public void contactEmailTest() {
        String email = "TestName";
        Contact contact = new Contact();
        contact.setName(email);
        assertEquals(email, contact.getName());
    }

}
