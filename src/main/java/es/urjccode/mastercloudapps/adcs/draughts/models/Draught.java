package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Draught extends Piece {

  Draught(Color color) {
    super(color);
  }

  @Override
  Error isCorrectDiagonalMovement(int amountBetweenDiagonalPieces, int pair, Coordinate... coordinates) {
    if (amountBetweenDiagonalPieces > 1)
      return Error.TOO_MUCH_EATINGS;
    return null;
  }

  @Override
  public String getCode(){
		return super.getCode().toUpperCase();
  }

}