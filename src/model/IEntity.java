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

    /**
     * Gets a string corresponding to the key/type parameter given
     * @param key String param to identify which string is wanted.
     */
    String getText(String key);

    /**
     * Sets a value of the model by the corresponding key given.
     * @param key String to identify which key/value.
     * @param value Object value which to give that key.
     */
    void setText(String key, Object value);

}
