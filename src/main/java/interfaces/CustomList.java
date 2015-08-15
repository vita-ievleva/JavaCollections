package interfaces;

import java.util.Iterator;

public interface CustomList<E> {
    void add(E e);

    void add(int index, E element);

    void clear();

    boolean contains(Object o);

    int lastIndexOf(Object o);

    int indexOf(Object o);

    E remove(int index);

    boolean remove(E o);

    E set(int index, E element);

    int size();

    Object[] toArray();

    boolean isEmpty();

    E get(int index);

    int hashCode();

    Iterator<E> iterator();


}
