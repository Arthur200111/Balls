package fr.acj.balls.element;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;

import fr.acj.balls.simulation.SimulationPanel;
import fr.acj.balls.utils.Line;

import fr.acj.balls.utils.Vect2D;

public class Ball {
    private Vect2D v;
    private Vect2D pos;
    private int r;
    private Color color;

    public Ball() {
        Random rand = new Random();
        this.pos = new Vect2D(rand.nextDouble() * SimulationPanel.WIDTH, rand.nextDouble() * SimulationPanel.HEIGHT);
        this.v = new Vect2D(rand.nextDouble(), rand.nextDouble());
        this.v.normalise();
        this.v.multiply(rand.nextDouble() * 600);
        this.r = rand.nextInt(50);

        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        this.color = new Color(red, green, blue);
    }

    public Ball(Vect2D v, Vect2D pos, int r, Color color) {
        this.v = v;
        this.pos = pos;
        this.r = r;
        this.color = color;
    }

    public void borderCollision(Line border) {
        // normalize the normal vector of the border
        Vect2D n = border.getNormalVector();
        Vect2D m = border.getPointFromLine();
        // Verification that the normal vector points towards the previous point of the
        // ball
        if (n.scal(this.pos.substr(m)) < 0) {
            // Not the good direction, change n to its opposite
            n.multiply(-1);
        }
        n.normalise();

        // Get the parallel line of the border at a distance of the radius of the ball
        Vect2D k = new Vect2D(m.getX() + this.r * n.getX(), m.getY() + this.r * n.getY());
        Line symLine = new Line(n.getX(), n.getY(), -n.getX() * k.getX() - n.getY() * k.getY());

        // The good position is symmetrical of the precedent position computed
        this.pos = symLine.getPointOfSymmetry(this.pos);

        // Change of the velocity vector
        this.v = symLine.getReflectionVector(this.v);

    }

    public void checkBorders(List<Line> borders) {
        double d;
        for (Line border : borders) {
            // Check distance between border and ball
            d = this.pos.dist(border);

            // if distance ok no calculations, go to next border
            if (d > r) {
                continue;
            }

            this.borderCollision(border);
        }
    }

    public void move(double deltaT) {
        this.pos.setX(deltaT * v.getX() + pos.getX());
        this.pos.setY(deltaT * v.getY() + pos.getY());
    }

    public void collisionWithAnotherBall(Ball ball, double d) {
        double a = (this.r*this.r-ball.getR()*ball.getR()+d*d)/(2*d);
        Vect2D u = ball.getPos().substr(this.pos);
        u.normalise();
        u.multiply(a);

        Vect2D I = this.pos.addi(u);
        Vect2D J = new Vect2D(I.getX() + u.getY(), I.getY() - u.getX());
        
        try {
            Line border = new Line(I, J);
            this.borderCollision(border);
            ball.borderCollision(border);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(this.color);
        g2.fillOval((int) this.pos.getX() - this.r, (int) this.pos.getY() - this.r, 2 * this.r, 2 * this.r);
    }

    public Vect2D getPos() {
        return this.pos;
    }

    public int getR() {
        return this.r;
    }
}
