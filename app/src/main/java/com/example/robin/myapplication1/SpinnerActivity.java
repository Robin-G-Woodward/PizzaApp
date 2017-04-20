package com.example.robin.myapplication1;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class SpinnerActivity implements AdapterView.OnItemSelectedListener {
    ////////////////////////////////////////////
    protected Menu context;
    protected String id_of_spinner;

    public SpinnerActivity(Context context,String id){
        this.context = (Menu) context;
        this.id_of_spinner = id;
    }
    //////////////////////////////////////////////////////////////////////////////

    public int getPreviousNum() {
        return previousNum;
    }

    public void setPreviousNum(int previousNum) {
        this.previousNum = previousNum;
    }

    private int previousNum = 0;

    ////////////////////////////////////////////////////////////////////////////

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
        int num = (int) parent.getItemAtPosition(position);//Returns the value selected from drop box
        BufferedReader br = null;//Define the buffered reader outside the scope of where it is used
        try { //Need to wrap with try catch to handle FileNotFoundException and IOException
            InputStream in = context.getAssets().open("id_to_cost.txt"); //Creates input stream from txt in assets file
            br = new BufferedReader(new InputStreamReader(in));//Buffered reader created to handle input stream from
                                                                //id_to_cost.txt in assets
            String line;//Define a string variable

            while ((line = br.readLine()) != null) {//Read every line in .txt till you can no longer
                String[] parts = line.split("-");//Create an array of the ID and COST

                if (parts[((parts.length) - 2)].equals(id_of_spinner)) {
                    float price = (Float.valueOf(parts[((parts.length) - 1)]))/100;//Converts string cost from txt to int and sets
                                                                              //and sets as variable
                    EditText etTotal = (EditText) (context).findViewById(R.id.etTotal);//Create object of edit text from Menu Activtiy
                    if ( num > getPreviousNum()){
                        float costCurrent = (num-getPreviousNum())*price;
                        float previousTotal = Float.valueOf(etTotal.getText().toString());//Gets total in EditText
                        float total = costCurrent + previousTotal; //Adds total to additional cost of new of pizza/s
                        BigDecimal bd = new BigDecimal(Float.toString(total));//Creating object of BigDecimal passing total as string
                        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);//Using BD setScale method to indicate num of decimal places
                        etTotal.setText(bd.toString());//Changes total in EditText
                    }else if(num == getPreviousNum()){
                        //If they clicked on the dropbox again but don't want to change number, do nothing
                    } else {//If they have chosen a fewer number of pizza
                        float costCurrent = (getPreviousNum()-num)*price;
                        float previousTotal = Float.valueOf(etTotal.getText().toString());//Gets total in EditText
                        float total = previousTotal - costCurrent; //Makes new total
                        BigDecimal bd = new BigDecimal(Float.toString(total));//Creating object of BigDecimal passing total as string
                        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);//Using BD setScale method to indicate num of decimal places
                        etTotal.setText(bd.toString());//Changes total in EditText
                    }
                    setPreviousNum(num);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: id_to_cost.txt" ); //If the file not found print error

        } catch (IOException e) {
            System.out.println("Unable to read file: id_to_cost.txt ");
        } finally {
            try {
                br.close();// Close the buffered reader which consiquenty closes input stream
            } catch (IOException e) {
                System.out.println("Unable to close file: id_to_cost.txt");
            } catch (NullPointerException ex) {
                // File was probably never opened
            }}}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}

