package collectionsImpl;


import interfaces.CustomList;


public class ArrayListTest extends ListTests {

    @Override
    CustomList getList() {
        return new ArrayList<>();
    }
}
