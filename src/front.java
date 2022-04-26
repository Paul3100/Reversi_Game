import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.print.DocFlavor;
import javax.swing.*;

public class front extends Thread{
    JFrame visual = new JFrame();
    JPanel panel = new JPanel();

    // Declaring them as class attributes
    // Adding Label
    JLabel l1 = new JLabel("");
    // Adding Buttons
    JButton button = new JButton();
    MyButton [] arrayLabels = new MyButton[64];
    String myText = "";
    boolean validation1;
    boolean validation2;
    static front first = new front("Reversi - white player");
    static front second = new front("Reversi - black player");
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
    public void place1(front first, int finalI1, one check){
        if (first.l1.getText() != " White player - click to put piece"){
            JOptionPane.showMessageDialog(first.visual,"Await your turn");
        }
        if (first.l1.getText() == " White player - click to put piece"&& !first.arrayLabels[finalI1].isClicked) {
            // Checking to see if any more moves can be made:
            for (int val = 0; val < 64; val++) {
                if(first.arrayLabels[val].col==Color.WHITE){
                    validation1 = check.validation(first.arrayLabels, val);
                    if (validation1 == true) {
                        break;
                    }
                    else{
                    }
                }
            }
            // Checking to see if any moves can be made: FOR 2nd player
            two check2 = new two();
            for (int val = 0; val < 64; val++) {
                if(second.arrayLabels[val].col==Color.BLACK){
                    validation2 = check2.validation(second.arrayLabels, val);
                    if (validation2 == true) {
                        break;
                    }
                }
            }
            // if no moves left for first but some left for right, pass to black
            if (!validation1&&validation2){
                first.l1.setText(" White player - not your turn");
                second.l1.setText(" Black player - click to put piece");

            }
            // If they can, then check if pieces can be captured
            if (validation1) {
                ArrayList<Integer> arr = check.manipulate(first.arrayLabels, finalI1);
                // There are elements to capture
                if (arr.size() != 0) {
                    first.arrayLabels[finalI1].isClicked = true;
                    first.arrayLabels[finalI1].col = Color.WHITE;
                    first.arrayLabels[finalI1].repaint();

                    for (int xi = 0; xi < arr.size(); xi++) {
                        first.arrayLabels[arr.get(xi)].isClicked = true;
                        first.arrayLabels[arr.get(xi)].col = Color.WHITE;
                        first.arrayLabels[arr.get(xi)].repaint();
                    }
                    // Switching control to other player
                    Thread t2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            first.l1.setText(" White player - not your turn");
                            second.l1.setText(" Black player - click to put piece");

                        }
                    });
                    t2.start();

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
            // Checking to see if any more moves can be made:
            validation1 = false;
            validation2 = false;
            for (int val = 0; val < 64; val++) {
                if(first.arrayLabels[val].col==Color.WHITE){
                    validation1 = check.validation(first.arrayLabels, val);
                    if (validation1) {
                        break;
                    }
                }
            }
            for (int val = 0; val < 64; val++) {
                if(second.arrayLabels[val].col==Color.BLACK){
                    validation2 = check2.validation(second.arrayLabels, val);
                    if (validation2) {
                        break;
                    }
                }
            }
            if (!validation1&&!validation2){
                int white = 0;
                int black = 0;
                for (int i = 0;i<64;i++){
                    if (first.arrayLabels[i].col == Color.BLACK)
                        black = black+1;
                    if (first.arrayLabels[i].col == Color.WHITE)
                        white = white+1;
                }
                if(white>black){
                    JOptionPane.showMessageDialog(first.visual,"White wins: "+white+":"+black);
                }
                if(white<black){
                    JOptionPane.showMessageDialog(second.visual,"Black wins: "+black+":"+white);
                }
                if(white==black){
                    JOptionPane.showMessageDialog(second.visual,"Draw: "+black+":"+white);
                }
                System.exit(0);
            }

        }
    }
    public void place2(front second,int finalI1,two check2){
        if (second.l1.getText() != " Black player - click to put piece"){
            JOptionPane.showMessageDialog(second.visual,"Await your turn");

        }
        if(second.l1.getText() == " Black player - click to put piece" && !second.arrayLabels[finalI1].isClicked ){
            // Checking to see if any more moves can be made:
            one check = new one();
            for (int val = 0; val < 64; val++) {
                if(first.arrayLabels[val].col==Color.WHITE){
                    validation1 = check.validation(first.arrayLabels, val);
                    if (validation1) {
                        break;
                    }
                }
            }
            // Checking to see if any moves can be made: FOR second player
            for (int val = 0; val < 64; val++) {
                if(second.arrayLabels[val].col==Color.BLACK){
                    validation2 = check2.validation(second.arrayLabels, val);
                    if (validation2) {
                        break;
                    }
                }
            }
            if(validation2){
                ArrayList<Integer> arr = check2.manipulate(second.arrayLabels, finalI1);
                // There are elements to capture
                if(arr.size() != 0) {
                    second.arrayLabels[finalI1].isClicked = true;
                    second.arrayLabels[finalI1].col = Color.BLACK;
                    second.arrayLabels[finalI1].repaint();
                    for (int xi = 0; xi < arr.size(); xi++) {
                        second.arrayLabels[arr.get(xi)].isClicked = true;
                        second.arrayLabels[arr.get(xi)].col = Color.BLACK;
                        second.arrayLabels[arr.get(xi)].repaint();
                    }
                    // Switching control to other player
                    // Switching control to other player
                    Thread t2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            first.l1.setText(" White player - click to put piece");
                            second.l1.setText(" Black player - not your turn");

                        }
                    });
                    t2.start();
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
            if (validation1&&!validation2){
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        first.l1.setText(" White player - click to put piece");
                        second.l1.setText(" Black player - not your turn");

                    }
                });

            }
            // Checking to see if any moves can be made: FOR second player
            validation1 = false;
            validation2 = false;
            for (int val = 0; val < 64; val++) {
                if(second.arrayLabels[val].col==Color.BLACK){
                    validation2 = check2.validation(second.arrayLabels, val);
                    if (validation2) {
                        break;
                    }
                }
            }
            // Checking to see if any more moves can be made:
            for (int val = 0; val < 64; val++) {
                if(first.arrayLabels[val].col==Color.WHITE){
                    validation1 = check.validation(first.arrayLabels, val);
                    if (validation1) {
                        break;
                    }
                }
            }
            if (!validation1&&!validation2){
                int white = 0;
                int black = 0;
                for (int i = 0;i<64;i++){
                    if (second.arrayLabels[i].col == Color.BLACK)
                        black = black+1;
                    if (second.arrayLabels[i].col == Color.WHITE)
                        white = white+1;
                }
                if(white>black){
                    JOptionPane.showMessageDialog(first.visual,"White wins: "+white+":"+black);
                }
                if(white<black){
                    JOptionPane.showMessageDialog(second.visual,"Black wins: "+black+":"+white);
                }
                if(white==black){
                    JOptionPane.showMessageDialog(second.visual,"Draw: "+black+":"+white);
                }
                System.exit(0);
            }
        }
    }
    public void initalisation1(front first) {
        // Checking if new move can be made needs to be repositioned - I think both need to be in both first and second
        one check = new one();
        // Test run
        first.button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> highest = new ArrayList<Integer>();
                // Adding manually as setting size did not work
                for (int i = 0; i < 64; i++) {highest.add(0);}
                for (int i = 0; i < 64; i++) {
                    if(arrayLabels[i].col!=Color.BLACK&&arrayLabels[i].col!=Color.WHITE){
                        highest.set(i, (check.manipulate(first.arrayLabels, i).size()));
                    }
                }

                first.place1(first, highest.indexOf(Collections.max(highest)), check);

            }
        });
        for (int i = 0; i < 64; i++) {
            int finalI1 = i;
            first.arrayLabels[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            first.place1(first, finalI1, check);
                        }
                    });
                    t1.start();

                }
            });

        }
    }
    public void initalisation2(front second){
        // Checking if new move can be made needs to be repositioned - I think both need to be in both first and second
        two check = new two();
        second.button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> highest = new ArrayList<Integer>();
                // Adding manually as setting size did not work
                for (int i = 0; i < 64; i++) {highest.add(0);}
                for (int i = 0; i < 64; i++) {
                    if(arrayLabels[i].col!=Color.BLACK&&arrayLabels[i].col!=Color.WHITE){
                        highest.set(i, (check.manipulate(second.arrayLabels, i).size()));
                    }
                }
                second.place2(second, highest.indexOf(Collections.max(highest)), check);

            }
        });
        for(int i = 0; i<64; i++){
            int finalI1 = i;
            second.arrayLabels[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Thread t3 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            second.place2(second,finalI1,check);
                        }
                    });
                    t3.start();
                }
            });
        }
    }
    public static void main(String args[]){
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


        first.initalisation1(first);
        second.initalisation2(second);
    }
}

class MyButton extends JButton
{
    public boolean isClicked = false;
    public Color col=null;

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


