package model;

public abstract class BaseEntity implements IEntity {

    private int id=-1;

    @Override
    public abstract void save();

    @Override
    public abstract void delete();

    @Override
    public abstract void view(int id);

    @Override
    public abstract void edit();

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}
