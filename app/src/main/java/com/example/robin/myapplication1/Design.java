package com.example.robin.myapplication1;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Color;


public class Design extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set XML layout
        ///////////////////////////////////////////////////////////////////////////
        setContentView(R.layout.activity_design);
        setContext(this);
        ///////////////////////////////////////////////////////////////////////////
        final Button buttonSmall = (Button)findViewById(R.id.buttonSmall);
        final Button buttonMedium = (Button)findViewById(R.id.buttonMeduim);
        final Button buttonLarge = (Button)findViewById(R.id.buttonLarge);

        buttonSmall.setTag("small");
        buttonMedium.setTag("medium");
        buttonLarge.setTag("large");

        View.OnClickListener onClickListenerSize = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String size = (String)v.getTag();
                if(size.equals("small")){
                    buttonSmall.setBackgroundColor(Color.RED);
                    DesignOrder order = getOrder();
                    String prevoiusSize = order.getSize();
                    if(prevoiusSize.equals("small")){
                        buttonSmall.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    else if(prevoiusSize.equals("medium")){
                        buttonMedium.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    else if(prevoiusSize.equals("large")){
                        buttonLarge.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    order.setSize(size);
                    order.total();
                }
                else if(size.equals("medium")){
                    buttonMedium.setBackgroundColor(Color.RED);
                    DesignOrder order = getOrder();
                    String prevoiusSize = order.getSize();
                    if(prevoiusSize.equals("small")){
                        buttonSmall.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    else if(prevoiusSize.equals("medium")){
                        buttonMedium.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    else if(prevoiusSize.equals("large")){
                        buttonLarge.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    order.setSize(size);
                    order.total();
                }
                else{
                    buttonLarge.setBackgroundColor(Color.RED);
                    DesignOrder order = getOrder();
                    String prevoiusSize = order.getSize();
                    if(prevoiusSize.equals("small")){
                        buttonSmall.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    else if(prevoiusSize.equals("medium")){
                        buttonMedium.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    else if(prevoiusSize.equals("large")){
                        buttonLarge.setBackgroundResource(android.R.drawable.btn_default);
                    }
                    order.setSize(size);
                    order.total();
                }
            }
        };

        buttonSmall.setOnClickListener(onClickListenerSize);
        buttonMedium.setOnClickListener(onClickListenerSize);
        buttonLarge.setOnClickListener(onClickListenerSize);

        /////////////////////////////////////////////////////////////////////////////
        ImageView IVham = (ImageView) findViewById(R.id.IVham);
        IVham.setTag("ham");
        ImageView IVonion = (ImageView) findViewById(R.id.IVonion);
        IVonion.setTag("onion");
        ImageView IVpepper = (ImageView) findViewById(R.id.IVpepper);
        IVpepper.setTag("pepper");
        ImageView IVpinapple = (ImageView) findViewById(R.id.IVpineapple);
        IVpinapple.setTag("pineapple");

        IVham.setOnLongClickListener(longClickListener);
        IVonion.setOnLongClickListener(longClickListener);
        IVpepper.setOnLongClickListener(longClickListener);
        IVpinapple.setOnLongClickListener(longClickListener);

        ImageView IVbase = (ImageView) findViewById(R.id.IVbase);
        IVbase.setOnDragListener(dragListener);
        /////////////////////////////////////////////////////////////////////////////////
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Choose a pizza size:");
        dialog.show();

        Button small = (Button) dialog.findViewById(R.id.BcustomDialogSmall);
        small.setTag("small");
        Button medium = (Button) dialog.findViewById(R.id.BcustomDialogMedium);
        medium.setTag("medium");
        Button large = (Button) dialog.findViewById(R.id.BcustomDialogLarge);
        large.setTag("large");

        View.OnClickListener onDialogClickedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String size = (String) v.getTag(); // Get string from ImageView.
                DesignOrder order = new DesignOrder(size,getContext());//Creates object of DesignOrder passing size & context
                order.total();
                setOrder(order); //Set order

                if(size.equals("small")){
                    buttonSmall.setBackgroundColor(Color.RED);
                }
                else if(size.equals("medium")){
                    buttonMedium.setBackgroundColor(Color.RED);
                }
                else if(size.equals("large")){
                    buttonLarge.setBackgroundColor(Color.RED);
                }

                dialog.cancel();
            }
        };

        small.setOnClickListener(onDialogClickedListener);
        medium.setOnClickListener(onDialogClickedListener);
        large.setOnClickListener(onDialogClickedListener);
    }

//Outside onCreate method

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            CharSequence name = (CharSequence) v.getTag();
            ClipData.Item item = new ClipData.Item(name);
            ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(dragData, myShadowBuilder, v, 0);
            }
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_STARTED:

                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        for (int i = 0; i < 10; i++) {
                            System.out.println("The data is excepted");
                            return true;
                        }
                        return  false;
                    }

                case DragEvent.ACTION_DRAG_ENTERED:

                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    String topping = (String) item.getText();

                    if(topping.equals("ham")){ //Condition for if the data passed is a certain topping
                        ImageView IVham_Pizza = (ImageView) findViewById(R.id.ham); //Define imahe from XML as object
                        IVham_Pizza.setAlpha(1.0f); //Make image Viewable
                        setTopping(topping,getOrder()); //Pass topping to DesginOrder class
                    }
                    else if (topping.equals("onion")){
                        ImageView IVonion_Pizza = (ImageView) findViewById(R.id.onion);
                        IVonion_Pizza.setAlpha(1.0f);
                        setTopping(topping,getOrder());
                    }
                    else if (topping.equals("pepper")){
                        ImageView IVpepper_Pizza = (ImageView) findViewById(R.id.pepper);
                        IVpepper_Pizza.setAlpha(1.0f);
                        setTopping(topping,getOrder());
                    }
                    else if (topping.equals("pineapple")){
                        ImageView IVpineapple_Pizza = (ImageView) findViewById(R.id.pineapple);
                        IVpineapple_Pizza.setAlpha(1.0f);
                        setTopping(topping,getOrder());
                    }

                    return true;
            }
            return false;
        }
    };

    public void setOrder(DesignOrder order) {
        this.order = order;
    }

    public DesignOrder getOrder() {
        return order;
    }

    DesignOrder order;

    public void setTopping(String topping,DesignOrder order){ //Function to allow the toppings passed to it
        order.addToppings(topping);//To be added to the List in order using DesignOrder's
        order.total();            //Works out the total of the order and displays it
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    Context context;

}




