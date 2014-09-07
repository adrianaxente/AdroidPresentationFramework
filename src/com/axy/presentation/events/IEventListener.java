package com.axy.presentation.events;

/**
 * Created by adrianaxente on 01.09.2014.
 */
public interface IEventListener<TEventArg extends EventArgs>
{
    void onExecute(TEventArg args);
}

