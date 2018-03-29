package model;

public abstract class BaseEntity implements IEntity {

    private int id;

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void view(int id) {

    }

    @Override
    public void edit() {

    }

    public int getId() {
        return id;
    }
}
