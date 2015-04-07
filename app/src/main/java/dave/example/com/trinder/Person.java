package dave.example.com.trinder;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;

import dave.example.com.trinder.Utils.API.APIClient;

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
        Person[] people = new Person[20];
        String[] names = {"Abby", "Brooke", "Christine", "Erin", "India", "Kimberly",
                "Lexi", "Naomi", "Paige", "Penny", "Quinn", "Sophia",
                "Tara", "Jennifer", "Victoria", "Julia", "Aoife", "Aisling",
                "Aine", "Caoimhe"};
        int[] ages = {18, 19, 19, 21, 24, 22,
                23, 18, 19, 22, 20, 18,
                21, 21, 22, 19, 18, 19,
                20, 22};
        String[] courses = {"CSB", "CS", "BESS", "Law", "Science", "Medicine",
                "BESS", "Psychology", "BESS", "French & Business", "Dental Science", "BESS",
                "MSISS", "CS", "BESS", "Law", "Politics", "Nursing",
                "Film Studies", "MSISS"};
        String[] statuses = {"Single", "In a relationship", "It's complecated", "Single", "Single", "In a relationship",
                "Single", "In a relationship", "Single", "Single", "In a relationship", "It's complecated",
                "Single", "In a relationship", "It's complecated", "Single", "In a relationship", "Single",
                "Single", "In a relationship"};
        String [] descriptions = {"I'm a pretty cool person to get along with",
                "Love to be tickled. That is all.",
                "I have a never ending love for tattoos, piercings, photography, art, Body Worlds, chessecake, music & texting",
                "Looking for someone to shovel out my car. Nothing complicated, no strings attached, just please shovel out my car.",
                "I'm going down ;), I'm yellin trinder...",
                "Looking for a guy who will pick me over beer",
                "I'll be Burger King and you'll be mcdonalds, I'll have it my way and you'll be loving it",
                "Final year is no fun. Need to have some good time with little effort",
                "Stimulate my mind ;)",
                "Single mom. Shoe lover. Coupon Queen. Blogger. Affiliate marketer. Fun gal. Yep, that's me in a few words :)",
                "I'm not your typical Asian girl",
                "No, YOU write something about yourself",
                "- intentionally left blank",
                "I've been having dreams about you and me #youdontknowme #help",
                "Hey it's me on the right with my best friend Tracy. We do everything together :) everything... ;)",
                "Don't mind about him, he's just a friend",
                "Hey I'm new to this, just swipe right and see what happens ;)",
                "I have an addiction to chocolate and I love the beach. Tired of the bar scene. If you want something, you will have to earn it. Southern boys know how to do it better ;)",
                "I'm 5'2'' which means I'm really good at hide and go seek, if you know what I mean ;)",
                "I have always believed in the saying more money more problems but if you have money that ain't a problem"};

        String[] demoImageUrls = {"http://fc03.deviantart.net/fs71/i/2013/265/d/2/female_sniper_stock__viii_by_phelandavion-d6ndob3.jpg?size=300x300",
                "http://www.tellyouall.com/wp-content/uploads/2014/08/iStock_000017987277Medium.jpg?size=300x300",
                "http://www.themarysue.com/wp-content/uploads/2015/02/shestock_20-640x426.jpg?size=300x300",
                "http://fc05.deviantart.net/fs71/i/2012/228/4/3/stock___gothic_emotions_by_mahafsoun-d5bd7m9.jpg?size=300x300",
                "http://www.runnersworld.com/sites/default/files/stock-women-lean-400.jpg?size=300x300",
                "http://www.washingtonpost.com/rf/image_606w/2010-2019/WashingtonPost/2014/02/10/Interactivity/Images/180351639_111392059961.jpg?size=300x300",
                "http://moneymorning.com/files/2014/03/Facebook-stock.jpg?size=300x300",
                "http://ak-hdl.buzzfed.com/static/2014-06/4/6/enhanced/webdr05/enhanced-buzz-wide-17282-1401877284-7.jpg?size=300x300",
                "http://www.runnersworld.com/sites/default/files/stock-women-elbow-back-400.jpg?size=300x300",
                "http://www.femalefirst.co.uk/image-library/land/376/s/stock-woman-reading-book-black-and-white-bw.jpg?size=300x300",
                "https://scstylecaster.files.wordpress.com/2014/02/stock-images-women.jpg?w=507&h=338?size=300x300",
                "https://s-media-cache-ak0.pinimg.com/236x/a6/07/22/a60722f8dd29e14520ca9a0afa46fe71.jpg?size=300x300",
                "http://www.runnersworld.com/sites/default/files/stock-women-ninja-400.jpg?size=300x300",
                "http://fc07.deviantart.net/fs71/i/2014/148/0/8/blue_dress___high_resolution_stock_11_by_liancary_stock-d7k2qum.jpg?size=300x300",
                "http://ak0.picdn.net/shutterstock/videos/7006102/preview/stock-footage-close-up-of-beautiful-african-american-and-asian-women-on-vacation-together.jpg?size=300x300",
                "http://www.doctornerdlove.com/wp-content/uploads/2012/10/iStock_000017606468XSmall.jpg?size=300x300",
                "http://f.tqn.com/y/bullying/1/S/8/3/-/-/iStock_000020963661Medium.jpg?size=300x300",
                "http://www.rantchic.com/wp-content/uploads/2014/10/stock-footage-cute-woman-smiling-while-she-is-on-a-hammock-on-a-sunny-beach.jpg?size=300x300",
                "http://www.thestonk.com/wp-content/uploads/2014/06/yoga-pants-selfies-16.png?size=300x300",
                "http://ak7.picdn.net/shutterstock/videos/1191232/preview/stock-footage-young-women-girls-at-bar-female-friends-and-adult-hispanic-couple-drinking-cocktail-in-pub-dolly.jpg?size=300x300"};

        for(int i=0; i<people.length; i++ ) {
            Person person = new Person();
            person.setId(i);
            person.setName(names[i]);
            person.setAge(ages[i]);
            person.setCourse(courses[i]);
            person.setStatus(statuses[i]);
            person.setDescription(descriptions[i]);
            String[] photos = {demoImageUrls[i]};
            person.setPhotoURLs(photos);
            people[i] = person;
        }
        return people;
    }
}
