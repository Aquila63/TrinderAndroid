package dave.example.com.trinder;

/**
 * Created by Sofwat on 17/02/2015.
 */
public class Person {


    private int id;
    private String name;
    private String course;
    private int age;
    private String status;
    private String description;
    private String[] photoURLs;

    public Person() {

    }

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

    public void setphotoURLs(String[] photoURLs) {
        this.photoURLs = photoURLs;
    }

    // methods

    public void like() {
        // send off to API
        APIClient.getInstance().like(this);

    }

    public void ignore() {
        // send off to API
        APIClient.getInstance().ignore(this);
    }

    public static Person[] generateSample() {
        Person[] people = new Person[6];
        String[] names = {"Alan", "Brad", "Colin", "David", "Eddie", "Fred"};
        int[] ages = {13, 16, 19, 21, 24, 30};
        String[] courses = {"CSB", "CS", "BESS", "Law", "Science", "Medicine"};
        String[] statuses = {"Single", "In a relationship", "It's complecated", "Single", "In a relationship", "It's complecated"};
        String description = "Hi, I'm new to this, swipe right";

        for(int i=0; i<6; i++ ) {
            Person person = new Person();
            person.setId(i);
            person.setName(names[i]);
            person.setAge(ages[i]);
            person.setCourse(courses[i]);
            person.setStatus(statuses[i]);
            person.setDescription(description);
            people[i] = person;
        }
        return people;
    }
}
