import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class two {
    public ArrayList<Integer> manipulate(MyButton[] arrayLabels,int position) {
        int left_left_diag;
        int left_top_diag;
        int left_bottom_diag;
        int left_right_diag;
        // Number of rows left below it
        int left_bottom = (int)Math.floor((63-position)/8.0);
        // to go down
        int down = position;
        ArrayList<Integer> arr_final = new ArrayList<Integer>();
        //Stores index of elements to change colour
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        // Ensures before checking for another white another black has at least been spotted
        boolean status = false;
        // Checks bottom Section - and that not on bottom column
        for(int i=0;i<left_bottom;i++){
            // Looping corresponding index below it
            down = down+8;
            if (down>63||down<0){
                break;
            }
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr1.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr1);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
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
            if (down>63||down<0){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr2.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr2);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
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
            if (down>63||down<0){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr3.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr3);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
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
            if (down>63||down<0){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr4.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr4);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                arr4 = new ArrayList<Integer>();
                break;
            }
        }
        // Checks bottom-left Section
        status = false;
        ArrayList<Integer> arr5 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_top;i++){
            down = down-7;
            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);

            // Checks to make sure not going out of bounds and edges
            if (down>63||down<0||left_left_diag<left_left){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr5.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr5);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                arr5 = new ArrayList<Integer>();
                break;
            }


        }
        // Checks top-left Section
        status = false;
        ArrayList<Integer> arr6 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_bottom;i++){
            down = down+9;
            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);

            if (down>63||down<0||left_left_diag<left_left){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr6.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr6);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                arr6 = new ArrayList<Integer>();
                break;
            }


        }
        // Checks top-right Section
        status = false;
        ArrayList<Integer> arr7 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_bottom;i++){
            down = down+7;

            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);
            left_right_diag = (int) (64-((left_bottom_diag*8)+(left_top_diag*8)+left_left_diag))-1;

            if (down>63||down<0||left_right_diag<left_right){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr7.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr7);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                arr7 = new ArrayList<Integer>();
                break;
            }


        }
        // Checks bottom-right Section
        status = false;
        ArrayList<Integer> arr8 = new ArrayList<Integer>();
        down = position;
        for(int i=0;i<left_top;i++){
            down = down-9;

            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);
            left_right_diag = (int) (64-((left_bottom_diag*8)+(left_top_diag*8)+left_left_diag))-1;

            if (down>63||down<0||left_right_diag<left_right){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                arr8.add(down);
                status = true;
                continue;
            }
            if(arrayLabels[down].col == Color.BLACK && status) {
                arr_final.addAll(arr8);
                break;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                arr8 = new ArrayList<Integer>();
                break;
            }


        }

        return arr_final;
    }
    public boolean validation(MyButton[] arrayLabels,int position){
        int left_left_diag;
        int left_top_diag;
        int left_bottom_diag;
        int left_right_diag;
        // Number of rows left below it
        double left_bottom = Math.floor((63-position)/8.0);
        // to go down
        int down = position;
        // Ensures before checking for another black another white has at least been spotted
        boolean status = false;
        // Checks bottom Section - and that not on bottom column
        for(int i=0;i<left_bottom;i++){
            // Looping corresponding index below it
            down = down+8;
            if (down>63||down<0){
                break;
            }
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {
                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }

        }
        // Checks top Section - and that not on top column
        down = position;
        int left_top = (int) (8-(left_bottom+1));
        for(int i=0;i<left_top;i++){
            down = down-8;
            if (down>63||down<0){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {

                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }


        }
        // Checks left Section
        down = position;
        int left_left = position-(left_top*8);
        for(int i=0;i<left_left;i++){
            down = down -1;
            if (down>63||down<0){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {
                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }

        }
        // Checks right Section
        down = position;
        int left_right = (int) (64-((left_bottom*8)+(left_top*8)+left_left))-1;
        for(int i=0;i<left_right;i++){
            down = down+1;
            if (down>63||down<0){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {
                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }
        }
        // Checks bottom-left Section
        status = false;
        down = position;
        for(int i=0;i<left_top;i++){
            down = down-7;
            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);

            // Checks to make sure not going out of bounds and edges
            if (down>63||down<0||left_left_diag<left_left){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {
                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }


        }
        // Checks top-left Section
        status = false;
        down = position;
        for(int i=0;i<left_bottom;i++){
            down = down+9;
            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);

            if (down>63||down<0||left_left_diag<left_left){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {
                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }


        }
        // Checks top-right Section
        status = false;
        down = position;
        for(int i=0;i<left_bottom;i++){
            down = down+7;

            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);
            left_right_diag = (int) (64-((left_bottom_diag*8)+(left_top_diag*8)+left_left_diag))-1;

            if (down>63||down<0||left_right_diag<left_right){
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {
                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }


        }
        // Checks bottom-right Section
        status = false;
        down = position;
        for(int i=0;i<left_top;i++){
            down = down-9;

            left_bottom_diag = (int)Math.floor((63-down)/8.0);
            left_top_diag = (int) (8-(left_bottom_diag+1));
            left_left_diag = down-(left_top_diag*8);
            left_right_diag = (int) (64-((left_bottom_diag*8)+(left_top_diag*8)+left_left_diag))-1;

            if (down>63||down<0||left_right_diag<left_right) {
                break;
            }
            // Looping corresponding index below it
            // If below it, there's an opposite colour
            if(arrayLabels[down].col == Color.WHITE ) {
                status = true;
                continue;
            }
            if(arrayLabels[down].col == null && status) {
                return true;
            }
            // If below piece is either not black or doesn't contain an element then break
            if(arrayLabels[down].col != Color.WHITE|| arrayLabels[down].col == null ) {
                break;
            }


        }

        return false;
    }

}


