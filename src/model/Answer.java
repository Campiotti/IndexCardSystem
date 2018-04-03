package model;

public class Answer extends BaseEntity implements IEntity {
    private int id;
    private int questionFk;
    private String answer;
    private boolean correct;

    public Answer(){}

    public Answer(String answer){
        this.answer=answer;
    }

    public Answer(String answer, boolean correct){
        this.answer=answer;
        this.correct=correct;
    }

    public Answer(String answer, int questionFk, boolean correct){
        this.answer=answer;
        this.questionFk= questionFk;
        this.correct=correct;
    }

    public Answer(int id,String answer, int questionFk, boolean correct){
        this.id=id;
        this.answer=answer;
        this.questionFk= questionFk;
        this.correct=correct;
    }


    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void view(int id) {
        this.id=id;
        //todo get data from answerBroker and put it into variables.

    }

    @Override
    public void edit() {

    }

    @Override
    public String getText(String key) {
        return null;
    }

    @Override
    public void setText(String key, Object value) {

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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
