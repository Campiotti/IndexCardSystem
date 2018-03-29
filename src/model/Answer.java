package model;

public class Answer extends BaseEntity implements IEntity {
    private int id;
    private int questionFk;
    private String answer;

    public Answer(){}

    public Answer(String answer){
        this.answer=answer;
    }

    public Answer(String answer, int questionFk){
        this.answer=answer;
        this.questionFk= questionFk;
    }

    public Answer(int id,String answer, int questionFk){
        this.id=id;
        this.answer=answer;
        this.questionFk= questionFk;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionFk() {
        return questionFk;
    }

    public void setQuestionFk(int questionFk) {
        this.questionFk = questionFk;
    }
}
