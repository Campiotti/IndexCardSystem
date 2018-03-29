package model;

public interface IEntity {

    public abstract void save();

    public abstract void delete();

    public abstract void view(int id);

    public abstract void edit();


}
