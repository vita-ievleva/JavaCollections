package collectionsImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MyHashMapTest {

    private MyHashMap<String, String> map;

    @Before
    public void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, map.size());

        map.put(null, "");
        map.put(null, "");
        map.put("", "");
        map.put("", "");
        map.put("1", "");

        assertEquals(3, map.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(map.isEmpty());
        map.put("1", "");
        assertFalse(map.isEmpty());
    }

    @Test
    public void testContainsKey() throws Exception {
        map.put("0", "0");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        Stream.of("0", "1", "2", "3", "4").forEach(v -> assertEquals(true, map.containsKey(v)));
        Stream.of(1, "-1", "string", null).forEach(v -> assertEquals(false, map.containsKey(v)));

        map.put(null, "null");
        assertEquals(true, map.containsKey(null));
        assertEquals(6, map.size());
    }

    @Test
    public void testContainsValue() throws Exception {
        map.put("0", "0");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", null);

        Stream.of(null, "0", "1", "2", "3", "4").forEach(v -> assertEquals(true, map.containsValue(v)));
        Stream.of(1, "-1", "string").forEach(v -> assertEquals(false, map.containsValue(v)));
    }

    @Test
    public void testPut() throws Exception {
        String[] expected = new String[30];
        for (int i = 0; i < 29; i++) {
            expected[i] = String.valueOf(i); // prepare expected result
            map.put("" + i, "" + i); // put entries into map
        }
        assertEquals(29, map.size());

        map.put(null, null);
        expected[29] = null;

        Stream.of(expected).forEach(value -> assertEquals(value, map.get("" + value)));
    }

    @Test
    public void testRemove() throws Exception {
        map.put("0", "0");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put(null, null);
        assertEquals(6, map.size());

        Stream.of("0", "1", "2", "3", "4", null).forEach(v -> assertEquals(v, map.remove(v)));
        assertEquals(0, map.size());

        Stream.of("0", "1", "2", "3", "4", null, null).forEach(v -> assertEquals(null, map.remove(v)));
        assertEquals(0, map.size());
    }


    @Test
    public void testClear() throws Exception {
        map.put("0", "0");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put(null, null);

        map.clear();

        assertEquals(0, map.size());
    }

}