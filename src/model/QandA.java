package model;

public interface QandA {

    /**
     * Gets a string corresponding to the key/type parameter given
     * @param key String param to identify which string is wanted.
     */
    Object getValue(String key);

    /**
     * Sets a value of the model by the corresponding key given.
     * @param key String to identify which key/value.
     * @param value Object value which to give that key.
     */
    void setText(String key, Object value);
}
