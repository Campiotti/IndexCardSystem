package model;

public class CardDeck extends BaseEntity implements IEntity {

    private int id;
    private String title;
    private int passPercent;
    private int cardsPerRun;

    public CardDeck(){}

    public CardDeck(String title, int passPercent, int cardsPerRun){
        this.title=title;
        this.passPercent=passPercent;
        this.cardsPerRun=cardsPerRun;
    }

    public CardDeck(int id,String title, int passPercent, int cardsPerRun){
        this.id=id;
        this.title=title;
        this.passPercent=passPercent;
        this.cardsPerRun=cardsPerRun;
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

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPassPercent() {
        return passPercent;
    }

    public void setPassPercent(int passPercent) {
        this.passPercent = passPercent;
    }

    public int getCardsPerRun() {
        return cardsPerRun;
    }

    public void setCardsPerRun(int cardsPerRun) {
        this.cardsPerRun = cardsPerRun;
    }
}
