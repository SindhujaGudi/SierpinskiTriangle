import java.awt.Graphics;
 import java.awt.Point;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.JApplet;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JTextField;

 public class Sierpinskitriangle extends JApplet {
     private JTextField jtfOrder = new JTextField("0", 5);
     private Sierpinskitriangle.SierpinskitrianglePanel trianglePanel = new Sierpinskitriangle.SierpinskitrianglePanel();

     public Sierpinskitriangle() {
         JPanel panel = new JPanel();
         panel.add(new JLabel("Enter an order: "));
         panel.add(this.jtfOrder);
         this.jtfOrder.setHorizontalAlignment(4);
         this.add(this.trianglePanel);
         this.add(panel, "South");
         this.jtfOrder.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 Sierpinskitriangle.this.trianglePanel.setOrder(Integer.parseInt(Sierpinskitriangle.this.jtfOrder.getText()));
             }
         });
     }

     public static void main(String[] args) {
         JFrame frame = new JFrame("Sierpinskitriangle");
         Sierpinskitriangle applet = new Sierpinskitriangle();
         frame.add(applet);
         frame.setDefaultCloseOperation(3);
         frame.setSize(1024, 768);
         frame.setVisible(true);
     }

     static class SierpinskitrianglePanel extends JPanel {
         private int order = 0;

         SierpinskitrianglePanel() {
         }

         public void setOrder(int order) {
             this.order = order;
             this.repaint();
         }

         protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             Point a = new Point(this.getWidth() / 2, 10);
             Point b = new Point(10, this.getHeight() - 10);
             Point c = new Point(this.getWidth() - 10, this.getHeight() - 10);
             Triangles(g, this.order, a, b, c);
         }

         private static void Triangles(Graphics g, int order, Point a, Point b, Point c) {
             if(order == 0) {
                 g.drawLine(a.x, a.y, b.x, b.y);
                 g.drawLine(a.x, a.y, c.x, c.y);
                 g.drawLine(b.x, b.y, c.x, c.y);
             } else {
                 Point p1 = midpoint(a, b);
                 Point p2 = midpoint(b, c);
                 Point p3 = midpoint(c, a);
                 Triangles(g, order - 1, a, p1, p3);
                 Triangles(g, order - 1, p1, b, p2);
                 Triangles(g, order - 1, p3, p2, c);
             }

         }

         private static Point midpoint(Point a, Point b) {
             return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
         }
     }
 }
