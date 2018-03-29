package model;

public class IndexCard extends BaseEntity implements IEntity {
    private int id;
    private String question;
    private boolean numberQuestion=false;

    public IndexCard(){

    }

    public IndexCard(String question, boolean numberQuestion){
        this.question=question;
        this.numberQuestion=numberQuestion;
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

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNumberQuestion() {
        return numberQuestion;
    }
}
