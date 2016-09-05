package in.silive.scrolls.Network;

import java.net.MalformedURLException;

/**
 * Created by akriti on 4/9/16.
 */
public interface NetworkResponseListener {
    public void beforeRequest() throws MalformedURLException;


    public void postRequest(Object result) throws MalformedURLException;
}
