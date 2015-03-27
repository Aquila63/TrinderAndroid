package dave.example.com.trinder;

import org.json.JSONException;
import dave.example.com.trinder.Utils.API.APIClient;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sofwat on 17/02/2015.
 */
public class Person implements Parcelable{


    private int id;
    private String name;
    private String course;
    private int age;
    private String status;
    private String description;
    private String[] photoURLs;

    public Person() {

    }
    public int describeContents() {
        return 0;
    }
    private Person(Parcel in) {
        id = in.readInt();
        name = in.readString();
        course = in.readString();
        age = in.readInt();
        status = in.readString();
        description = in.readString();
        photoURLs = in.createStringArray();
    }
    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeString(course);
        out.writeInt(age);
        out.writeString(status);
        out.writeString(description);
        out.writeStringArray(photoURLs);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    // setters, getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPhotoURLs() {
        return photoURLs;
    }

    public void setPhotoURLs(String[] photoURLs) {
        this.photoURLs = photoURLs;
    }

    // methods

    public void like() {
        // send off to API
        try {
            APIClient.getInstance().like(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void ignore() {
        // send off to API
        try {
            APIClient.getInstance().ignore(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static Person[] generateSample() {
        Person[] people = new Person[6];
        String[] names = {"Alan", "Brad", "Colin", "David", "Eddie", "Fred"};
        int[] ages = {13, 16, 19, 21, 24, 30};
        String[] courses = {"CSB", "CS", "BESS", "Law", "Science", "Medicine"};
        String[] statuses = {"Single", "In a relationship", "It's complecated", "Single", "In a relationship", "It's complecated"};
        String description = "Hi, I'm new to this, swipe right";
        String[] demoImageUrls = {"http://robohash.org/demo.png?size=300x300"};

        for(int i=0; i<6; i++ ) {
            Person person = new Person();
            person.setId(i);
            person.setName(names[i]);
            person.setAge(ages[i]);
            person.setCourse(courses[i]);
            person.setStatus(statuses[i]);
            person.setDescription(description);
            person.setPhotoURLs(demoImageUrls);
            people[i] = person;
        }
        return people;
    }
}
