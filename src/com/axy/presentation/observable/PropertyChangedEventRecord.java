package com.axy.presentation.observable;

/**
 * Created by adrianaxente on 06.09.2014.
 */
public class PropertyChangedEventRecord<TSource>
{
    private Object _sender;
    private PropertyChangedEventArg<TSource> _eventArgs;

    public PropertyChangedEventRecord(Object sender, PropertyChangedEventArg<TSource> eventArgs)
    {
        this._sender = sender;
        this._eventArgs = eventArgs;
    }

    public PropertyChangedEventArg<TSource> getEventArgs()
    {
        return _eventArgs;
    }

    public Object getSender()
    {
        return  this._sender;
    }


}
