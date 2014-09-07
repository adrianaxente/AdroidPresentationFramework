package com.axy.presentation.observable;

import android.annotation.TargetApi;
import android.os.Build;
import com.axy.presentation.events.IEventListener;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by adrianaxente on 06.09.2014.
 */
public class PropertyChangedEventRecorder<TSource>
{
    private Map<String, PropertyChangedEventArgs>_eventMap = new LinkedHashMap<String, PropertyChangedEventArgs>();

    private PropertyChangedEvent _propertyChangedEvent;

    private IEventListener<PropertyChangedEventArgs> _propertyChangedListener = null;

    private boolean _isRecording = false;

    public PropertyChangedEventRecorder(PropertyChangedEvent propertyChangedEvent)
            throws IllegalArgumentException
    {
        if (propertyChangedEvent == null)
        {
            throw new IllegalArgumentException("propertyChangedEvent");
        }

        this._propertyChangedEvent = propertyChangedEvent;
        this._propertyChangedListener =
                new IPropertyChangeEventListener()
                {
                    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
                    @Override
                    public void onExecute(PropertyChangedEventArgs arg) {
                        String propertyName = arg.getPropertyName();

                        PropertyChangedEventRecorder.this._eventMap.put(
                                propertyName,
                                arg);
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

    public void fire(PropertyChangedEvent propertyChangedEvent) throws IllegalArgumentException
    {
        if (propertyChangedEvent == null)
            throw new IllegalArgumentException("propertyChangedEvent");

        if (this.getIsRecording())
            this._propertyChangedEvent.removeEventListener(this._propertyChangedListener);

        for(Map.Entry<String, PropertyChangedEventArgs> entry: this._eventMap.entrySet())
        {
            PropertyChangedEventArgs record = entry.getValue();

            propertyChangedEvent.fire(entry.getValue());
        }

        this._eventMap.clear();

        if (this.getIsRecording())
            this._propertyChangedEvent.addEventLister(this._propertyChangedListener);
    }
}
