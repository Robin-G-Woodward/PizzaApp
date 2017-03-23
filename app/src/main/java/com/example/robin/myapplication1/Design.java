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


public class Design extends Activity {

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    String size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set XML layout
        setContentView(R.layout.activity_design);
        Context context = this;

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
        dialog.setTitle("Choose A size of pizza");
        dialog.show();

        Button small = (Button) dialog.findViewById(R.id.BcustomDialogSmall);
        small.setTag("small");
        Button medium = (Button) dialog.findViewById(R.id.BcustomDialogMedium);
        medium.setTag("medium");
        Button large = (Button) dialog.findViewById(R.id.BcustomDialogLarge);
        large.setTag("large");

        View.OnClickListener onDialogClickedListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String size = (String)v.getTag();
                setSize(size);
                dialog.cancel();
            }
        };

        small.setOnClickListener(onDialogClickedListener);
        medium.setOnClickListener(onDialogClickedListener);
        large.setOnClickListener(onDialogClickedListener);
        ///////////////////////////////////////////////////////////////////////////////
    }


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

                    if(topping.equals("ham")){
                        ImageView IVham_Pizza = (ImageView) findViewById(R.id.IVham_pizza);
                        IVham_Pizza.setAlpha(1.0f);
                    }
                    else if (topping.equals("onion")){
                        ImageView IVonion_Pizza = (ImageView) findViewById(R.id.IVonion_pizza);
                        IVonion_Pizza.setAlpha(1.0f);
                    }
                    else if (topping.equals("pepper")){
                        ImageView IVpepper_Pizza = (ImageView) findViewById(R.id.IVpepper_pizza);
                        IVpepper_Pizza.setAlpha(1.0f);
                    }
                    else if (topping.equals("pineapple")){
                        ImageView IVpineapple_Pizza = (ImageView) findViewById(R.id.IVpinapple_pizza);
                        IVpineapple_Pizza.setAlpha(1.0f);
                    }

                    return true;
            }
            return false;
        }
    };

}




