package org.aetienne.app.service;

import com.google.gson.JsonSyntaxException;

import junit.framework.Assert;

import org.aetienne.app.service.entity.EntityFactory;
import org.junit.Test;

import org.aetienne.app.service.entity.Transaction;

/**
 * Created by Adrien on 23/09/2015.
 */
public class BeanFactoryTest {
    String jsonText = "{\"apdu\":\"monApdu\"}";

    @Test
    public void testGetTransaction() throws Exception {
        Transaction t = EntityFactory.getTransaction(jsonText);
        Assert.assertEquals("monApdu", t.getApdu());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testGetTransaction_with_wrong_json() throws Exception {
        Transaction t = EntityFactory.getTransaction("{\"apdu\"=\"truc\"");
    }

    @Test
    public void testGetJsonString() throws Exception {
        Transaction t = new Transaction("monApdu");
        Assert.assertEquals("{\"apdu\":\"monApdu\"}", EntityFactory.fromJson(t));
    }
}
