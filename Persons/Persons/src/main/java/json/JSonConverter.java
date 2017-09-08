package json;

import com.google.gson.Gson;
import Entity.Person;
import java.util.List;

public class JSonConverter {

    public static Person getPersonFromJson(String js) {
        return new Gson().fromJson(js, Person.class);
    }

    public static String getJSONFromPerson(Person p) {
        return new Gson().toJson(p);
    }

    public static String getJSONFromPerson(List<Person> persons) {
        return new Gson().toJson(persons);
    }
}
