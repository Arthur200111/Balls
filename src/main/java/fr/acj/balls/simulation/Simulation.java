package fr.acj.balls.simulation;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import fr.acj.balls.element.Ball;

public class Simulation {
    private List<Ball> balls;

    public Simulation() {
        balls = new LinkedList<>();
        balls.add(new Ball());
    }

    public void draw(Graphics2D g2) {
        for (Ball ball: this.balls) {
            ball.draw(g2);
        }
    }

    public void update(double deltaT) {
        for (Ball ball: this.balls) {
            ball.update(deltaT);
        }
    }
}
