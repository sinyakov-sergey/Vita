import java.util.Scanner;

public class Field {

    private Player player;

    private Player bot;

    public Field() {
        player = new Player(true);
        bot = new Player(false);
    }

    public void start(){
        while (player.getDeck().size() != 0){
            System.out.println("---------------------------------------------------------------------");
            if (!player.isDefend()) System.out.println("атакует игрок, бот защищается");
            else System.out.println("атакует бот, игрок защищается");
            fight();//игрок и бот выбирают карты и происходит подсчет очков
            changeRole();//смана ролей(атака, защита)
            System.out.println("---------------------------------------------------------------------");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            score();//вывод текущего счета
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        if (player.getPoints() > bot.getPoints()) System.out.println("ПОБЕДИЛ ИГРОК");
        else if (player.getPoints() < bot.getPoints()) System.out.println("ПОБЕДИЛ БОТ");
        else System.out.println("НИЧЬЯ");
    }


    private void fight(){
        System.out.println("карты игрока:" + player.getDeck());
        System.out.print("игрок выберете карту:");
        Scanner sc = new Scanner(System.in);
        int playerCard = sc.nextInt();
        while (!player.step(playerCard)){
            System.out.println("у вас в колоде нет такой карты");
            System.out.print("игрок выберете карту:");
            playerCard = sc.nextInt();
        }
        int botCard = botChooseCard();
        System.out.println("бот выбрал:" + botCard);
        bot.step(botCard);

        int points = 0;
        if (player.isDefend()) points = botCard - playerCard;
        else points = playerCard - botCard;

        if (points < 0)points = 0;

        System.out.println("защищающийся получил штрафных очков:" + points);
        if (player.isDefend()) player.addPoints(points);
        else bot.addPoints(points);
    }

    private int botChooseCard() {
        if (bot.getDeck().size() == 12){
            return bot.getDeck().get(0);
        }//бот выбирает 0 карту при первой его атаке
        else if (bot.isDefend()){
            return bot.getDeck().get((bot.getDeck().size() - 1) / 2);
        }//бот при защите выбирает центральный элемент из его колоды
        else return bot.getDeck().get(bot.getDeck().size() - 1);//бот при атаке выбирает самую большую карту
    }

    private void score() {
        System.out.println("ШТРАФНЫЕ ОЧКИ");
        System.out.println("игрок:" + player.getPoints());
        System.out.println("бот:" + bot.getPoints());
    }

    private void changeRole() {
        boolean def = player.isDefend();
        player.setDefend(!def);
        bot.setDefend(def);
    }
}
