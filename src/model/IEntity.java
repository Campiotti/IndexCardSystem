package model;

public interface IEntity {

    /**
     * Saves the model into the database
     */
    void save();

    /**
     * removes the model from the database
     */
    void delete();

    /**
     * sets the model's data by the id which is stored in the database.
     * @param id int database id which is stored in the database, used to identify model.
     */
    void view(int id);

    /**
     * updates a model's values in the database.
     */
    void edit();


}
