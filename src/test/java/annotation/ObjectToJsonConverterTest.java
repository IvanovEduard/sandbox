package annotation;

import annotation.simplejsonconvert.JsonSerializationException;
import annotation.simplejsonconvert.ObjectToJsonConverter;
import annotation.simplejsonconvert.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectToJsonConverterTest {

    @Test
    public void givenObjectSerializedThenTrueReturned() throws JsonSerializationException {
        Person person = new Person("soufiane", "cheouati", "34");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        assertEquals(
                "{\"personAge\":\"34\",\"firstName\":\"Soufiane\",\"lastName\":\"Cheouati\"}",
                jsonString);
    }

}