import java.awt.*;
import javax.swing.*;

public class one extends front {
    public static void manipulate(front first) {
        for(int i = 0; i<64; i++){
            int finalI = i;
            int finalI1 = i;
            first.arrayLabels[i].addActionListener(e-> {
                if (first.l1.getText() != " White player - click to put piece" ){
                    JOptionPane.showMessageDialog(first.visual,"Await your turn");
                }
                else{
                    first.arrayLabels[finalI1].isClicked = true;
                    first.arrayLabels[finalI1].col = Color.BLACK;
                    System.out.println("Button Pressed First "+finalI);
                    first.arrayLabels[finalI1].repaint();
                }
            });
        }
    }


}
