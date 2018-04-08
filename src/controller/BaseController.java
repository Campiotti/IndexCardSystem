package controller;

import helper.ErrorLogger;
import model.BaseModel;
import model.IEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

abstract class BaseController implements IController{

    @Override
    public void saveModel(IEntity model) {
        try {
            model.save();
        } catch (SQLException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteModel(IEntity model) {
        model.delete();
    }

    @Override
    public void updateModel(IEntity model) {
        model.edit();
    }
}
