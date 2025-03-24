package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Paint {

    public static void main(String[] args) {

        JFrame wall = new JFrame("Paint");
        wall.setLayout(new BorderLayout());


        DrawPanel drawingPanel = new DrawPanel();
        TopPanel topPanel = new TopPanel();
        drawingPanel.setTopPanel(topPanel);
        topPanel.setDrawPanel(drawingPanel);

        wall.add(topPanel, BorderLayout.PAGE_START);
        wall.add(drawingPanel, BorderLayout.CENTER);

        topPanel.setPreferredSize(new Dimension(0, 50));
        drawingPanel.setBackground(Color.white);

        wall.setSize(1000, 800);
        wall.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wall.setVisible(true);

    }

}
