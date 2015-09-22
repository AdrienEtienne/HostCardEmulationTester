package galitt.kn.service;

import junit.framework.Assert;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import galitt.kn.service.hce.Transaction;

/**
 * Created by Adrien on 22/09/2015.
 */
public class ObjectFromResponseFactoryTest {
    String jsonText = "{\"apdu\":\"monApdu\"}";

    @Test
    public void testGetTransaction() throws Exception {
        Transaction t = ObjectFromResponseFactory.GetTransaction(jsonText);
        Assert.assertEquals("monApdu", t.GetApdu());
    }

    @Test(expected = ParseException.class)
    public void testGetTransaction_with_wrong_json() throws Exception {
        ObjectFromResponseFactory.GetTransaction("{\"apdu\"=\"truc\"}");
    }
}