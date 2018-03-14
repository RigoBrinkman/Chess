package game;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

abstract class Piece {

    private Tile tile;
    private Player owner;
    private Type type;
    private Board board;

    protected enum Type {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }

    protected Piece(Type type, Tile tile, Player owner, Board board) {
        Game.getLogger().log(Level.FINEST, "Creating {0} at x={1} y={2} for "
                + "player {3}", new Object[]{type, tile.getCoordinates()[0],
                    tile.getCoordinates()[1], owner});
        this.type = type;
        this.tile = tile;
        this.owner = owner;
        this.board = board;
    }

    protected static Type getType(String s) {
        return Type.valueOf(s.toUpperCase());
    }

    protected Type getType() {
        return type;
    }

    protected int[] getCoordinates() {
        return this.tile.getCoordinates();
    }

    protected Board getBoard() {
        return board;
    }

    protected void setTile(Tile tile) {
        this.tile = tile;
    }

    protected void moveTo(Tile destination) {
        destination.setPiece(this);
        this.tile.removePiece();
        this.tile = destination;
    }

    protected abstract List<Tile> getPossibleMoves();
}

class Pawn extends Piece {

    public Pawn(Tile location, Player owner, Board board) {
        super(Type.PAWN, location, owner, board);
    }

    @Override
    protected List<Tile> getPossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class Rook extends Piece {

    public Rook(Tile location, Player owner, Board board) {
        super(Type.ROOK, location, owner, board);
    }

    @Override
    protected List<Tile> getPossibleMoves() {
        List<Tile> possibleMoves = new ArrayList();
        //go left
        int x = getCoordinates()[0];
        int y = getCoordinates()[1];
        while (true) {
            x--;
            if (getBoard().getTile(x, y).isPresent() && getBoard().getTile(x, y)
                    .get().getPiece().isPresent()) {
                possibleMoves.add(getBoard().getTile(x, y).get());
            } else {
                break;
            }
        }
        return possibleMoves;
    }
}

class Knight extends Piece {

    public Knight(Tile location, Player owner, Board board) {
        super(Type.KNIGHT, location, owner, board);
    }

    @Override
    protected List<Tile> getPossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class Bishop extends Piece {

    public Bishop(Tile location, Player owner, Board board) {
        super(Type.BISHOP, location, owner, board);
    }

    @Override
    protected List<Tile> getPossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class Queen extends Piece {

    public Queen(Tile location, Player owner, Board board) {
        super(Type.QUEEN, location, owner, board);
    }

    @Override
    protected List<Tile> getPossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class King extends Piece {

    public King(Tile location, Player owner, Board board) {
        super(Type.KING, location, owner, board);
    }

    @Override
    protected List<Tile> getPossibleMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
