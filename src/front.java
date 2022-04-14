import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class front{
    JFrame visual = new JFrame();
    JPanel panel = new JPanel();
    // Declaring them as class attributes
    // Adding Label
    JLabel l1 = new JLabel("");
    // Adding Buttons
    JButton button = new JButton();
    MyButton [] arrayLabels = new MyButton[64];
    front(){
        panel.setLayout( new GridLayout(8,8) ); // Setting the number of boxes
        visual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Program should not run in background after exit
        visual.setTitle("Reversi"); // Name of Application
        visual.setVisible(true);
    }
    public void boxes(){ // Creating GUI
        for(int i=0;i<64;i++){
            // Creating boxes, setting colour and size
            MyButton myButton = new MyButton();
            myButton.setBackground(Color.GREEN);
            myButton.setSize(100,100);

            arrayLabels[i]=myButton;
            panel.add(myButton);
        }
        l1.setBounds(50,50, 100,30);
        // Adding default circles
        arrayLabels[27].isClicked = true;
        arrayLabels[27].col = Color.WHITE;
        arrayLabels[27].repaint();
        arrayLabels[36].isClicked = true;
        arrayLabels[36].col = Color.WHITE;
        arrayLabels[36].repaint();
        arrayLabels[28].isClicked = true;
        arrayLabels[28].col = Color.BLACK;
        arrayLabels[28].repaint();
        arrayLabels[35].isClicked = true;
        arrayLabels[35].col = Color.BLACK;
        arrayLabels[35].repaint();

        button.setFont(new Font("Ariel", Font.BOLD, 60));
        visual.add(l1,BorderLayout.NORTH);
        visual.add(panel, BorderLayout.CENTER );
        visual.add(button, BorderLayout.SOUTH );
        visual.pack();
        visual.setSize(800,800);
        visual.setVisible(true);
    }
    public void initalisation(front first,front second){
        for(int i = 0; i<64; i++){
            int finalI = i;
            int finalI1 = i;
            first.arrayLabels[i].addActionListener(e-> {
                if (first.l1.getText() != " White player - click to put piece" ){
                    JOptionPane.showMessageDialog(first.visual,"Await your turn");
                    System.out.println(1);
                }
                else{
                    arrayLabels[finalI1].isClicked = true;
                    arrayLabels[finalI1].col = Color.BLACK;
                    System.out.println("Button Pressed First "+finalI);
                    arrayLabels[finalI1].repaint();
                }
            });
            second.arrayLabels[i].addActionListener(e-> {
                if (second.l1.getText() != " White player - click to put piece" ){
                    JOptionPane.showMessageDialog(second.visual,"Await your turn");
                    System.out.println(2);
                }
                else{
                    arrayLabels[finalI1].isClicked = true;
                    arrayLabels[finalI1].col = Color.BLACK;
                    System.out.println("Button Pressed First "+finalI);
                    arrayLabels[finalI1].repaint();
                }
            });
        }
    }
    public static void main(String args[]){
        front first = new front();
        // Is it the players turn?
        int state = 0;
        // Creation of button
        first.button = new JButton("Greedy AI (play white)");
        // Label telling user whether it is their turn
        first.l1 = new JLabel(" White player - click to put piece");
        first.boxes();


        front second = new front();
        second.button = new JButton("Greedy AI (play black)");
        second.l1 = new JLabel(" Black player - not your turn");
        second.boxes();

        first.initalisation(first,second);

    }
}

class MyButton extends JButton
{
    public boolean isClicked;
    public Color col;

    public Dimension getPreferredSize()
    {
        return (new Dimension(100, 40));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (isClicked)
        {
            super.paintComponent(g);
            g.setColor(col);
            g.fillOval(2, 2, getWidth()-5, getHeight()-5);
           // this.isClicked = false;

        }
    }
}
// Painting goes over all buttons
// Painting individual buttons