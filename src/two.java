import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class two extends front {
    public static front second = new front();
    public void manipulate(MyButton [] arrayLabels){

        second.button = new JButton("Greedy AI (play black)");
        second.l1 = new JLabel(" Black player - not your turn");
        second.boxes(arrayLabels);

        for(int i = 0; i<64; i++){
            int finalI = i;
            int finalI1 = i;
            arrayLabels[i].addActionListener(e-> {
                /*
                    if (second.l1.getText() != " Black player - click to put piece" ){
                        JOptionPane.showMessageDialog(second.visual,"Await your turn");
                    }

                 */
                arrayLabels[finalI1].isClicked = true;
                arrayLabels[finalI1].col = Color.WHITE;
                System.out.println("Button Pressed Two "+finalI);
                arrayLabels[finalI1].repaint();

            });
        }
    }

}
