package game;

import java.util.List;
import java.util.logging.Level;

class Turn {

    private static List<Player> players;
    private static Player playerOnTurn;

    protected static void firstTurn(List<Player> players) {
        Game.getLogger().log(Level.FINER, "First turn goes to {0}",
                players.get(0));
        Turn.players = players;
        Turn.playerOnTurn = players.get(0);
        newTurn();
    }

    protected static void newTurn() {
        Game.getLogger().log(Level.FINEST, "Turn starting for {0}",
                playerOnTurn);
        Game.getLogger().log(Level.FINEST, "Turn ending for {0}",
                playerOnTurn);
        /*
        Try to pass the turn to the next player, if there is no next player,
        go back to player 1
        */
        try {
            playerOnTurn = players.get(players.indexOf(playerOnTurn) + 1);
        }catch(IndexOutOfBoundsException e){
            playerOnTurn = players.get(0);
        }
    }

}
