package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

    private ArrayList<Shapes> shapes = new ArrayList<>();
    private Point start, end;
    public TopPanel topPanel;
    private Path2D currentPath;
    private boolean isErasing = false;

    public DrawPanel() {

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
                if (topPanel != null && (topPanel.selectedShape == ShapeType.PENCIL || topPanel.selectedShape == ShapeType.ERASER)) {

                    currentPath = new Path2D.Double();
                    currentPath.moveTo(start.x, start.y);
                    isErasing = (topPanel.selectedShape == ShapeType.ERASER);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentPath != null) {
                    if (isErasing) {
                        shapes.add(new Eraser(currentPath));
                    } else {
                        shapes.add(new Pencil(currentPath, topPanel.selectedColor, topPanel.isDotted));
                    }
                    currentPath = null;
                } else if (start != null && end != null) {
                    addShape();

                }
                start = null;
                end = null;

                repaint();
            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                end = e.getPoint();
                if (currentPath != null) {
                    currentPath.lineTo(end.x, end.y);
                }

                repaint();
            }
        });

    }

    public void undoLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }

    }

    public void clearAll() {
        if (!shapes.isEmpty()) {
            shapes.clear();
            repaint();
        }
    }

    private void addShape() {
        if (start != null && end != null) {
            ShapeType currentShape = topPanel.selectedShape;
            Color currentColor = topPanel.selectedColor;
            switch (currentShape) {
                case LINE:
                    shapes.add(new Line(start, end, currentColor, topPanel.isDotted));
                    break;
                case RECTANGLE:
                    int x = Math.min(start.x, end.x);
                    int y = Math.min(start.y, end.y);
                    int width = Math.abs(end.x - start.x);
                    int height = Math.abs(end.y - start.y);
                    shapes.add(new Rect(new Point(x, y), width, height, currentColor, topPanel.isFilled, topPanel.isDotted));
                    break;
                case OVAL:
                    x = Math.min(start.x, end.x);
                    y = Math.min(start.y, end.y);
                    width = Math.abs(end.x - start.x);
                    height = Math.abs(end.y - start.y);
                    shapes.add(new Oval(new Point(x, y), width, height, currentColor, topPanel.isFilled, topPanel.isDotted));
                    break;
                case PENCIL:
                    if (currentPath != null) {
                        shapes.add(new Pencil((Path2D) currentPath.clone(), currentColor, topPanel.isDotted));
                        currentPath = null;
                    }
                    break;

            }

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        for (int i = 0; i < shapes.size(); i++) {
            Shapes shape = shapes.get(i);
            g.setColor(shape.color);
            if (shape instanceof Line) {
                Line line = (Line) shape;
                if (line.isDotted) {
                    Graphics2D g2d = (Graphics2D) g;
                    float[] dashPattern = {5, 5};
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dashPattern, 0));
                } else {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(2));
                }
                g.drawLine(line.getP().x, line.getP().y, line.getP2().x, line.getP2().y);

            } else if (shape instanceof Rect) {
                Rect rect = (Rect) shape;

                if (rect.isDotted) {
                    Graphics2D g2d = (Graphics2D) g;
                    float[] dashPattern = {5, 5};
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dashPattern, 0));
                } else if (rect.isFilled) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(2));
                    g.fillRect(rect.getP().x, rect.getP().y, rect.getWidth(), rect.getHeight());
                } else {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(2));
                }

                g.drawRect(rect.getP().x, rect.getP().y, rect.getWidth(), rect.getHeight());
            } else if (shape instanceof Oval) {
                Oval oval = (Oval) shape;

                if (oval.isDotted) {
                    Graphics2D g2d = (Graphics2D) g;
                    float[] dashPattern = {5, 5};
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dashPattern, 0));
                } else if (oval.isFilled) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(2));
                    g.fillOval(oval.getP().x, oval.getP().y, oval.getWidth(), oval.getHeight());
                } else {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(2));
                }

                g.drawOval(oval.getP().x, oval.getP().y, oval.getWidth(), oval.getHeight());
            } else if (shape instanceof Pencil) {
                Graphics2D g2d = (Graphics2D) g;
                Pencil pencil = (Pencil) shape;
                if (pencil.isDotted()) {
                    float[] dashPattern = {5, 5};
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dashPattern, 0));
                } else {
                    g2d.setStroke(new BasicStroke(2));
                }
                g2d.draw(pencil.getPath());
            } else if (shape instanceof Eraser) {
                Graphics2D g2d = (Graphics2D) g;
                Eraser eraser = (Eraser) shape;
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(10));
                g2d.draw(eraser.getPath());
            }
        }

        if (currentPath != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(isErasing ? Color.WHITE : topPanel.selectedColor);
            g2d.draw(currentPath);
        }

        if (start != null && end != null) {
            Color currentColor = topPanel.selectedColor;
            ShapeType currentShape = topPanel.selectedShape;

            g2.setStroke(new BasicStroke(2));
            switch (currentShape) {
                case LINE:
                    g.drawLine(start.x, start.y, end.x, end.y);
                    break;
                case RECTANGLE:
                    int x = Math.min(start.x, end.x);
                    int y = Math.min(start.y, end.y);
                    int width = Math.abs(end.x - start.x);
                    int height = Math.abs(end.y - start.y);
                    g.drawRect(x, y, width, height);
                    break;
                case OVAL:
                    x = Math.min(start.x, end.x);
                    y = Math.min(start.y, end.y);
                    width = Math.abs(end.x - start.x);
                    height = Math.abs(end.y - start.y);
                    g.drawOval(x, y, width, height);
                    break;
                case PENCIL:
                    if (currentPath != null) {
                        g2.setColor(topPanel.selectedColor);
                        g2.setStroke(new BasicStroke(2));
                        g2.draw(currentPath);
                    }
                    break;
            }
        }
    }

    public void setTopPanel(TopPanel topPanel) {
        this.topPanel = topPanel;
    }

}
