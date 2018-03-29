package model;

public class IndexCard extends BaseEntity implements IEntity {
    private int id;
    private String question;
    private boolean numberQuestion=false;

    public IndexCard(){}

    public IndexCard(String question){
        this.question=question;
    }

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

    @Override
    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
