import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class one {
    public ArrayList<Integer> manipulate(MyButton[] arrayLabels,int position) {
        // Number of rows left below it
        double left_bottom = Math.floor((63-position)/8.0);
        // to go down
        int down = position;
        //Stores index of elements to change colour
        ArrayList<Integer> arr = new ArrayList<Integer>();
        // Ensures before checking for another white another black has at least been spotted
        boolean status = false;
        // Checks bottom Section - and that not on bottom column
        for(int i=0;i<left_bottom;i++){
            // Looping corresponding index below it
            down = down+8;
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                return arr;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                break;
            }

        }

        // Checks top Section - and that not on top column
        int left_top = (int) (8-(left_bottom+1));
        int down_up = position;
        for(int i=0;i<left_top;i++){
            System.out.println(i);
            // Looping corresponding index below it
            down_up = down_up-8;
            System.out.print(position+" "+down+"\n");
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                return arr;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                break;
            }

        }

        return arr;
    }
}



