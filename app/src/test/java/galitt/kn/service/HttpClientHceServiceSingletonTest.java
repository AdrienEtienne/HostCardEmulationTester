package galitt.kn.service;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Adrien on 22/09/2015.
 */
public class HttpClientHceServiceSingletonTest {

    @Test
    public void testGetInstance() throws Exception {
        HttpClientHceServiceSingleton client = HttpClientHceServiceSingleton.getInstance();
        HttpClientHceServiceSingleton client2 = HttpClientHceServiceSingleton.getInstance();
        Assert.assertEquals(8080, client.getPort());
        Assert.assertEquals(8080, client2.getPort());
        client.setAddressAndPort("", 9000);
        Assert.assertEquals(9000, client.getPort());
        Assert.assertEquals(9000, client2.getPort());
    }

    @Test
    public void testGetAddress() throws Exception {
        HttpClientHceServiceSingleton client = HttpClientHceServiceSingleton.getInstance();
        client.setAddressAndPort("127.0.0.1", 9000);
        Assert.assertEquals("http://127.0.0.1", client.getAddress());
    }

    @Test
    public void testGetPort() throws Exception {
        HttpClientHceServiceSingleton client = HttpClientHceServiceSingleton.getInstance();
        client.setAddressAndPort("127.0.0.1", 8080);
        Assert.assertEquals(8080, client.getPort());
    }

    @Test
    public void testSetAddressAndPort() throws Exception {
        HttpClientHceServiceSingleton client = HttpClientHceServiceSingleton.getInstance();
        client.setAddressAndPort("localhost", 9000);
        Assert.assertEquals("http://localhost", client.getAddress());
        Assert.assertEquals(9000, client.getPort());
    }

    @Test
    public void testGetUrl() throws Exception {

    }
}