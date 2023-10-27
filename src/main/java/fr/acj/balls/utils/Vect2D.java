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

    public Vect2D(Vect2D v) {
        this(v.getX(), v.getY());
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public double dist(Vect2D v) {
        return Math.sqrt(Math.pow(this.x - v.getX(), 2.) + Math.pow(this.y - v.getY(), 2.));
    }

    public double dist(Line l) {
        return Math.abs(l.getA() * this.x + l.getB() * this.y + l.getC())
                / Math.sqrt(Math.pow(l.getA(), 2) + Math.pow(l.getB(), 2));
    }

    public Vect2D substr(Vect2D v) {
        return new Vect2D(this.x - v.getX(), this.y - v.getY());
    }

    public double scal(Vect2D v) {
        return v.getX() * this.x + v.getY() * this.y;
    }

    public void multiply(double c) {
        this.x *= c;
        this.y *= c;
    }

    public void normalise() {
        this.multiply(1/this.norm());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Vect2D vect2D = (Vect2D) obj;
        return vect2D.getX() == this.x && vect2D.getY() == this.y;
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