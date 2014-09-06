package com.axy.presentation.observable;

import com.axy.presentation.events.GenericEventArg;

/**
 * Created by adrianaxente on 04.09.2014.
 */
public class PropertyChangedEventArg<TSource> extends GenericEventArg<TSource>
{
    // <editor-fold description="Private Fields">


    private String _propertyName;
    private Object _oldValue;
    private Object _newValue;

    // </editor-fold>

    // <editor-fold description="Constructor">

    public PropertyChangedEventArg(TSource source, String _propertyName, Object newValue, Object oldValue)
    {
        super(source);
        this._propertyName = _propertyName;
        this._newValue = newValue;
        this._oldValue = oldValue;
    }

    // </editor-fold>

    // <editor-fold description="Getters & Setters">

    public String getPropertyName() { return  this._propertyName; }
    public Object getOldValue() { return  this._oldValue; }
    public Object getNewValue() { return  this._newValue; }

    // </editor-fold>
}