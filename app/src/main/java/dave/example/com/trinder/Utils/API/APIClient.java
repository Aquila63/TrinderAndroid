package dave.example.com.trinder.Utils.API;

import org.json.*;
import org.apache.http.Header;
import com.loopj.android.http.*;

import dave.example.com.trinder.Person;

/**
 * Created by npaters on 20/02/15.
 */

public class APIClient extends BaseClient {
    private static APIClient singletonInstance = null;
    long lastSuccessfulMatchesTimestamp = System.currentTimeMillis();
    protected APIClient() {
    }
    public static APIClient getInstance() {
        if(singletonInstance == null) {
            singletonInstance = new APIClient();
        }
        return singletonInstance;
    }
    public void getPeople(final Callback<Person[]> callback) throws JSONException {
        this.get("users", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray. Throw exception?
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray matches) {
                // todo parse and return
                Person[] people = parseArray(matches);
                callback.execute(people);
            }
            // todo onFailure
        });
    }
    public void getMatches(final Callback<Person[]> callback) throws JSONException {
        this.get("matches", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray. Throw exception?
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray matches) {
                // todo parse and return
                Person[] people = parseArray(matches);
                callback.execute(people);
            }
            // todo onFailure
        });
    }
    public void getHistory(final Callback<Person[]> callback) throws JSONException {
        this.get("history", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray. Throw exception?
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray matches) {
                // todo parse and return
                Person[] people = parseArray(matches);
                callback.execute(people);
            }
            // todo onFailure
        });
    }
    public void getNewMatches(final Callback<Person[]> callback) throws JSONException {
        RequestParams params = new RequestParams("since", lastSuccessfulMatchesTimestamp); // System.currentTimeMillis() can be changed by user. Maybe use something else?
        this.get("matches", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray. Throw exception?
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray matches) {
                lastSuccessfulMatchesTimestamp = System.currentTimeMillis();
                Person[] people = parseArray(matches);
                callback.execute(people);
            }
            // todo onFailure
        });
    }
    public void like(Person person) throws JSONException {
        this.get("users/" + person.id + "/like", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            }
            // todo onFailure
        });
    }
    
    public void ignore(Person person) throws JSONException {
        this.get("users/" + person.id + "/ignore", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            }
            // todo onFailure
        });
    }

    public void updateProfile(Person person) throws JSONException {

    }
    
    /*
        Callback's true if user is part of valid group
        Callback's false if user needs to verify email
    */

    public void authenticateWithFacebookAccessToken(String token, final Callback<Boolean> callback) throws JSONException {
        RequestParams params = new RequestParams("token", token);
        this.post("auth/facebook", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String token = response.getString("accessToken");
                    this.setAccessToken(token);
                    callback.execute(response.getBoolean("didAuthenticate"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray matches) {
                    // If the response is JSONArray instead of expected JSONObject. Throw exception?
            }
            // todo onFailure
        });
    }
    
    /*
        Callback's true if verification email was sent
        Callback's false if user cannot access app.
    */

    public void verifyEmail(String email, final Callback<Boolean> callback) throws JSONException {
        RequestParams params = new RequestParams("email", lastSuccessfulMatchesTimestamp); // System.currentTimeMillis() can be changed by user. Maybe use something else?
        this.post("auth/email", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    callback.execute(response.getBoolean("success"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray matches) {
                // If the response is JSONArray instead of expected JSONObject. Throw exception?
            }
            // todo onFailure
        });
    }

    public void checkIfCurrentUserIsVerified(final Callback<Boolean> callback) throws JSONException {
        this.get("auth/verified", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    callback.execute(response.getBoolean("isVerified"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            // todo onFailure
        });
    }

    private Person[] parseArray(JSONArray matches) {
        Person[] people = new Person[matches.length()];
        try {
            for (int i = 0; i < matches.length(); i++) {
                JSONObject person = matches.getJSONObject(i);
                Person p = new Person();
                p.setId(person.getInt("id"));
                p.setName(person.getString("name"));
                p.setCourse(person.getString("course"));
                p.setAge(person.getInt("age"));
                p.setStatus(person.getString("status"));
                p.setDescription(person.getString("description"));

                JSONArray jsonArray = person.getJSONArray("photo_urls");
                String[] photos = new String[jsonArray.length()];

                for (int j=0; j < jsonArray.length(); j++) {
                    photos[j] = jsonArray.get(j).toString();
                }

                p.setphotoURLs(photos); // this mightn't work
                people[i] = p;
            }
        }
        catch (JSONException e) { e.printStackTrace(); }
        return people;


    }
}
