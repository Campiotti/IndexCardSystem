package model;

import persistence.AnswerBroker;

public class Question extends BaseEntity implements IEntity,QandA {
    private int id;
    private int cardDeckFk;
    private String question;
    private boolean numberQuestion=false;

    public Question(){}

    public Question(String question){
        this.question=question;
    }

    public Question(String question, boolean numberQuestion){
        this.question=question;
        this.numberQuestion=numberQuestion;
    }

    public Question(int cardDeckFk, String question, boolean numberQuestion){
        this.cardDeckFk=cardDeckFk;
        this.question=question;
        this.numberQuestion=numberQuestion;
    }

    public Question(int id, int cardDeckFk, String question, boolean numberQuestion){
        this.id=id;
        this.cardDeckFk=cardDeckFk;
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
    public Object getValue(String key) {
        return null;
    }

    @Override
    public void setText(String key, Object value) {

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

    public int getCardDeckFk() {
        return cardDeckFk;
    }

    public void setCardDeckFk(int cardDeckFk) {
        this.cardDeckFk = cardDeckFk;
    }
}
