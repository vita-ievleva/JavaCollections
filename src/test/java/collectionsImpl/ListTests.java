package collectionsImpl;

import interfaces.CustomList;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.Assert.*;


public abstract class ListTests {

    private CustomList<String> list;

    abstract CustomList getList();

    @Before
    public void setUp() {
        list = getList();
    }


    @Test
    public void testAdd() throws Exception {
        list.add("FirstElement");
        list.add("SecondElement");
        list.add("ThirdElement");
        list.add("FourthElement");
        list.add(null);

        assertEquals("FirstElement", list.get(0));
        assertEquals("SecondElement", list.get(1));
        assertEquals("FourthElement", list.get(3));
        assertNull(list.get(4));
        assertEquals(list.size(), 5);

    }

    @Test
    public void testAddToSpecificPlace() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");
        list.add(3, "FourthElement");
        list.add(4, null);
        list.add(5, null);

        assertEquals("FirstElement", list.get(0));
        assertEquals("SecondElement", list.get(1));
        assertEquals("FourthElement", list.get(3));
        assertNull(list.get(4));
        assertNull(list.get(5));
        assertEquals(list.size(), 6);
    }

    @Test
    public void testClear() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.clear();

        assertEquals(0, list.size());

    }

    @Test
    public void testContains() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");

        assertTrue(list.contains("FirstElement"));
        assertFalse(list.contains("SomeElement"));
        assertFalse(list.contains(null));

        list.add(1, null);
        assertTrue(list.contains(null));
    }

    @Test
    public void testIndexOf() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");
        list.add(3, "ThirdElement");
        list.add(4, null);

        assertEquals(list.indexOf("ThirdElement"), 2);
        assertEquals(list.indexOf(null), 4);
        assertEquals(list.indexOf("SomeElement"), -1);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "FirstElement");
        list.add(2, null);
        list.add(3, null);

        assertEquals(list.lastIndexOf("FirstElement"), 1);
        assertEquals(list.lastIndexOf(null), 3);
        assertEquals(list.lastIndexOf(""), -1);

    }

    @Test
    public void testRemove() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, null);
        list.add(3, null);
        list.add(4, "4");
        list.add(5, "5");

        assertEquals("5", list.remove(5));
        assertEquals(5, list.size());
        assertEquals("4", list.remove(4));
        assertEquals(4, list.size());
        assertEquals(null, list.remove(2));
        assertEquals(3, list.size());
        assertEquals("FirstElement", list.remove(0));

        list.remove(1);
        assertEquals(1, list.size());

    }

    @Test
    public void testRemove1() throws Exception {
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(3, "3");
        list.add(4, "4");
        list.add(5, "5");

        Stream.of("1", "2", "3", "4", "5").forEach(item -> assertEquals(true, list.remove(item)));

        assertEquals(1, list.size());

    }

    @Test
    public void testSet() throws Exception {
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(3, "3");
        list.add(4, "4");
        list.add(5, "5");

        assertEquals("0", list.set(0, "Zero"));
        assertEquals(6, list.size());
        assertEquals("5", list.set(5, "Five"));
        assertEquals(6, list.size());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, list.size());

        list.add(0, "0");
        assertEquals(1, list.size());
    }

    @Test
    public void testToArray() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, null);
        Object[] array = list.toArray();

        assertEquals(array[0], "FirstElement");
        assertNull(array[1]);
    }

    @Test
    public void testGet() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");

        assertEquals(list.get(0), "FirstElement");
        assertEquals(list.get(1), "SecondElement");
        assertEquals(list.get(2), "ThirdElement");

        list.add(3, "FourthElement");

        assertEquals(list.get(0), "FirstElement");
        assertEquals(list.get(1), "SecondElement");
        assertEquals(list.get(2), "ThirdElement");
        assertEquals(list.get(3), "FourthElement");
    }

    @Test
    public void testIterator() throws Exception {
        int i = 0;
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");
        list.add(3, "FourthElement");

        String[] expected = {"FirstElement", "SecondElement", "ThirdElement", "FourthElement"};

        Iterator<String> itr = list.iterator();

        while (itr.hasNext()) {
            assertEquals(expected[i++], itr.next());
        }
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(list.isEmpty());

        list.add(0, "FirstElement");
        assertFalse(list.isEmpty());
    }

}
