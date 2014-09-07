package com.axy.presentation.presentationModel;

import com.axy.presentation.editing.IEditable;
import com.axy.presentation.events.IEventListener;
import com.axy.presentation.misc.ICloseable;
import com.axy.presentation.model.observable.ObservableModel;
import com.axy.presentation.observable.IPropertyChangeEventListener;
import com.axy.presentation.observable.PropertyChangedEventArgs;
import com.axy.presentation.observable.PropertyChangedEventRecorder;
import org.robobinding.presentationmodel.AbstractPresentationModel;

/**
 * Created by adrianaxente on 04.09.2014.
 */
//todo: implement property maps
public abstract class AbstractViewModel<TModel extends ObservableModel<TModel>> extends AbstractPresentationModel implements IPropertyChangeEventListener, IEditable, ICloseable
{

    // <editor-fold description="Private Fields">

    private TModel _model;

    private TModel _backupModel;

    private PropertyChangedEventRecorder<TModel> _propertyChangeRecorder;

    // </editor-fold>

    // <editor-fold description="Constructor">

    /**
     * Constructs an instance of the com.axy.presentation.Presentation.AbstractViewModel
     * @param model
     * @throws IllegalArgumentException
     */
    public AbstractViewModel(TModel model) throws IllegalArgumentException
    {
        super();

        if (model == null)
        {
            throw new IllegalArgumentException("model");
        }

        this._model = model;
        this._model.getPropertyChangedEvent().addEventLister(this);
    }

    // </editor-fold>

    // <editor-fold description="Getters & Setters">

    public TModel getModel(){ return  this._model; }

    @Override
    public void onExecute(PropertyChangedEventArgs arg)
    {
        if (arg != null && arg.getSender() != this) {
            this.refreshPresentationModel();
            //this.firePropertyChange(arg.getPropertyName());
        }
    }

    // <editor-fold description="Editing">

    @Override
    public void beginEdit()
    {
        if (this.getIsEditing())
            return;

        this._model.getPropertyChangedEvent().removeEventListener(this);
        this._backupModel = this._model;
        this._model = this._model.createClone();
        this._model.getPropertyChangedEvent().addEventLister(this);
        this._propertyChangeRecorder = new PropertyChangedEventRecorder<TModel>(this._model.getPropertyChangedEvent());
        this._propertyChangeRecorder.StartRecording();
    }

    @Override
    public void endEdit(boolean commitChanges)
    {
        if (!this.getIsEditing())
            return;

        this._model.getPropertyChangedEvent().removeEventListener(this);

        if (commitChanges) {
            this._backupModel.copyFrom(this._model);
            this._propertyChangeRecorder.StopRecording();
            this._propertyChangeRecorder.fire(this._backupModel.getPropertyChangedEvent());
        }

        this._propertyChangeRecorder = null;
        this._model = this._backupModel;
        this._backupModel = null;
        this._model.getPropertyChangedEvent().addEventLister(this);
    }

    @Override
    public boolean getIsEditing()
    {
        return this._backupModel != null;
    }

    // </editor-fold>

    // <editor-fold description="closable">

    @Override
    public void close()
    {
        this._model.getPropertyChangedEvent().removeEventListener(this);
    }

    // </editor-fold>
}
