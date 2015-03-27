package dave.example.com.trinder.Utils.API;

import android.provider.Contacts;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.Header;
import com.loopj.android.http.*;

import dave.example.com.trinder.Person;

/**
 * Created by npaters on 20/02/15.
 */

class APIClient extends BaseClient {
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
    public void like(Person person, boolean didLike) throws JSONException {

    }

    public void updateProfile(Person person) throws JSONException {

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

                p.setPhotoURLs(photos); // this mightn't work
                people[i] = p;
            }
        }
        catch (JSONException e) { e.printStackTrace(); }
        return people;


    }
}
