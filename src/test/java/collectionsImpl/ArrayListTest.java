package collectionsImpl;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class ArrayListTest {


    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>(2);
    }


    @After
    public void tearDown() {
        list = null;
    }


    @Test
    public void testAdd() throws Exception {
        list.add("FirstElement");
        list.add("SecondElement");
        list.add("ThirdElement");
        list.add("FourthElement");
        list.add(null);

        Assert.assertEquals("FirstElement", list.get(0));
        Assert.assertEquals("SecondElement", list.get(1));
        Assert.assertEquals("FourthElement", list.get(3));
        Assert.assertNull(list.get(4));
        Assert.assertEquals(list.size(), 5);

    }

    @Test
    public void testAddToSpecificPlace() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");
        list.add(3, "FourthElement");
        list.add(4, null);
        list.add(5, null);

        Assert.assertEquals("FirstElement", list.get(0));
        Assert.assertEquals("SecondElement", list.get(1));
        Assert.assertEquals("FourthElement", list.get(3));
        Assert.assertNull(list.get(4));
        Assert.assertNull(list.get(5));
        Assert.assertEquals(list.size(), 6);
    }

    @Test
    public void testClear() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.clear();

        Assert.assertEquals(0, list.size());

    }

    @Test
    public void testContains() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");

        Assert.assertTrue(list.contains("FirstElement"));
        Assert.assertFalse(list.contains("SomeElement"));
        Assert.assertFalse(list.contains(null));

        list.add(1, null);
        Assert.assertTrue(list.contains(null));
    }

    @Test
    public void testIndexOf() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");
        list.add(3, "ThirdElement");
        list.add(4, null);

        Assert.assertEquals(list.indexOf("ThirdElement"), 2);
        Assert.assertEquals(list.indexOf(null), 4);
        Assert.assertEquals(list.indexOf("SomeElement"), -1);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "FirstElement");
        list.add(2, null);
        list.add(3, null);

        Assert.assertEquals(list.lastIndexOf("FirstElement"), 1);
        Assert.assertEquals(list.lastIndexOf(null), 3);
        Assert.assertEquals(list.lastIndexOf(""), -1);

    }

    @Test
    public void testRemove() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, null);
        list.add(3, null);
        list.add(4, "4");
        list.add(5, "5");

        Assert.assertEquals("5", list.remove(5));
        Assert.assertEquals(5, list.size());
        Assert.assertEquals("4", list.remove(4));
        Assert.assertEquals(4, list.size());
        Assert.assertEquals(null, list.remove(2));
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("FirstElement", list.remove(0));

        list.remove(1);
        list.remove(0);
        Assert.assertEquals(0, list.size());

    }

    @Test
    public void testRemove1() throws Exception {
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(3, "3");
        list.add(4, "4");
        list.add(5, "5");

        Assert.assertEquals(6, list.size());
        Assert.assertEquals(true, list.remove("5"));
        Assert.assertEquals(5, list.size());
        Assert.assertEquals(true, list.remove("4"));
        Assert.assertEquals(4, list.size());
        Assert.assertEquals(true, list.remove("3"));
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(true, list.remove("1"));
        Assert.assertEquals(2, list.size());

        list.remove("2");
        Assert.assertEquals(1, list.size());

        list.remove("0");
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testSet() throws Exception {
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(3, "3");
        list.add(4, "4");
        list.add(5, "5");

        Assert.assertEquals("0", list.set(0, "Zero"));
        Assert.assertEquals(6, list.size());
        Assert.assertEquals("5", list.set(5, "Five"));
        Assert.assertEquals(6, list.size());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(0, list.size());

        list.add(0, "0");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testToArray() throws Exception {
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");
        list.add(3, "FourthElement");
        list.add(4, null);
        Object[] array = list.toArray();

        Assert.assertEquals(array[0], "FirstElement");
        Assert.assertNull(array[4]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() throws Exception {
        list.add(0, "FirstElement");
        list.get(1);
    }

    @Test
    public void testIterator() throws Exception {
        int i = 0;
        list.add(0, "FirstElement");
        list.add(1, "SecondElement");
        list.add(2, "ThirdElement");
        list.add(3, "FourthElement");

        Iterator<String> itr = list.iterator();

        while (itr.hasNext()) {
            Assert.assertEquals(list.get(i++), itr.next());
        }
    }

    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(list.isEmpty());

        list.add(0, "FirstElement");
        Assert.assertFalse(list.isEmpty());
    }

}
