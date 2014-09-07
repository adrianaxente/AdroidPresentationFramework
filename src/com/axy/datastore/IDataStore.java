package com.axy.datastore;

import com.axy.misc.ICloseable;

import java.util.List;

/**
 * Created by adrianaxente on 07.09.2014.
 */
public interface IDataStore<TID> extends ICloseable
{
    <T> T store(T entity) throws IllegalArgumentException;

    <T> T delete(T entity) throws IllegalArgumentException;;

    <T> List<T> getAll();

    //todo: change this to a GUID, it will be more appropriate
    <T> T getById(TID id);

    <T> TID getId(T entity) throws IllegalArgumentException;

    IDataStoreConfiguration getConfiguration();
}
