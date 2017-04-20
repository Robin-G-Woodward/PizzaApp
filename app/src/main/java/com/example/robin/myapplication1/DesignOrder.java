package com.example.robin.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 3/23/2017.
 */

public class DesignOrder extends Activity{

    public Context context;

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    private String size;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private String total;

    private static final int NUM_ROWS = 1;
    private static final int NUM_COLS = 2;

    public DesignOrder(String size,Context context) {
        this.context = context;
        this.size = size;
        //This is the constructor for the Design order class
        //This allows the the size of the pizza to be passed
        //When the class is instantiated
    }
    ////////////////////////////////////////////////////////////////////////////

    public ArrayList<String> getToppings() {
        return toppings;
    }

    private ArrayList<String> toppings = new ArrayList<String>(); //Creating a List to store toppings

    ////////////////////////////////////////////////////////////////////////////

    public void addToppings(String topping) { //A method to which a topping is passed to each time a topng is added
        toppings.add(topping);
        ///////////////////////////////////////////////////////////////////////////////////////
        final TableLayout table = (TableLayout)((Design)context).findViewById(R.id.tableForSideBar);//Define Layout to add topping to
        for(int row = 0; row < NUM_ROWS; row++){//For each row you want to add
            TableRow tableRow = new TableRow(context); //Define a row and pass it the context of the activity its added too.
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);//Alignment of each row to be centered so that children added are centered
            tableRow.setGravity(Gravity.CENTER_VERTICAL);
            table.addView(tableRow);//Adds a row to the TableLayout
            for(int col = 0; col < NUM_COLS; col++){//For each row add NUM_COL = 2
                if(col== 0){//First column for name of topping
                    TextView sideBarTopping = new TextView(context);//Define text view
                    sideBarTopping.setText(topping);//Set text of view to name of the topping which is passed to parameters
                    sideBarTopping.setTextSize(25f);//Define size
                    tableRow.addView(sideBarTopping);//Add text view to row
                }
                else if(col == 1) {//Second column for delete button
                    ImageButton button = new ImageButton(context);//Define image
                    button.setTag(topping);
                    button.setImageResource(R.drawable.close_button);//Set resources referenced from drawable folder
                    button.setBackgroundResource(0); // Removes button ImageButton Elevation

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { //On click listener for cancel button
                            String topping = (String)v.getTag();//Name of topping deleted
                            toppings.remove(topping);//Remove the topping from List to allow total to function correctly
                            total();//Revise the total

                            TableRow row = (TableRow)v.getParent(); //Type cast the parent of button passed to give row needing to be removed
                            table.removeView(row);// Remove row from the table

                            int resId = context.getResources().getIdentifier(topping, "id", "com.example.robin.myapplication1");//Create ID of ImageView by passing string equivalent
                            ImageView toppingBase = (ImageView)((Design)context).findViewById(resId);//Instantiate Image view of the Design Activity
                            toppingBase.setAlpha(0.0f);//Turn the ImageView transparent                by using findViewById
                        }
                    });

                    tableRow.addView(button);//Adds button to row.
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////
    }

    public String total(){//Method used to total order and amend EditText

        int numOfToppings = toppings.size();//Num of toppings in array list by using size method
        EditText editText = (EditText)((Design)context).findViewById(R.id.etTotal);
        //To define the edit text from outside the activity class by using findViewById method on the object of
        // context which is passed to the constructor of DesginOrder. The output has to be cast to ensure the value
        // returned is an EditText and the Design context, we know those both to be true so casting is appropriate

        if(size.equals("small")){// -If size meets condition, size passed to constructor of DesignOrder
            float baseCost = 5.00f; // Base and topping costs defined within scope of decision
            float cost = 0.6f;
            float total = ((cost*numOfToppings)+baseCost);//The calculation to work out total cost(Self explanitory
            BigDecimal bd = new BigDecimal(Float.toString(total));//Creating object of BigDecimal passing total as string
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);//Using BD setScale method to indicate num of decimal places
            editText.setText(bd.toString());//Set the text to the total.
            setTotal(bd.toString());

        }
        else if (size.equals("medium")){
            float baseCost = 7.00f;
            float cost = 0.7f;
            float total = ((cost*numOfToppings)+baseCost);
            BigDecimal bd = new BigDecimal(Float.toString(total));
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            editText.setText(bd.toString());
            setTotal(bd.toString());

        }
        else if (size.equals("large")){
            float baseCost = 9.00f;
            float cost = 0.8f;
            float total = ((cost*numOfToppings)+baseCost);
            BigDecimal bd = new BigDecimal(Float.toString(total));
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            editText.setText(bd.toString());
            setTotal(bd.toString());

        }
        return getTotal();
    }
}












