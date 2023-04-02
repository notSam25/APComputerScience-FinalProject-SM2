package GFX;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GFX.Camera.CameraMovement;
import Game.Map;
import Util.KeyboardHandler;

public class Renderer extends JFrame {
    private static class Panel extends JPanel {

        /*
         * An override of the component method that draws the window for the game.
         *
         * @note see Oracle documentation for information regarding this method.
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            camera.render(g, curMap);

            g.dispose();
        }

        private Map curMap = new Map();
        private Camera camera = new Camera();
    }

    /*
     * Starts the window for drawing the game.
     */
    public Renderer() {
        this.m_Panel.setDoubleBuffered(false);
        this.m_Panel.setPreferredSize(new Dimension(600, 600));

        this.setTitle(m_WindowName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(new Point(75, 200));
        this.setUndecorated(false);
        this.add(this.m_Panel);
        this.pack();
        this.setVisible(true);
        this.addKeyListener(KeyboardHandler.getListener());
    }

    /*
     * The logic handler for the game.
     */
    public void handleGame() {

        while (true) {
            KeyboardHandler.update();
            this.repaint(); // Draw to the window

            if (KeyboardHandler.keys[KeyboardHandler.GetKey('w')].down) {
                Camera.moveCamera(CameraMovement.UP, 10);
            }
            if (KeyboardHandler.keys[KeyboardHandler.GetKey('a')].down) {
                Camera.moveCamera(CameraMovement.LEFT, 10);
            }
            if (KeyboardHandler.keys[KeyboardHandler.GetKey('s')].down) {
                Camera.moveCamera(CameraMovement.DOWN, 10);
            }
            if (KeyboardHandler.keys[KeyboardHandler.GetKey('d')].down) {
                Camera.moveCamera(CameraMovement.RIGHT, 10);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private final Panel m_Panel = new Panel();
    private static final String m_WindowName = "Final Project | github.com/notSam25/";
}