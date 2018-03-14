package chess;

import game.Game;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Chess {

    private static final Logger LOGGER = Logger.getLogger(Chess.class.getName());
    private static FileHandler handler;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOGGER.setLevel(Level.FINEST);
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                handler = new FileHandler("P:/Logs/chess/chess.%u.%g.log",
                        100 * (1024 ^ 2), 10, true);
            } else {
                handler = new FileHandler("/var/log/chess/chess.%u.%g.log",
                        100 * (1024 ^ 2), 10, true);
            }
            handler.setFormatter(new SimpleFormatter());
        } catch (IOException | SecurityException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            System.exit(1);
        }
        LOGGER.addHandler(handler);
        LOGGER.fine("Logging started");
        
        
        Game game = Game.startNewGame();
    }

}
