package ohtuesimerkki;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Testeroos {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    @Test
    public void nameSearchWorks() {
        Player found = stats.search("Kurri");
        assertEquals("Kurri",found.getName());
        Player Found = stats.search("asdgf");
        assertEquals(null,Found);
    }
    @Test
    public void TeamSearchWerks() {

        List<Player> found = stats.team("EDM");

        assertEquals("Semenko",found.get(0).getName());
        assertTrue(found.size() == 3);
    }
    @Test
    public void TopScorersWorks(){
        List<Player> found = stats.topScorers(2);

        assertEquals("Gretzky",found.get(0).getName());
        assertEquals("Lemieux",found.get(1).getName());
    }


}    

