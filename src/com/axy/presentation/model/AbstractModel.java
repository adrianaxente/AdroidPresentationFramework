package com.axy.presentation.model;

/**
 * Created by adrianaxente on 03.09.2014.
 */
public abstract class AbstractModel<TThis extends AbstractModel<TThis>> implements Cloneable
{
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
            TThis clone =  (TThis)this.clone();
            return  clone;
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
}
