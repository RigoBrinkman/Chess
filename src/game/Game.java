package game;

import chess.Chess;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Game {

    private static final Logger LOGGER = Logger.getLogger(Chess.class.getName());

    private final String FILENAME = "defaultGame.properties";
    FileInputStream input;
    Properties properties;
    private List<Player> players;
    private List<Piece> pieces;
    private Board board;
    private Turn turn;
    private boolean gameIsOver;

    private Game() {
        LOGGER.fine("Creating game");
        gameIsOver = false;

        try {
            LOGGER.log(Level.FINE, "Using property file {0}", FILENAME);
            input = new FileInputStream(FILENAME);
            properties = new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            LOGGER.severe(e.toString());
        } catch (IOException e) {
            LOGGER.severe(e.toString());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.toString());
                }
            }
        }

        LOGGER.finer("Properties loaded");

        LOGGER.log(Level.FINER, "Creating Players");
        this.players = createPlayers();
        LOGGER.finer("Creating board");
        this.board = createBoard();

        LOGGER.finer("Creating pieces");
        this.pieces = createPieces(this.board);

        Turn.firstTurn(players);
        while (!gameIsOver) {
            Turn.newTurn();
        }
    }

    public static Game startNewGame() {
        return new Game();
    }

    private List<Player> createPlayers() {
        return Arrays.stream(
                properties
                        .getProperty("players")
                        .split("#"))
                .map(Player::new)
                .collect(Collectors.toList()
                );
    }

    private List<Piece> createPieces(Board board) {
        String[] props = {"piecesType", "piecesPosition", "piecesOwner"};

        List<List<String>> list = Arrays.stream(props)
                .map(p -> Arrays.stream(
                properties
                        .getProperty(p)
                        .split("#"))
                .collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (int i = 0; i < list.get(0).size(); i++) {
            PieceFactory.getPiece(Piece.getType(list.get(0).get(i)),
                    board.getTile(Integer.parseInt(list.get(1).get(i))).get(),
                    players.get(Integer.parseInt(list.get(2).get(i))),
                    board);
        }

        return new ArrayList<Piece>();

    }

    private Board createBoard() {
        return new Board(
                Arrays.stream(
                        properties
                                .getProperty("boardSize")
                                .split("#"))
                        .mapToInt(Integer::parseInt)
                        .toArray()
        );
    }

    protected Player getPlayer(int index) {
        if (players.size() > index) {
            return players.get(index);
        } else {
            throw new IllegalArgumentException("Player no. " + index
                    + "does not exist");
        }
    }

    protected static Logger getLogger() {
        return LOGGER;
    }

}
