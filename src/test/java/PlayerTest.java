import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void step() {
        Player player = new Player(true);
        for (int i = 0; i < 12; i++) {
            player.step(i);
        }
        System.out.println(player.getDeck().size() == 0);
    }
}