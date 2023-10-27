package fr.acj.balls.simulation;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import fr.acj.balls.element.Ball;
import fr.acj.balls.utils.Line;
import fr.acj.balls.utils.Vect2D;

public class Simulation {
    private List<Ball> balls;
    private List<Line> borders;

    public Simulation() {
        this.balls = new LinkedList<>();
        this.balls.add(new Ball());

        Vect2D a = new Vect2D(0,0);
        Vect2D b = new Vect2D(SimulationPanel.WIDTH,0);
        Vect2D c = new Vect2D(SimulationPanel.WIDTH, SimulationPanel.HEIGHT);
        Vect2D d = new Vect2D(0, SimulationPanel.HEIGHT);

        this.borders = new LinkedList<>();
        try {
            this.borders.add(new Line(a, b));
            this.borders.add(new Line(b, c));
            this.borders.add(new Line(c, d));
            this.borders.add(new Line(d, a));
            this.borders.add(new Line(d, b));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void draw(Graphics2D g2) {
        for (Ball ball: this.balls) {
            ball.draw(g2);
        }
    }

    public void update(double deltaT) {
        for (Ball ball: this.balls) {
            ball.update(deltaT, borders);
        }
    }
}
