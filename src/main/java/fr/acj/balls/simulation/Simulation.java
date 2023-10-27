package fr.acj.balls.simulation;

import java.awt.Color;
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
        Ball ball1 = new Ball(new Vect2D(100, 0), new Vect2D(200, 250), 50, Color.red);
        Ball ball2 = new Ball(new Vect2D(-100, 0), new Vect2D(800, 250), 30, Color.blue);

        this.balls = new LinkedList<>();
        this.balls.add(ball1);
        this.balls.add(ball2);

        this.addBoxBorders();
    }

    public Simulation(int nbBalls) {
        this.balls = new LinkedList<>();
        for (int i = 0; i<nbBalls; i++) {
            this.balls.add(new Ball());
        }
        this.addBoxBorders();
    }

    private void addBoxBorders() {
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
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void update(double deltaT) {
        moveBalls(deltaT);
        checkBordersBall();
        checkCollisionsBetweenBalls();
    }

    public void draw(Graphics2D g2) {
        for (Ball ball: this.balls) {
            ball.draw(g2);
        }
    }

    private void moveBalls(double deltaT) {
        for (Ball ball: this.balls) {
            ball.move(deltaT);
        }
    }

    private void checkBordersBall() {
        for (Ball ball: this.balls) {
            ball.checkBorders(borders);
        }
    }

    private void checkCollisionsBetweenBalls() {
        Double d;
        for (int i = 0; i < this.balls.size() - 1; i++) {
            for (int j = i+1; j < this.balls.size(); j++) {
                d = balls.get(i).getPos().dist(balls.get(j).getPos());
                if (d > balls.get(i).getR() + balls.get(j).getR()) {
                    continue;
                }
                balls.get(i).collisionWithAnotherBall(balls.get(j), d);
            }
        }
    }
}
