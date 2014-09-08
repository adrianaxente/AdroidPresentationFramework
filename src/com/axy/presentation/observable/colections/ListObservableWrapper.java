package com.axy.presentation.observable.colections;

import android.util.Log;
import com.axy.misc.IGetObject;

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

    private IGetObject<List<T>> _wrappedGetter;

    private CollectionChangedEvent _collectionChangedEvent = new CollectionChangedEvent();

    // </editor-fold>

    // <editor-fold description="Constructor">

    public ListObservableWrapper(final List<T> wrappedList)
            throws IllegalArgumentException
    {
        if (wrappedList == null)
            throw new IllegalArgumentException("wrappedList");

        this._wrappedGetter = new IGetObject<List<T>>() {
            @Override
            public List<T> getObject() {
                return  wrappedList;
            }
        };
    }

    public ListObservableWrapper(IGetObject<List<T>> wrappedListGetter)
            throws IllegalArgumentException
    {
        if (wrappedListGetter == null)
            throw new IllegalArgumentException("wrappedList");

        this._wrappedGetter = wrappedListGetter;
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
        this.getWrappedList().add(location, object);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
    }

    @Override
    public boolean add(T object) {

        boolean result = this.getWrappedList().add(object);

        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean addAll(int location, Collection<? extends T> collection) {
        boolean result = this.getWrappedList().addAll(location, collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean result = this.getWrappedList().addAll(collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public void clear() {
        this.getWrappedList().clear();
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
    }

    @Override
    public boolean contains(Object object) {
        return this.getWrappedList().contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.getWrappedList().containsAll(collection);
    }

    @Override
    public boolean equals(Object object) {
        return this.getWrappedList().equals(object);
    }

    @Override
    public T get(int location) {
        return this.getWrappedList().get(location);
    }

    @Override
    public int hashCode() {
        return this.getWrappedList().hashCode();
    }

    @Override
    public int indexOf(Object object) {
        return this.getWrappedList().indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return this.getWrappedList().isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return this.getWrappedList().iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return this.getWrappedList().lastIndexOf(object);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.getWrappedList().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int location) {
        return this.getWrappedList().listIterator(location);
    }

    @Override
    public T remove(int location) {
        T result = this.getWrappedList().remove(location);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean remove(Object object) {
        boolean result = this.getWrappedList().remove(object);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean result = this.getWrappedList().removeAll(collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean result = this.getWrappedList().retainAll(collection);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public T set(int location, T object) {
        T result = this.getWrappedList().set(location, object);
        this._collectionChangedEvent.fire(new CollectionChangedEventArgs());
        return result;
    }

    @Override
    public int size() {
        return this.getWrappedList().size();
    }

    @Override
    public List<T> subList(int start, int end) {
        return this.getWrappedList().subList(start, end);
    }

    @Override
    public Object[] toArray() {
        return this.getWrappedList().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        return this.getWrappedList().toArray(array);
    }

    // </editor-fold>

    protected List<T> getWrappedList()
    {
        return this._wrappedGetter.getObject();
    }
}
