package galitt.aetienne.hostcardemulationtester.service;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Adrien on 23/09/2015.
 */
public class ApiHCEApplicationTest {
    @Test
    public void testGetInstance() throws Exception {
        ApiHCEApplication client = ApiHCEApplication.getInstance();
        ApiHCEApplication client2 = ApiHCEApplication.getInstance();
        Assert.assertEquals(8080, client.getPort());
        Assert.assertEquals(8080, client2.getPort());
        client.setAddressAndPort("", 9000);
        Assert.assertEquals(9000, client.getPort());
        Assert.assertEquals(9000, client2.getPort());
    }

    @Test
    public void testGetAddress() throws Exception {
        ApiHCEApplication client = ApiHCEApplication.getInstance();
        client.setAddressAndPort("127.0.0.1", 9000);
        Assert.assertEquals("http://127.0.0.1", client.getAddress());
    }

    @Test
    public void testGetPort() throws Exception {
        ApiHCEApplication client = ApiHCEApplication.getInstance();
        client.setAddressAndPort("127.0.0.1", 8080);
        Assert.assertEquals(8080, client.getPort());

    }

    @Test
    public void testSetAddressAndPort() throws Exception {
        ApiHCEApplication client = ApiHCEApplication.getInstance();
        client.setAddressAndPort("localhost", 9000);
        Assert.assertEquals("http://localhost", client.getAddress());
        Assert.assertEquals(9000, client.getPort());
    }

    @Test
    public void testGetUrl_authentication() throws Exception {
        ApiHCEApplication client = ApiHCEApplication.getInstance();
        client.setAddressAndPort("localhost", 9000);
        Assert.assertEquals("http://localhost:9000/api/users/argument",
                client.getUrl(ApiHCEApplication.Apis.AUTHENTICATION, "/argument"));
    }

    @Test
    public void testGetUrl_hce() throws Exception {
        ApiHCEApplication client = ApiHCEApplication.getInstance();
        client.setAddressAndPort("localhost", 9000);
        Assert.assertEquals("http://localhost:9000/api/hce/argument",
                client.getUrl(ApiHCEApplication.Apis.HCE, "/argument"));
    }
}