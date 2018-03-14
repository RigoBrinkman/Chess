package game;


class PieceFactory {

    static protected Piece getPiece(Piece.Type type, Tile tile, Player owner,
            Board board) {
        switch (type) {
            case PAWN:
                return new Pawn(tile, owner, board);
            case ROOK:
                return new Rook(tile, owner, board);
            case KNIGHT:
                return new Knight(tile, owner, board);
            case BISHOP:
                return new Bishop(tile, owner, board);
            case QUEEN:
                return new Queen(tile, owner, board);
            case KING:
                return new King(tile, owner, board);
            default:
                throw new IllegalArgumentException("Unknown Piece type: " + type);
        }
    }

}
