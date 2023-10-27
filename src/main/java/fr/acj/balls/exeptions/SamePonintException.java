package fr.acj.balls.exeptions;

public class SamePonintException extends Exception{

    public SamePonintException() {
        super("Need two distinct points to define a line");
    }


}
