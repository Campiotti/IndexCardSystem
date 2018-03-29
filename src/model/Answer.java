package model;

public class Answer extends BaseEntity implements IEntity {
    private int id;
    private String answer;

    public Answer(String answer){
        this.answer=answer;
    }
    public Answer(){}

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }
}
