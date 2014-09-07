package com.axy.presentation.events;

import java.util.LinkedList;

/**
 * Created by adrianaxente on 01.09.2014.
 */
public class Event<TEventArg extends EventArgs>
{
    private LinkedList<IEventListener<TEventArg>> _listeners;

    public void addEventLister(IEventListener<TEventArg> listener)
    {
        if (listener == null)
            throw new NullPointerException("listener is null");

        if (this._listeners == null)
            this._listeners = new LinkedList<IEventListener<TEventArg>>();

        this._listeners.add(listener);
    }

    public void removeEventListener(IEventListener<TEventArg> listener)
    {
        if (listener == null)
            throw new NullPointerException("listener is null");

        if (this._listeners == null)
            return;

        this._listeners.removeFirstOccurrence(listener);

        if (this._listeners.isEmpty())
            this._listeners = null;
    }

    public void fire(TEventArg arg)
    {
        if (arg == null)
            throw new NullPointerException("arg is null");

        if (this._listeners == null)
            return;

        for(IEventListener<TEventArg> listener : this._listeners)
            listener.onExecute(arg);
    }
}
