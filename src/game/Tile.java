package game;

import java.util.Optional;
import java.util.logging.Level;

class Tile {

    private final int[] Coordinates = new int[2];
    private Optional<Piece> piece;

    protected Tile(int x, int y) {
        Game.getLogger().log(Level.FINEST, "Creating tile x={0} y={1}",
                new Integer[]{x, y});
        this.Coordinates[0] = x;
        this.Coordinates[1] = y;
        this.piece = null;
    }

    protected Optional<Piece> getPiece() {
        return piece;
    }

    protected void setPiece(Piece piece) {
        this.piece = Optional.of(piece);
    }

    protected void removePiece() {
        this.piece = null;
    }

    protected int[] getCoordinates() {
        return Coordinates;
    }
}
