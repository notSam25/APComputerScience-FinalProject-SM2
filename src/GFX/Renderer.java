package GFX;

import java.awt.Graphics;
import java.awt.Point;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Game.GameHandler;
import Game.LoadScreen;
import Game.Map;
import Util.KeyboardHandler;
import Util.MouseHandler;

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
            
            this.setBackground(backgroundColor);
            curMap.drawMap(g);

            if (!LoadScreen.isInGame()) {
                LoadScreen.startMenu(g);
            } else {
                Renderer.getGameHandler().drawEntities(g);

                g.setColor(Color.BLACK);
                g.drawString("FPS: " + Renderer.framePerSecond, 10, Renderer.getWindowHeight() - 10);

            }

            g.dispose();
        }

        private static Color backgroundColor = new Color(99, 132, 60, 255);
        private Map curMap = new Map();
    }

    /*
     * Starts the window for drawing the game.
     */
    public Renderer() {
        m_Panel.setDoubleBuffered(false);
        m_Panel.setPreferredSize(new Dimension(windowWidth, windowHeight));

        this.setTitle(m_WindowName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(new Point(0, 0));
        this.setMinimumSize(new Dimension(500, 500));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.add(m_Panel);
        this.pack();
        this.setVisible(true);
        this.addKeyListener(KeyboardHandler.getListener());
        this.addMouseListener(MouseHandler.getListener());
    }

    /*
     * The logic handler for the game.
     */
    public void handleGame() {
        double interval = (double) 1000000000 / idealFPS, nextDrawTime = interval + System.nanoTime();
        double now = System.nanoTime();
        int FPS = 0;
        while (true) {
            // update values for the input handlers
            KeyboardHandler.update();
            MouseHandler.update();
            mousePosition = updateMousePosition();

            // update window vars
            windowWidth = this.getContentPane().getWidth();
            windowHeight = this.getContentPane().getHeight();
            windowHeightPadding = this.getHeight() - windowHeight;

            // update game entities
            gameHandler.handleGame();

            // draw to the screen
            this.repaint();

            try {
                double remainder = nextDrawTime - System.nanoTime();
                remainder = remainder / 1000000000;
                FPS++;

                if (remainder < 0)
                    remainder = 0;

                if (System.nanoTime() >= now + 1000000000) {
                    framePerSecond = FPS;
                    FPS = 0;
                    now = System.nanoTime();
                }

                Thread.sleep((long) remainder);
                nextDrawTime += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int[] updateMousePosition() {
        Point mousePos = MouseHandler.getMouseScreenPosition();

        if (mousePos == null)
            return new int[] { 0, 0 };

        SwingUtilities.convertPointFromScreen(mousePos, this);
        return new int[] { mousePos.x, mousePos.y };
    }

    public static int[] getMouseScreenPosition() {
        if (mousePosition != null)
            return new int[] { mousePosition[0], mousePosition[1] - windowHeightPadding };
        else
            return new int[] { -1, -1 };
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static GameHandler getGameHandler() {
        return gameHandler;
    }

    public static int getWindowHeightPadding() {
        return windowHeightPadding;
    }

    private static int[] mousePosition;
    private static int windowWidth = 800, windowHeight = 600, windowHeightPadding, idealFPS = 120, framePerSecond = 0;
    private static final Panel m_Panel = new Panel();
    private static final GameHandler gameHandler = new GameHandler();
    private static final String m_WindowName = "Final Project | github.com/notSam25/";
}
