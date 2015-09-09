package collectionsImpl;


import interfaces.CustomList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements CustomList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public void add(E value) {
        add(size, value);
    }

    public void add(int index, E value) {
        isIndexValid(index);

        Node<E> node = new Node<>(value);

        if (isEmpty()) {
            first = last = node;

        } else if (index == size) {
            last.next = node;
            node.prev = last;
            last = node;

        } else if (index == 0) {
            first.prev = node;
            node.next = first;
            first = node;

        } else {
            Node<E> nodeToFind = searchNode(index);

            nodeToFind.prev.next = node;
            nodeToFind.prev = node;
            node.next = nodeToFind;
            node.prev = nodeToFind.prev;
        }
        size++;
    }

    public E get(int index) {
        return searchNode(index).value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> temp = first; temp != null; temp = temp.next)
            result[i++] = temp.value;
        return result;
    }

    public E set(int index, E newElement) {
        Node<E> nodeFoundByIndex = searchNode(index);
        E item = nodeFoundByIndex.value;
        nodeFoundByIndex.value = newElement;
        return item;
    }

    public int indexOf(Object obj) {
        int index = 0;

        for (Node<E> temp = first; temp != null; temp = temp.next) {
            if (obj == null ? temp.value == null : obj.equals(temp.value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        int index = size - 1;

        for (Node<E> temp = last; temp != null; temp = temp.prev) {
            if (obj == null ? temp.value == null : obj.equals(temp.value)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public E remove(int index) {
        isIndexValid(index);
        E removedValue;

        if (index == 0 && size > 0) {
            removedValue = removeFirst();
        } else if (index == size - 1) {
            removedValue = removeLast();
        } else {
            Node<E> temp = searchNode(index);
            removedValue = temp.value;

            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
        }
        size--;
        return removedValue;
    }

    private E removeLast() {
        E old = last.value;
        last.prev.next = null;
        last = last.prev;

        return old;
    }

    private E removeFirst() {
        E old = first.value;
        first.next.prev = null;
        first = first.next;

        return old;
    }

    @Override
    public boolean remove(Object object) {
        int i = 0;
        if (object == null) {
            for (Node<E> temp = first; temp != null; temp = temp.next) {
                if (temp.value == null) {
                    remove(i);
                    return true;
                }
                i++;
            }
        } else {
            for (Node<E> temp = first; temp != null; temp = temp.next) {
                if (object.equals(temp.value)) {
                    remove(i);
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public void clear() {
        for (Node<E> i = first; i != null; ) { // unlink nodes from first till the end of list
            Node<E> temp = i.next; // hold link to the next node
            i.value = null;
            i.next = null;
            i.prev = null;
            i = temp; // go to next node to unlink its values
        }
        size = 0;
        first = last = null;
    }

    private Node<E> searchNode(int index) {
        isIndexValid(index);
        Node<E> temp;

        if (index > size / 2) {
            temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
            return temp;
        }

        temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void isIndexValid(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<E> {
        private Node<E> lastReturned;
        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            lastReturned = searchNode(currentIndex);
            currentIndex++;

            return (E) lastReturned.value;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            LinkedList.this.remove(indexOf(lastReturned.value));
            currentIndex--;
        }
    }

    private class Node<E> {
        private E value;
        private Node<E> next, prev;

        Node(E element) {
            this.value = element;
        }
    }
}
