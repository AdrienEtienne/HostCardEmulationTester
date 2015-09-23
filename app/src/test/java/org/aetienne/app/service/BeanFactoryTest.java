package org.aetienne.app.service;

import com.google.gson.JsonSyntaxException;

import junit.framework.Assert;

import org.junit.Test;

import org.aetienne.app.service.hce.Transaction;

import static org.junit.Assert.*;

/**
 * Created by Adrien on 23/09/2015.
 */
public class BeanFactoryTest {
    String jsonText = "{\"apdu\":\"monApdu\"}";

    @Test
    public void testGetTransaction() throws Exception {
        Transaction t = BeanFactory.GetTransaction(jsonText);
        Assert.assertEquals("monApdu", t.GetApdu());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testGetTransaction_with_wrong_json() throws Exception {
        Transaction t = BeanFactory.GetTransaction("{\"apdu\"=\"truc\"");
    }

    @Test
    public void testGetJsonString() throws Exception {
        Transaction t = new Transaction("monApdu");
        Assert.assertEquals("{\"apdu\":\"monApdu\"}", BeanFactory.GetJsonString(t));
    }
}
