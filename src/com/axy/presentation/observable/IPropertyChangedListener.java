package com.axy.presentation.observable;

import com.axy.presentation.events.IGenericEventListenerBase;

/**
 * Created by adrianaxente on 04.09.2014.
 */
public interface IPropertyChangedListener<TSource> extends IGenericEventListenerBase<TSource, PropertyChangedEventArg<TSource>>
{
}
