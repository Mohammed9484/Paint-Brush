package paint;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;

public abstract class Shapes {

    protected Point p;
    protected Color color;
    

}

class Line extends Shapes {

    private Point p2;
    boolean isDotted;

    public Line(Point p, Point p2, Color color,boolean isDotted) {
        this.p = p;
        this.p2 = p2;
        this.color=color;
        this.isDotted=isDotted;
    }

    public boolean isDotted() {
        return isDotted;
    }

    

    public Color getColor() {
        return color;
    }

    public Point getP() {
        return p;
    }

    public Point getP2() {
        return p2;
    }
    
}
class Rect extends Shapes{
private int width , height;
    final boolean isFilled;
    final boolean isDotted;

    public Rect(Point p,int width, int height,Color color,boolean isFilled,boolean isDotted) {
        this.p=p;
        this.width = width;
        this.height = height;
        this.color=color;
        this.isFilled = isFilled;
        this.isDotted = isDotted;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public boolean isIsFilled() {
        return isFilled;
    }

    public boolean isIsDotted() {
        return isDotted;
    }

    public int getHeight() {
        return height;
    }

    public Point getP() {
        return p;
    }
    

}
class Oval extends Shapes{
    private int width , height;
    final boolean isFilled;
    final boolean isDotted;

    public Oval(Point p,int width, int height,Color color,boolean isFilled,boolean isDotted) {
        this.p=p;
        this.width = width;
        this.height = height;
        this.color=color;
        this.isFilled = isFilled;
        this.isDotted = isDotted;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public boolean isDotted() {
        return isDotted;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getP() {
        return p;
    }
    

}
class Eraser extends Shapes {
    private Path2D path;

    public Eraser(Path2D path) {
        this.path = path;
        this.color = Color.WHITE; 
    }

    public Path2D getPath() {
        return path;
    }
}

class Pencil extends Shapes {
    private Path2D path;
     public  boolean isDotted;

 

    public Path2D getPath() {
        return path;
    }

    public boolean isDotted() {
        return isDotted;
    }

    public Pencil(Path2D path, Color color,boolean isDotted) {
        this.path = path;
        this.color = color;
        this.isDotted=isDotted;
       
    }

   
}
