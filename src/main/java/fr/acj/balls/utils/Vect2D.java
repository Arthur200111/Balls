package fr.acj.balls.utils;

public class Vect2D {
    private double x;
    private double y;

    public Vect2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vect2D() {
        this(.0, .0);
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public double dist(Vect2D v) {
        return Math.sqrt(Math.pow(this.x-v.getX(),2.) + Math.pow(this.y-v.getY(), 2.));
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.y + "]";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}