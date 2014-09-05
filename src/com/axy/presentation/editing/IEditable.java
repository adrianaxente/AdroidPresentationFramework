package com.axy.presentation.editing;

/**
 * Created by adrianaxente on 06.09.2014.
 */
public interface IEditable {
    void beginEdit();
    void endEdit(boolean commitChanges);
    boolean getIsEditing();
    //test
}
