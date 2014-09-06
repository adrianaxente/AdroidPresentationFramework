package com.axy.presentation.presentationModel;

import com.axy.presentation.model.AbstractModel;
import com.axy.presentation.model.observable.ObservableModel;
import com.axy.presentation.observable.IPropertyChangedListener;
import com.axy.presentation.observable.PropertyChangedEventArg;
import org.robobinding.itempresentationmodel.AbstractItemPresentationModel;

/**
 * Created by adrianaxente on 04.09.2014.
 */
//todo: implement property maps
public abstract class AbstractItemViewModel<TModel extends ObservableModel<TModel>> extends AbstractItemPresentationModel<TModel> implements IPropertyChangedListener<TModel>
{

    // <editor-fold description="Private Fields">

    private TModel _model;

    private TModel _backupModel;

    private int _index;

    // </editor-fold>


    // <editor-fold description="Getters & Setters">

    public TModel getModel(){ return  this._model; }

    public int getIndex() { return this._index; }


    @Override
    protected void doUpdateData(int i, TModel model) {

        if (model == null)
        {
            throw new IllegalArgumentException("model");
        }

        this._model = model;
        this._index = i;
        this._model.getPropertyChangedEvent().addEventLister(this);
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
