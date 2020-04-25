package es.urjccode.mastercloudapps.adcs.draughts.models;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static org.mockito.Mockito.when;

public class GameBuilder {

    private Color color;
    private List<String> strings;

    public GameBuilder() {
        this.color = null;
        this.strings = new ArrayList<String>();
    }

    public GameBuilder color(Color color){
        this.color = color;
        return this;
    }

    public GameBuilder rows(String... strings) {
        for (String string : strings) {
            assert Pattern.matches("[bBnN ]{8}", string);
            this.strings.add(string);
        }
        return this;
    }

    public Game build() {
        if (this.strings.size() == 0)
            return new Game();
        Board board = new Board();
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt()).thenReturn(0);
        Game game = new Game(board, randomNumberMock);
        assert this.strings.size() == Coordinate.getDimension();
        this.setColor(game, board);
        for (int i = 0; i < this.strings.size(); i++) {
            this.setRow(board, i, this.strings.get(i));
        }
        return game;
    }

    private void setColor(Game game, Board board) {
        if (this.color == Color.BLACK) {
            board.put(new Coordinate(7, 0), new Pawn(Color.WHITE));
            game.move(new Coordinate(7, 0), new Coordinate(6, 1));
            board.remove(new Coordinate(6, 1));
        }
    }

    private void setRow(Board board, int row, String string) {
        for (int j = 0; j < string.length(); j++) {
            Color color = this.getColor(string.charAt(j));
            if (color != null) {
                Piece piece = new Pawn(color);
                if (Character.isUpperCase(string.charAt(j)))
                    piece = new Draught(color);
                board.put(new Coordinate(row, j), piece);
            }
        }
    }

    private Color getColor(char character) {
        switch (character) {
        case 'b':
        case 'B':
            return Color.WHITE;
        case 'n':
        case 'N':
            return Color.BLACK;
        default:
            return null;
        }
    }

}
