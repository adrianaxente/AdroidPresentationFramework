package com.axy.presentation.observable.colections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by adrianaxente on 07.09.2014.
 */
public class ListObservableWrapper<T> implements  IObservableList<T>
{
    // <editor-fold description="Fields">

    private List<T> _wrappedList;

    private CollectionChangedEvent _collectionChangedEvent = new CollectionChangedEvent();

    // </editor-fold>

    // <editor-fold description="Constructor">

    public ListObservableWrapper(List<T> wrappedList) throws IllegalArgumentException
    {
        if (wrappedList == null)
            throw new IllegalArgumentException("wrappedList");

        this._wrappedList = wrappedList;
    }

    // </editor-fold>

    // <editor-fold description="INotifyCollectionChanged Members">

    @Override
    public CollectionChangedEvent getCollectionChangedEvent() {
        return this._collectionChangedEvent;
    }

    // </editor-fold>

    // <editor-fold description="List Members">

    @Override
    public void add(int location, T object) {
        this._wrappedList.add(location, object);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
    }

    @Override
    public boolean add(T object) {
        boolean result = this._wrappedList.add(object);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean addAll(int location, Collection<? extends T> collection) {
        boolean result = this._wrappedList.addAll(location, collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean result = this._wrappedList.addAll(collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public void clear() {
        this._wrappedList.clear();
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
    }

    @Override
    public boolean contains(Object object) {
        return this._wrappedList.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this._wrappedList.containsAll(collection);
    }

    @Override
    public boolean equals(Object object) {
        return this._wrappedList.equals(object);
    }

    @Override
    public T get(int location) {
        return this._wrappedList.get(location);
    }

    @Override
    public int hashCode() {
        return this._wrappedList.hashCode();
    }

    @Override
    public int indexOf(Object object) {
        return this._wrappedList.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return this._wrappedList.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return this._wrappedList.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return this._wrappedList.lastIndexOf(object);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this._wrappedList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int location) {
        return this._wrappedList.listIterator(location);
    }

    @Override
    public T remove(int location) {
        T result = this._wrappedList.remove(location);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean remove(Object object) {
        boolean result = this._wrappedList.remove(object);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean result = this._wrappedList.removeAll(collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean result = this._wrappedList.retainAll(collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public T set(int location, T object) {
        T result = this._wrappedList.set(location, object);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public int size() {
        return this._wrappedList.size();
    }

    @Override
    public List<T> subList(int start, int end) {
        return this._wrappedList.subList(start, end);
    }

    @Override
    public Object[] toArray() {
        return this._wrappedList.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        return this._wrappedList.toArray(array);
    }

    // </editor-fold>
}
