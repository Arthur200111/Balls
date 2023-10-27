package fr.acj.balls;

import javax.swing.JFrame;

import fr.acj.balls.simulation.SimulationPanel;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Physics Simulator");

        SimulationPanel sp = new SimulationPanel();

        window.add(sp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        sp.startSimulatorThread();
    }
}
