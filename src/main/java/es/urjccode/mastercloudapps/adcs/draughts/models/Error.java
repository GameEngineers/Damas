package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Error {
    BAD_FORMAT,
    EMPTY_ORIGIN, 
    OPPOSITE_PIECE, 
    NOT_DIAGONAL, 
    NOT_EMPTY_TARGET, 
    NOT_ADVANCED, 
    WITHOUT_EATING,
    COLLEAGUE_EATING,
    TOO_MUCH_ADVANCED,
    TOO_MUCH_EATINGS,
    TOO_MUCH_JUMPS;
}