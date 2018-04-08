package controller;

import model.IEntity;

public interface IController {

    void saveModel(IEntity model);
    void updateModel(IEntity model);
    void deleteModel(IEntity model);
    //IEntity getModel(int id);

}
