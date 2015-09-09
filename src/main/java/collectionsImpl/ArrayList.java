package collectionsImpl;

import interfaces.CustomList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] array;

    public ArrayList(int capacity) {
        if (capacity > 0) {
            this.array = new Object[capacity];
        } else if (capacity == 0) {
            this.array = new Object[]{};
        } else {
            throw new RuntimeException("Capacity should be >= 0, but was " + capacity);
        }
    }

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E element) {
        add(size, element);

    }

    @Override
    public void add(int index, E element) {
        if (size < array.length) {
            System.arraycopy(array, index, array, index + 1, size - index);
        } else { // increase array capacity
            Object[] expandedArray = new Object[(array.length + (array.length >> 1))];
            System.arraycopy(array, 0, expandedArray, 0, index); // copy first part of array to new one
            System.arraycopy(array, index, array = expandedArray, index + 1, size - index); // copy second part of array
        }
        array[index] = element;
        size++;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int index = size - 1; index >= 0; index--) {
            if (o == null && array[index] == null) {
                return index;
            } else if (o != null && o.equals(array[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(Object o) {
        for (int index = 0; index < size; index++) {
            if (o == null && array[index] == null) {
                return index;
            } else if (o != null && o.equals(array[index])) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public E remove(int index) {
        isIndexValid(index); // if index is valid - remove this element
        @SuppressWarnings("unchecked")
        E removedElement = (E) array[index];

        if (index == 0) {
            System.arraycopy(array, index + 1, array, index, size);
        } else if (index == size - 1) {
            System.arraycopy(array, 0, array, 0, size - 1); // copy first part of array to new one
        } else {
            System.arraycopy(array, index + 1, array, index, size - index - 1); // copy first part of array to new one
        }
        array[--size] = null;
        return removedElement;
    }

    @Override
    public boolean remove(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(array[i])) {
                    remove(i);
                    return true;

                }
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        isIndexValid(index);
        @SuppressWarnings("unchecked")
        E elementToChange = (E) array[index];
        array[index] = element;

        return elementToChange;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int index) {
        isIndexValid(index);

        return (E) array[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr<E> implements Iterator<E> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (currentIndex == size) {
                throw new NoSuchElementException();
            }
            return (E) array[currentIndex++];
        }

        @Override
        public void remove() {
            if (currentIndex == 0) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(--currentIndex);
        }
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

}
