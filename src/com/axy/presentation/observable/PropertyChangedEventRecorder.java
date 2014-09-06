package com.axy.presentation.observable;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by adrianaxente on 06.09.2014.
 */
public class PropertyChangedEventRecorder<TSource>
{
    private Map<String, PropertyChangedEventRecord<TSource>>_eventMap = new LinkedHashMap<String, PropertyChangedEventRecord<TSource>>();

    private PropertyChangedEvent<TSource> _propertyChangedEvent;

    private IPropertyChangedListener<TSource> _propertyChangedListener = null;

    private boolean _isRecording = false;

    public PropertyChangedEventRecorder(PropertyChangedEvent<TSource> propertyChangedEvent)
            throws IllegalArgumentException
    {
        if (propertyChangedEvent == null)
        {
            throw new IllegalArgumentException("propertyChangedEvent");
        }

        this._propertyChangedEvent = propertyChangedEvent;
        this._propertyChangedListener =
                new IPropertyChangedListener<TSource>()
                {
                    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
                    @Override
                    public void execute(Object sender, PropertyChangedEventArg<TSource> arg) {
                        String propertyName = arg.getPropertyName();

                        PropertyChangedEventRecorder.this._eventMap.put(
                                propertyName,
                                new PropertyChangedEventRecord<TSource>(sender, arg));
                    }
                };
    }

    public boolean getIsRecording()
    {
        return this._isRecording;
    }

    public void StartRecording()
    {
        this._isRecording = true;
        this._propertyChangedEvent.addEventLister(this._propertyChangedListener);
    }


    public void StopRecording()
    {
        this._propertyChangedEvent.removeEventListener(this._propertyChangedListener);
        this._isRecording = false;
    }

    public void fire(PropertyChangedEvent<TSource> propertyChangedEvent) throws IllegalArgumentException
    {
        if (propertyChangedEvent == null)
            throw new IllegalArgumentException("propertyChangedEvent");

        if (this.getIsRecording())
            this._propertyChangedEvent.removeEventListener(this._propertyChangedListener);

        for(Map.Entry<String, PropertyChangedEventRecord<TSource>> entry: this._eventMap.entrySet())
        {
            PropertyChangedEventRecord<TSource> record = entry.getValue();

            propertyChangedEvent.fire(record.getSender(), record.getEventArgs());
        }

        this._eventMap.clear();

        if (this.getIsRecording())
            this._propertyChangedEvent.addEventLister(this._propertyChangedListener);
    }
}
