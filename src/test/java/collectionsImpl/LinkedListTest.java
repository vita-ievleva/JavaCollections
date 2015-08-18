package collectionsImpl;

import interfaces.CustomList;


public class LinkedListTest extends ListTests {

    @Override
    CustomList getList() {
        return new LinkedList<>();
    }
}