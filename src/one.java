import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class one {
    public ArrayList<Integer> manipulate(MyButton[] arrayLabels,int position) {
        // Number of rows left below it
        double left_bottom = Math.floor((63-position)/8.0);
        // to go down
        int down = position;
        //Stores index of elements to change colour
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        // Ensures before checking for another white another black has at least been spotted
        boolean status = false;
        // Checks bottom Section - and that not on bottom column
        for(int i=0;i<left_bottom;i++){
            // Looping corresponding index below it
            down = down+8;
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr1.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr1 = new ArrayList<Integer>();
                break;
            }

        }
        // Checks top Section - and that not on top column
        status = false;
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        down = position;
        int left_top = (int) (8-(left_bottom+1));
        for(int i=0;i<left_top;i++){
            down = down-8;
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr2.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr2 = new ArrayList<Integer>();
                break;
            }


        }
        // Checks left Section
        status = false;
        ArrayList<Integer> arr3 = new ArrayList<Integer>();
        down = position;
        int left_left = position-(left_top*8);
        for(int i=0;i<left_left;i++){
            down = down -1;
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr3.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr3 = new ArrayList<Integer>();
                break;
            }

        }
        // Checks right Section
        status = false;
        ArrayList<Integer> arr4 = new ArrayList<Integer>();
        down = position;
        int left_right = (int) (64-((left_bottom*8)+(left_top*8)+left_left))-1;
        for(int i=0;i<left_right;i++){
            down = down+1;
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr4.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr4 = new ArrayList<Integer>();
                break;
            }
        }
        // Checks top-right Section
        status = false;
        ArrayList<Integer> arr5 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_bottom;i++){
            down = down+7;
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr5.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr5 = new ArrayList<Integer>();
                break;
            }


        }
        // Checks bottom-right Section
        status = false;
        ArrayList<Integer> arr6 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_top;i++){
            down = down-9;
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr6.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr6 = new ArrayList<Integer>();
                break;
            }


        }
        // Checks bottom-left Section
        status = false;
        ArrayList<Integer> arr7 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_top;i++){
            down = down-7;
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr7.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr7 = new ArrayList<Integer>();
                break;
            }


        }
        // Checks top-left Section
        status = false;
        ArrayList<Integer> arr8 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_bottom;i++){
            down = down+9;
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.BLACK ) {
                arr8.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.WHITE && status) {
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.BLACK|| arrayLabels[down].col == null ) {
                arr8 = new ArrayList<Integer>();
                break;
            }


        }

        // Finding which section captures the most
        ArrayList<Integer> capture_most = new ArrayList<Integer>();
        capture_most.add(arr1.size());
        capture_most.add(arr2.size());
        capture_most.add(arr3.size());
        capture_most.add(arr4.size());
        capture_most.add(arr5.size());
        capture_most.add(arr6.size());
        capture_most.add(arr7.size());
        capture_most.add(arr8.size());
        switch(capture_most.indexOf(Collections.max(capture_most))) {
            case 0:
                return arr1;
            case 1:
                return arr2;
            case 2:
                return arr3;
            case 3:
                return arr4;
            case 4:
                return arr5;
            case 5:
                return arr6;
            case 6:
                return arr7;
            case 7:
                return arr8;
        }
        //should not be returning what is below
        return capture_most;
    }
    public static void main(String[] args){

    }
}



