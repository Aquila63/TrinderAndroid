package dave.example.com.trinder.Utils.API;

import android.content.Context;
import android.content.SharedPreferences;

import com.loopj.android.http.*;

/**
 * Created by npaters on 20/02/15.
 */

public class BaseClient {
    private static final String BASE_URL = "http://trinder-api-paterson.c9.io/"; // for example
    private static final String DEFAULT_ACCESS_TOKEN = "bTmunLMfMu-J-Q";
    
    private String accessToken;
    private Context mContext;

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
        return BASE_URL + relativeUrl + "?access_token=" + getAccessToken();
    }
    
    public String getAccessToken() {
        if(mContext == null) {
            return "";
        }
        if (accessToken == null) {
            // get from shared preferences
            SharedPreferences pref = mContext.getSharedPreferences("preferences", 0);
            accessToken = pref.getString("accessToken", null); // getting String
        }
        return accessToken;
    }
    
    public void setAccessToken(String accessToken, Context ctx) {
        SharedPreferences pref = ctx.getSharedPreferences("preferences", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("accessToken", accessToken);
        editor.commit();
        mContext = ctx;
        accessToken = accessToken;
    }
    
    // can be used to determine if user is logged in.
    public boolean hasValidAccessToken() {
        return accessToken != null;
    }
}
