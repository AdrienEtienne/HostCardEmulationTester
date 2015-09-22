package galitt.kn.service;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adrien on 22/09/2015.
 */
public class HttpClientHceServiceSingletonTest {

    @Test
    public void testGetInstance() throws Exception {
        HttpClientHceServiceSingleton client = HttpClientHceServiceSingleton.getInstance();
        HttpClientHceServiceSingleton client2 = HttpClientHceServiceSingleton.getInstance();
        Assert.assertEquals(8080, client.GetPort());
        Assert.assertEquals(8080, client2.GetPort());
        client.SetAddressAndPort("", 9000);
        Assert.assertEquals(9000, client.GetPort());
        Assert.assertEquals(9000, client2.GetPort());
    }

    @Test
    public void testGetAddress() throws Exception {

    }

    @Test
    public void testGetPort() throws Exception {

    }

    @Test
    public void testSetAddressAndPort() throws Exception {

    }

    @Test
    public void testGetUrl() throws Exception {

    }
}