package galitt.kn.service.hce;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Adrien on 22/09/2015.
 */
public class TransactionTest {

    @Test
    public void testObjectToJson() throws Exception {
        Transaction t = new Transaction("monApdu");
        String s = t.ObjectToJson();
        Assert.assertEquals("{\"apdu\":\"monApdu\"}", s);
    }
}