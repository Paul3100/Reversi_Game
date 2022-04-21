import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.print.DocFlavor;
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
    String myText = "";
    boolean validation;
    front(String title){
        panel.setLayout( new GridLayout(8,8) ); // Setting the number of boxes
        visual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Program should not run in background after exit
        visual.setTitle(title); // Name of Application
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
        // Checking if new move can be made needs to be repositioned - I think both need to be in both first and second
        one check = new one();
        two check2 = new two();
        for(int i = 0; i<64; i++){
            int finalI1 = i;
            first.arrayLabels[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (first.l1.getText() != " White player - click to put piece" ){
                                JOptionPane.showMessageDialog(first.visual,"Await your turn: Double click to close");
                            }
                            if (first.l1.getText() == " White player - click to put piece"&& !first.arrayLabels[finalI1].isClicked) {
                                // Checking to see if any more moves can be made:
                                for (int val = 0; val < 64; val++) {
                                    if(first.arrayLabels[val].col==Color.WHITE){
                                        first.validation = check.validation(first.arrayLabels, val);
                                        if (first.validation) {
                                            break;
                                        }
                                    }
                                }

                                // Checking to see if any moves can be made: FOR 2nd player
                                for (int val = 0; val < 64; val++) {
                                    if(second.arrayLabels[val].col==Color.BLACK){
                                        second.validation = check2.validation(second.arrayLabels, val);
                                        if (second.validation) {
                                            break;
                                        }
                                    }
                                }
                                // If they can, then check if pieces can be captured
                                if (first.validation) {
                                    ArrayList<Integer> arr = check.manipulate(first.arrayLabels, finalI1);
                                    System.out.println("First - "+"Size: "+arr.size()+" Array: "+arr+" Clicked: "+finalI1);
                                    // There are elements to capture
                                    if (arr.size() != 0) {
                                        arrayLabels[finalI1].isClicked = true;
                                        arrayLabels[finalI1].col = Color.WHITE;
                                        arrayLabels[finalI1].repaint();
                                        for (int xi = 0; xi < arr.size(); xi++) {
                                            arrayLabels[arr.get(xi)].isClicked = true;
                                            arrayLabels[arr.get(xi)].col = Color.WHITE;
                                            arrayLabels[arr.get(xi)].repaint();
                                        }
                                        // Move only valid if piece captured
                                        // Passing command to black player
                                        Thread thread = new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                first.l1.setText(" White player - not your turn");
                                                second.l1.setText(" Black player - click to put piece");
                                            }
                                        });
                                        thread.start();

                                    }
                                }
                                // Making second window 180 degrees
                                int count = 0;
                                for (int x2 = 63; x2 >= 0; x2--) {
                                    second.arrayLabels[count].isClicked = first.arrayLabels[x2].isClicked;
                                    second.arrayLabels[count].col = first.arrayLabels[x2].col;
                                    second.arrayLabels[count].repaint();
                                    count++;
                                }
                                // if no moves left for first but some left for right
                                if (!first.validation&&second.validation){
                                    first.l1.setText(" White player - not your turn");
                                    second.l1.setText(" Black player - click to put piece");
                                }
                                if (!first.validation&&!second.validation){
                                    System.out.println("End White");
                                }

                            }

                        }
                    });
                    t.start();
                }
            });

            second.arrayLabels[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Thread sec = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (second.l1.getText() != " Black player - click to put piece"){
                                JOptionPane.showMessageDialog(second.visual,"Await your turn: Double click to close");

                            }
                            if(second.l1.getText() == " Black player - click to put piece" && !second.arrayLabels[finalI1].isClicked ){
                                // Checking to see if any more moves can be made:
                                for (int val = 0; val < 64; val++) {
                                    if(first.arrayLabels[val].col==Color.WHITE){
                                        first.validation = check.validation(first.arrayLabels, val);
                                        if (first.validation) {
                                            break;
                                        }
                                    }
                                }

                                // Checking to see if any moves can be made: FOR 1st player
                                for (int val = 0; val < 64; val++) {
                                    if(second.arrayLabels[val].col==Color.BLACK){
                                        second.validation = check2.validation(second.arrayLabels, val);
                                        if (second.validation) {
                                            break;
                                        }
                                    }
                                }
                                if(second.validation){
                                    ArrayList<Integer> arr = check2.manipulate(second.arrayLabels, finalI1);
                                    System.out.println("Second - "+"Size: "+arr.size()+" Array: "+arr+" Clicked: "+finalI1);
                                    // There are elements to capture
                                    if(arr.size() != 0) {
                                        arrayLabels[finalI1].isClicked = true;
                                        arrayLabels[finalI1].col = Color.BLACK;
                                        arrayLabels[finalI1].repaint();
                                        for (int xi = 0; xi < arr.size(); xi++) {
                                            arrayLabels[arr.get(xi)].isClicked = true;
                                            arrayLabels[arr.get(xi)].col = Color.BLACK;
                                            arrayLabels[arr.get(xi)].repaint();
                                        }
                                        // Passing command to white player
                                        Thread thread2 = new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                first.l1.setText(" White player - click to put piece");
                                                second.l1.setText(" Black player - not your turn");

                                            }
                                        });
                                        thread2.start();
                                    }
                                }
                                int count = 0;
                                for(int x2 = 63; x2>=0;x2--){
                                    first.arrayLabels[x2].isClicked = second.arrayLabels[count].isClicked;
                                    first.arrayLabels[x2].col = second.arrayLabels[count].col;
                                    first.arrayLabels[x2].repaint();
                                    count++;
                                }
                                // if no moves left for second but some left for first
                                if (first.validation&&!second.validation){
                                    first.l1.setText(" White player - click to put piece");
                                    second.l1.setText(" Black player - not your turn");
                                }
                                if (!first.validation&&!second.validation){
                                    System.out.println("End Black");
                                }
                            }

                        }
                    });
                    sec.start();

                }
            });
        }
    }
    // displays information of who won.
    public void count(){

    }
    public static void main(String args[]){
        front first = new front("Reversi - white player");
        front second = new front("Reversi - black player");
        // Is it the players turn?
        int state = 0;
        // Creation of button
        first.button = new JButton("Greedy AI (play white)");
        second.button = new JButton("Greedy AI (play black)");
        // Label telling user whether it is their turn
        first.l1 = new JLabel(" White player - click to put piece");
        second.l1 = new JLabel(" Black player - not your turn");
        first.boxes();
        second.boxes();


        first.initalisation(first,second);
        second.initalisation(first,second);
        first.myText = "First";
        second.myText = "Second";
    }
}

class MyButton extends JButton
{
    public boolean isClicked = false;
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
            if (col == Color.BLACK){
                g.setColor(Color.WHITE);
                g.drawOval(2,2,getWidth()-5, getHeight()-5);
                g.setColor(Color.BLACK);
                g.fillOval(2,2,getWidth()-5, getHeight()-5);
            } else{
                g.setColor(Color.BLACK);
                g.drawOval(2,2,getWidth()-5, getHeight()-5);
                g.setColor(Color.WHITE);
                g.fillOval(2,2,getWidth()-5, getHeight()-5);

            }


            // this.isClicked = false;

        }
    }
}
// Not your turn comes up at wrong time
// Check if player can make move - Otherwise pass control to other player +
// AI +


/* Plan for checking if player can make a move:
With position = index in arrayLabels (for player)
Loop through each line for index, checking for white (or black).
If status = true and end does not contain a colour, return true
 */

/* Plan for AI:
With position = index in arrayLabels (for player) that is NULL
Calculate how many pieces can be captured using the if player can make move function
Add position, and number of pieces it can capture into an array
Fetch index of largest number -> Find corresponding index, then plot
 */


