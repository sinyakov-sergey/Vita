import java.util.LinkedList;

public class Player {

    private final LinkedList<Integer> deck;//выбрал Linked(а не Array) потому что будет много манипуляций

    private int points = 0;//штрафные очки игрока

    private boolean defend;

    public Player(boolean defend) {
        this.defend = defend;
        deck = new LinkedList<Integer>();
        for (int i = 0; i < 12; i++) {
            deck.add(i);
        }
    }


    public LinkedList<Integer> getDeck() {
        return deck;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points = this.points + points;
    }

    public boolean isDefend() {
        return defend;
    }

    public void setDefend(boolean defend) {
        this.defend = defend;
    }

    public boolean step(Integer card){
        return deck.remove(card);//ход игрока(возвращает false если пытается взять карту которой нет)
    }
}
