package com.axy.presentation.events;


/**
 * Created by adrianaxente on 01.09.2014.
 */

public class EventArgs
{
    public static final EventArgs EMPTY = new EventArgs();

    private Object _sender;

    public EventArgs()
    {
    }

    public EventArgs(Object sender)
    {
        this._sender = sender;
    }

    public Object getSender()
    {
        return _sender;
    }

}

