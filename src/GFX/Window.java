package GFX;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import SortingAlgo.BinarySearch;

public class Window extends JFrame {

    private class Panel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            bs.onRender(g);

            g.dispose();
        }

        private BinarySearch bs = new BinarySearch();
    }

    public Window(String windowName) {
        this.m_WindowName = windowName;
    }

    public void render() {
        m_Panel.setDoubleBuffered(true);
        m_Panel.setPreferredSize(new Dimension(kWindowWidth, kWindowHeight));

        this.setTitle(m_WindowName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(new Point(75, 200));
        this.setUndecorated(false);
        this.add(this.m_Panel);
        this.pack();
        this.setVisible(true);

        while (true) {
            this.repaint();
            try {
                Thread.sleep(1 * 1000); // 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static final int kWindowWidth = 800, kWindowHeight = 600, kPixelsPerBlock = 50;
    private final String m_WindowName;
    private final Panel m_Panel = new Panel();
}