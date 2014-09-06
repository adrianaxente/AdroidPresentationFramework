package com.axy.presentation.model.observable;

import com.axy.presentation.model.AbstractModel;
import com.axy.presentation.observable.INotifyPropertyChanged;
import com.axy.presentation.observable.PropertyChangedEvent;
import com.axy.presentation.observable.PropertyChangedEventArg;

/**
 * Created by adrianaxente on 07.09.2014.
 */
public abstract class ObservableModel<TThis extends ObservableModel<TThis>> extends AbstractModel<TThis> implements INotifyPropertyChanged<TThis>
{

    protected transient PropertyChangedEvent<TThis> _propertyChangedEvent = new PropertyChangedEvent<TThis>();

    @Override
    public PropertyChangedEvent<TThis> getPropertyChangedEvent()
    {
        return this._propertyChangedEvent;
    }

    protected boolean RaisePropertyChanged(Object sender, String propertyName, Object oldValue, Object newValue)
    {
        if (oldValue != newValue) {
            this._propertyChangedEvent.fire(
                    sender,
                    new PropertyChangedEventArg<TThis>((TThis) this, propertyName, oldValue, newValue));
            return true;
        }

        return false;
    }

    @Override
    public TThis createClone()
    {
        TThis clone = super.createClone();

        if (clone != null)
            clone._propertyChangedEvent = new PropertyChangedEvent<TThis>();

        return clone;
    }
}
