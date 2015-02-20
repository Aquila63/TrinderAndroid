package dave.example.com.trinder.Utils.API;

import com.loopj.android.http.*;

/**
 * Created by npaters on 20/02/15.
 */

public class BaseClient {
    private static final String BASE_URL = "http://trinder-api-paterson.c9.io/"; // for example
    private static final String DEFAULT_ACCESS_TOKEN = "bTmunLMfMu-J-Q";

    BaseClient() {

    }

    // todo: Access Token
    // todo: Use a queue of sorts so that the app can still be used offline (to an extent)


    private static AsyncHttpClient client = new AsyncHttpClient();

    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl + "?access_token=" + DEFAULT_ACCESS_TOKEN;
    }
}