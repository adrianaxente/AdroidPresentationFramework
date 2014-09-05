package com.axy.presentation.events;

import java.util.LinkedList;

/**
 * Created by adrianaxente on 01.09.2014.
 */
public class Event
{
    private LinkedList<IEventListener> _listeners;

    public void addEventLister(IEventListener listener)
    {
        if (listener == null)
        {
            throw new NullPointerException("listener is null");
        }

        if (this._listeners == null)
        {
            this._listeners = new LinkedList<IEventListener>();
        }

        this._listeners.add(listener);
    }

    public void removeEventListener(IEventListener listener)
    {
        if (listener == null)
        {
            throw new NullPointerException("listener is null");
        }

        if (this._listeners == null)
        {
            return;
        }

        this._listeners.removeFirstOccurrence(listener);

        if (this._listeners.isEmpty())
        {
            this._listeners = null;
        }
    }

    public void fire(Object sender, EventArg arg)
    {
        if (arg == null)
        {
            throw new NullPointerException("arg is null");
        }

        for(IEventListener listener : this._listeners)
        {
            listener.execute(sender, arg);
        }
    }

}
