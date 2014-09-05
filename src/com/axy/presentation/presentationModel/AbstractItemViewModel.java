package com.axy.presentation.presentationModel;

import com.axy.presentation.model.AbstractModel;
import com.axy.presentation.events.IPropertyChangedListener;
import com.axy.presentation.events.PropertyChangedEventArg;
import org.robobinding.itempresentationmodel.AbstractItemPresentationModel;

/**
 * Created by adrianaxente on 04.09.2014.
 */
//todo: implement property maps
public abstract class AbstractItemViewModel<TModel extends AbstractModel<TModel>> extends AbstractItemPresentationModel<TModel> implements IPropertyChangedListener<TModel>
{

    // <editor-fold description="Private Fields">

    private TModel _model;

    private TModel _backupModel;

    private int _index;

    // </editor-fold>


    // <editor-fold description="Getters & Setters">

    public TModel getModel(){ return  this._model; }

    public int getIndex() { return this._index; }

    public boolean getIsInTransaction()
    {
        return this._backupModel != null;
    }

    // </editor-fold>

    // <editor-fold description="Public Methods">

    public void startTransaction()
    {
        this._backupModel = this._model;
        this._model = this._model.createClone();
    }

    public void commitTransaction()
    {
        this._backupModel.copyFrom(this._model);
        this._model = this._backupModel;
        this._backupModel = null;

        //todo: fire property changes if any
    }

    public void rollbackTransaction()
    {
        this._model = this._backupModel;
        this._backupModel = null;

        //todo: fire property changes if any
    }

    // </editor-fold>

    @Override
    protected void doUpdateData(int i, TModel model) {

        if (model == null)
        {
            throw new IllegalArgumentException("model");
        }

        this._model = model;
        this._index = i;
        this._model.propertyChangedEvent.addEventLister(this);
    }

    @Override
    public void execute(Object sender, PropertyChangedEventArg<TModel> arg)
    {
        if (sender != this) {
            this.refreshPresentationModel();
            //this.firePropertyChange(arg.getPropertyName());
        }
    }
}
