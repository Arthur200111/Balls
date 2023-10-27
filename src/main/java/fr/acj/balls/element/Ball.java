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
        this.pos = new Vect2D(rand.nextDouble()*SimulationPanel.WIDTH, rand.nextDouble()*SimulationPanel.HEIGHT);
        this.v = new Vect2D(rand.nextDouble(), rand.nextDouble());
        this.v.normalise();
        this.v.multiply(rand.nextDouble()*600);
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

    public Vect2D checkBorders(List<Line> borders, Vect2D newPos) {
        double d;
        Vect2D n, m, k;
        Line symLine;
        for (Line border : borders) {
            // Check distance between border and ball
            d = newPos.dist(border);

            // if distance ok no calculations, go to next border
            if (d > r) {
                continue;
            }

            // normalize the normal vector of the border
            n = border.getNormalVector();
            m = border.getPointFromLine();
            // Verification that the normal vector points towards the previous point of the
            // ball
            if (n.scal(this.pos.substr(m)) < 0) {
                // Not the good direction, change n to its opposite
                n.multiply(-1);
            }
            n.normalise();

            // Get the parallel line of the border at a distance of the radius of the ball
            k = new Vect2D(m.getX() + this.r * n.getX(), m.getY() + this.r * n.getY());
            symLine = new Line(n.getX(), n.getY(), -n.getX() * k.getX() - n.getY() * k.getY());

            // The good position is symmetrical of the precedent position computed
            newPos = symLine.getPointOfSymmetry(newPos);

            // Change of the velocity vector
            this.v = symLine.getReflectionVector(this.v);
        }
        return newPos;
    }

    public Vect2D move(double deltaT) {
        return new Vect2D(deltaT * v.getX() + pos.getX(), deltaT * v.getY() + pos.getY());
    }

    public void update(double deltaT, List<Line> borders) {
        Vect2D newPos = this.move(deltaT);
        newPos = this.checkBorders(borders, newPos);
        this.pos = newPos;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(this.color);
        g2.fillOval((int) this.pos.getX() - this.r, (int) this.pos.getY() - this.r, 2 * this.r, 2 * this.r);
    }
}
