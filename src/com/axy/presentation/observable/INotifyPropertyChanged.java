package com.axy.presentation.observable;

/**
 * Created by adrianaxente on 07.09.2014.
 */
public interface INotifyPropertyChanged<TSource>
{
    PropertyChangedEvent<TSource> getPropertyChangedEvent();
}
