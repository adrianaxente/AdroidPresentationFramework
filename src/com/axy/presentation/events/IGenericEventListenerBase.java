package com.axy.presentation.events;

/**
 * Created by adrianaxente on 01.09.2014.
 */
public interface IGenericEventListenerBase<TSource, TEventArg extends GenericEventArg<TSource>>
{
    void execute(Object sender, TEventArg arg);
}

