package com.axy.presentation.model;

import com.axy.presentation.editing.IEditable;
import com.axy.presentation.events.PropertyChangedEvent;
import com.axy.presentation.events.PropertyChangedEventArg;

/**
 * Created by adrianaxente on 03.09.2014.
 */
public abstract class AbstractModel<TThis extends AbstractModel<TThis>> implements Cloneable
{

    // <editor-fold description="Fields">

    public transient PropertyChangedEvent<TThis> propertyChangedEvent = new PropertyChangedEvent<TThis>();

    // </editor-fold>

    // <editor-fold description="Identity">

    /**
     * Gets the identity of this instance
     * @return
     */
    public abstract long getId();

    // </editor-fold>

    // <editor-fold description="Cloning & copying">

    /**
     * Copies the properties of teh source object into this object
     * @param source
     * @throws IllegalArgumentException
     */
    public void copyFrom(TThis source) throws IllegalArgumentException
    {
        if (source == null)
        {
            throw new IllegalArgumentException("source");
        }
    }

    /**
     * Clones the object into a new instance
     * @return the cloned object
     */
    public TThis createClone()
    {
        try
        {
            return (TThis)this.clone();
        }
        catch (CloneNotSupportedException ex)
        {
            //todo: log the exception
            return null;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    // </editor-fold>

    // <editor-fold description="Observation">

    protected boolean RaisePropertyChanged(Object sender, String propertyName, Object oldValue, Object newValue)
    {
        if (oldValue != newValue) {
            this.propertyChangedEvent.fire(
                    sender,
                    new PropertyChangedEventArg<TThis>((TThis) this, propertyName, oldValue, newValue));
            return true;
        }

        return false;
    }

    // </editor-fold>
}
