package model;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface IEntity {

    /**
     * Saves the model into the database
     */
    void save() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException;

    /**
     * removes the model from the database
     */
    void delete();

    /**
     * sets the model's data by the id which is stored in the database.
     */
    void view();

    /**
     * updates a model's values in the database.
     */
    void edit();

    /**
     * Fills all Properties with the data Object array's values
     * @param data data which is used to fill the model's variables
     * @param  hasId true or false depending if the id is supposed to be set
     */
    void patchData(Object[] data, boolean hasId);

}
