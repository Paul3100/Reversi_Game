import java.awt.*;
import javax.swing.*;

public class one extends front {
    public static front first = new front();
    public void manipulate(MyButton [] arrayLabels){
        // Is it the players turn?
        int state = 0;
        // Creation of button
        first.button = new JButton("Greedy AI (play white)");
        // Label telling user whether it is their turn
        first.l1 = new JLabel(" White player - click to put piece");
        first.boxes(arrayLabels);

        for(int i = 0; i<64; i++){
            int finalI = i;
            int finalI1 = i;
            arrayLabels[i].addActionListener(e-> {
                if (first.l1.getText() != " White player - click to put piece" ){
                    JOptionPane.showMessageDialog(first.visual,"Await your turn");
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


}
