package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntBinaryOperator;
import java.util.logging.Level;

class Board {

    private final int DIMENSIONS = 2;
    private Map<Integer, Tile> tiles;

    protected Board(int[] boardSize) {
        if (boardSize.length != DIMENSIONS) {
            throw new IllegalArgumentException("Illegal amount of dimensions "
                    + "for boardSize was passed, passed: " + boardSize.length
                    + ", required: " + DIMENSIONS);
        }

        Game.getLogger().log(Level.FINER, "Creating tiles, {0} by {1}",
                new Integer[]{boardSize[0], boardSize[1]});
        createTiles(boardSize[0], boardSize[1]);
    }

    /*
    Note that the keys for tiles in this map are 2 digit Integers, the first
    digit representing the x-coordinate the second representing the
    y-coordinate, both starting count at 1.
    e.g. key 46 gets the tile with x=4 y=6
     */
    private void createTiles(int xLength, int yLength) {
        tiles = new HashMap();
        for (int i = 1; i <= xLength; i++) {
            for (int j = 1; j <= yLength; j++) {
                tiles.put(
                        getKey.applyAsInt(i, j),
                        new Tile(i, j));
            }
        }
    }

    protected void placePiece(Piece piece, int x, int y) {
        Game.getLogger().log(Level.FINEST, "placing a {0} on tile with x={1} "
                + "y={2}", new Object[]{piece.getType().toString(), x, y});

        if (tiles.containsKey(getKey.applyAsInt(x, y))) {
            tiles
                    .get(getKey
                            .applyAsInt(x, y))
                    .setPiece(piece);
        } else {
            throw new IndexOutOfBoundsException("Tile x=" + x + " y=" + y
                    + " is out of bounds");
        }
    }

    protected void placePiece(Piece piece, Integer key) {
        Game.getLogger().log(Level.FINEST, "placing a {0} on tile with x={1} "
                + "y={2}", new Object[]{piece.getType().toString(),
                    key.toString().charAt(0), key.toString().charAt(1)});

        if (tiles.containsKey(key)) {
            tiles
                    .get(key)
                    .setPiece(piece);
        } else {
            throw new IndexOutOfBoundsException("Tile x="
                    + key.toString().charAt(0) + " y="
                    + key.toString().charAt(1) + " is out of bounds");
        }
    }

    public Optional<Tile> getTile(int key) {
        if (tiles.containsKey(key)) {
            return Optional.of(tiles.get(key));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Tile> getTile(int x, int y) {
        if (tiles.containsKey(getKey.applyAsInt(x, y))) {
            return Optional.of(tiles.get(getKey.applyAsInt(x, y)));
        } else {
            return Optional.empty();
        }
    }

    /*
    returns the key used in the tiles map from an x and y coordinate
     */
    private IntBinaryOperator getKey = (x, y) -> x * 10 + y;
}
