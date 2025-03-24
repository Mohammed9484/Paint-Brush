package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
    

    public ShapeType selectedShape = ShapeType.LINE;
    public Color selectedColor = Color.BLACK;
    public boolean isDotted = false;
    public boolean isFilled = false;

//declare buttons
    JButton clear = new JButton("Clear");
    JButton undo = new JButton("Undo");
    JButton line = new JButton("Line");
    JButton oval = new JButton("Oval");
    JButton rect = new JButton("Rect");
    JButton pencil = new JButton("Pencil");
    JButton eraser = new JButton("Eraser");
    private JCheckBox filled = new JCheckBox("Filled");
    private JCheckBox dotted = new JCheckBox("Dotted");

    JButton red = new JButton();
    JButton green = new JButton();
    JButton blue = new JButton();
    public DrawPanel drawingPanel;

    public TopPanel() {

//postion of button
        red.setPreferredSize(new Dimension(30, 40));
        red.setBackground(Color.red);
        green.setPreferredSize(new Dimension(30, 40));
        green.setBackground(Color.green);
        blue.setPreferredSize(new Dimension(30, 40));
        blue.setBackground(Color.BLUE);

//adding buttons
        add(clear);
        add(undo);
        add(line);
        add(oval);
        add(rect);
        add(pencil);
        add(eraser);
        add(filled);
        add(dotted);
        add(red);
        add(green);
        add(blue);
        //action Lisenter
        //red button
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = Color.RED;
            }
        });
        //blue button
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = Color.BLUE;
            }
        });
        //green button
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = Color.GREEN;
            }
        });
        //line button
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = ShapeType.LINE;
            }
        });
        //rect button
        rect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = ShapeType.RECTANGLE;
            }
        });
        //oval button
        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = ShapeType.OVAL;
            }
        });
        //undo button
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.undoLastShape();
            }
        });
        //clear button
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.clearAll();
            }
        });
        //eraser button
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = ShapeType.ERASER;
            }
        });
        //pencil button
        pencil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = ShapeType.PENCIL;
            }
        });
        //fill button
        filled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (filled.isSelected()) {
                    dotted.setSelected(false);
                }
            

            }
        });
        filled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                isFilled = filled.isSelected();
            }

        });
        //dotted button
        dotted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (dotted.isSelected()) {
                    filled.setSelected(false);
                }
              

            }
        });
        dotted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                isDotted = dotted.isSelected();
            }
        });

    }

   

    public void setDrawPanel(DrawPanel drawPanel) {
    this.drawingPanel = drawPanel;
}

 

}
