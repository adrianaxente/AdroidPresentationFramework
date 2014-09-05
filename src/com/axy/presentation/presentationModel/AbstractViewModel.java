package com.axy.presentation.presentationModel;

import com.axy.presentation.editing.IEditable;
import com.axy.presentation.model.AbstractModel;
import com.axy.presentation.events.IPropertyChangedListener;
import com.axy.presentation.events.PropertyChangedEventArg;
import org.robobinding.presentationmodel.AbstractPresentationModel;

/**
 * Created by adrianaxente on 04.09.2014.
 */
//todo: implement property maps
public abstract class AbstractViewModel<TModel extends AbstractModel<TModel>> extends AbstractPresentationModel implements IPropertyChangedListener<TModel>, IEditable
{

    // <editor-fold description="Private Fields">

    private TModel _model;

    private TModel _backupModel;

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
        this._model.propertyChangedEvent.addEventLister(this);
    }

    // </editor-fold>

    // <editor-fold description="Getters & Setters">

    public TModel getModel(){ return  this._model; }

    @Override
    public void execute(Object sender, PropertyChangedEventArg<TModel> arg)
    {
        if (sender != this) {
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

        this._model.propertyChangedEvent.removeEventListener(this);
        this._backupModel = this._model;
        this._model = this._model.createClone();
        this._model.propertyChangedEvent.addEventLister(this);

    }

    @Override
    public void endEdit(boolean commitChanges)
    {
        if (!this.getIsEditing())
            return;

        this._model.propertyChangedEvent.removeEventListener(this);

        if (commitChanges) {
            this._backupModel.copyFrom(this._model);
            //todo: raise the recorded property changes on the backup model
        }

        this._model = this._backupModel;
        this._backupModel = null;
        this._model.propertyChangedEvent.addEventLister(this);
    }

    @Override
    public boolean getIsEditing()
    {
        return this._backupModel != null;
    }

    // </editor-fold">
}
