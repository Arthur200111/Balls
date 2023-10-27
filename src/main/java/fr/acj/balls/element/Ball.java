package fr.acj.balls.element;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.acj.balls.utils.Vect2D;

public class Ball {
    private Vect2D v;
    private Vect2D pos;
    private int r;

    public Ball() {
        this.v = new Vect2D(200,20);
        this.pos = new Vect2D(500, 250);
        this.r = 10;
    }

    public void update(double deltaT) {
        this.pos.setX(deltaT * v.getX() + pos.getX());
        this.pos.setY(deltaT * v.getY() + pos.getY());
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillOval((int) this.pos.getX() - this.r, (int) this.pos.getY() - this.r, 2 * this.r, 2 * this.r);
    }
}
