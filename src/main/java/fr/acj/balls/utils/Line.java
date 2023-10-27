package fr.acj.balls.utils;

import fr.acj.balls.exeptions.SamePonintException;

/**
 * Line defined by its equation : ax + by + c = 0
 */
public class Line {
    private double a;
    private double b;
    private double c;

    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Line(Vect2D m1, Vect2D m2) throws SamePonintException {
        if (m1.equals(m2)) {
            throw new SamePonintException();
        }
        if (m1.getX() == m2.getX()) {
            this.b = 0.;
            this.a = 1.;
        } else {
            this.b = 1.;
            this.a = this.b * (m1.getY() - m2.getY()) / (m2.getX() - m1.getX());
        }
        this.c = -this.a * m1.getX() - this.b * m1.getY();
    }

    public Vect2D getPointOfSymmetry(Vect2D p) {
        return new Vect2D(
                (p.getX() * (this.b * this.b - this.a * this.a) - 2 * a * (this.b * p.getY() + this.c))
                        / (this.a * this.a + this.b * this.b),
                (p.getY() * (this.a * this.a - this.b * this.b) - 2 * this.b * (this.a * p.getX() + this.c))
                        / (this.a * this.a + this.b * this.b));
    }

    public Vect2D getReflectionVector(Vect2D v) {
        return new Vect2D(
                ((this.b * this.b - this.a * this.a) * v.getX() - 2 * this.a * this.b * v.getY())
                        / (this.a * this.a + this.b * this.b),
                ((this.a * this.a - this.b * this.b) * v.getY() - 2 * this.a * this.b * v.getX())
                        / (this.a * this.a + this.b * this.b));
    }

    public Vect2D getNormalVector() {
        return new Vect2D(this.a, this.b);
    }

    public Vect2D getPointFromLine() {
        if (a == 0)
            return new Vect2D(0, -this.c / this.b);
        return new Vect2D(-this.c / this.a, 0);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return a + "x + " + b + "y + " + c + " = 0";
    }

}
