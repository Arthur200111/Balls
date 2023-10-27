package fr.acj.balls.simulation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel implements Runnable {
    public static int HEIGHT = 500;
    public static int WIDTH = 1000;

    private int fps = 60;
    private Thread simulatorThread;

    private Simulation simu;

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(SimulationPanel.WIDTH, SimulationPanel.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.simu = new Simulation(20);
    }

    public void startSimulatorThread() {
        this.simulatorThread = new Thread(this);
        this.simulatorThread.start();
    }

    private void update(double deltaT) {
        this.simu.update(deltaT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        this.simu.draw(g2);

        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1000 / this.fps;
        double deltaT = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;

        while (simulatorThread != null) {
            currentTime = System.currentTimeMillis();
            deltaT += currentTime - lastTime;
            lastTime = currentTime;

            if (deltaT > drawInterval) {
                this.update(deltaT/1000);
                this.repaint();
                deltaT = 0;
            }
        }
    }

}
