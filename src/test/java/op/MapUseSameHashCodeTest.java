package op;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MapUseSameHashCodeTest {

    @Test
    public void name() {
        Map<KeyObj, String> map = new HashMap<>();

        IntStream.rangeClosed(1, 100)
                .forEach(value -> map.put(new KeyObj(value, "key" + value), "value - " + value));
        System.out.println(map.size());
    }

    private class KeyObj {
        int i;
        String value;

        public KeyObj(int i, String value) {
            this.i = i;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
