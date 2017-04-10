package com.example.sweng.seiqol_app; /**
 * Created by pablo on 03/02/17.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


//Beginnings of code for a barChart where the bars are dragable to increase or decrease them.

public class DraggableBarChart extends View {

    public static final int LEFT_PADDING = 150;
    public static final int Y_AXIS_PADDING = 20;
    public static final int Y_TEXT_PADDING = 20;
    private ArrayList<Bar> bars = new ArrayList<Bar>();
    private int  barSelected= 2;//-1;
    private int barWidth = 70;
    private boolean isFirstDraw = true;
    private double scaleFactor;

    Paint paint;
    Canvas canvas;

    public DraggableBarChart(Context context) {
        super(context);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
        scaleFactor = 1;
    }

    public DraggableBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addBars();
        scaleFactor = 1;
    }

    public DraggableBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
        scaleFactor = 1;

    }

    private void addBars(){
        /*
            Add the five bars for the SEiQoL the colors are picked from the photo of the bar chart
         */
        bars.add(new Bar(50,.5, "#dcbd0d"));//yellow
        bars.add(new Bar(200,.5, "#909baf"));//grey
        bars.add(new Bar(350,.5, "#de571d"));//red/orange
        bars.add(new Bar(500,.5, "#7f628e"));//purple
        bars.add(new Bar(650, .5, "#308754"));//green
    }

    // the method that draws the individual bars
    // and shows where the marker will be.
    @Override
    protected void onDraw(Canvas canvas) {
        if(isFirstDraw){//scale bars to the size of the view
            scaleFactor = ((double) this.getHeight() -Y_AXIS_PADDING-Y_AXIS_PADDING)/this.getHeight();
            for(Bar b : bars){
                b.setHeight( (int) (b.getPercent()*(this.getHeight()-Y_AXIS_PADDING-Y_AXIS_PADDING)));
            }
            isFirstDraw=false;
        }

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);

        //draw stroke
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        //paint.setStyle(Paint.Style.FILL);

        //canvas.drawRect(0,this.getHeight(),this.getWidth(), 0,paint); //draw outline of edges of view

        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(400,1000,400, 0,paint);
        paint.setStyle(Paint.Style.FILL);

        // write testing vals to view
        paint.setColor(Color.BLUE);
        paint.setTextSize(25);
        paint.setStrokeWidth(0);
//        canvas.drawText( bars.get(barSelected).getPercent() + "%  Bar Selected: " + barSelected
//                + " Selected Bar Value: " + bars.get(barSelected).getHeight(), LEFT_PADDING,Y_AXIS_PADDING-paint.getTextSize(), paint);

//        canvas.drawText( "c bar y: " + (this.getHeight()-bars.get(barSelected).getHeight()) + "c bar x: "+  bars.get(barSelected).getX() + LEFT_PADDING + " " + bars.get(barSelected).getPercent() + "%  Bar Selected: " + barSelected
//                + " Selected Bar Value: " + bars.get(barSelected).getHeight(), LEFT_PADDING,400+Y_AXIS_PADDING-paint.getTextSize(), paint);
//
//
//        canvas.drawText( "height: " + (this.getHeight()) + "c bar x: "+  this.getWidth() + " " , LEFT_PADDING,200+Y_AXIS_PADDING-paint.getTextSize(), paint);



        paint.setColor(Color.parseColor("#70DB4255"));

//draw bars
        for(Bar bar: bars){
            paint.setColor(bar.getC());
//            canvas.drawRect( LEFT_PADDING+ bar.getX(),
//                    this.getHeight()-Y_AXIS_PADDING,
//                    LEFT_PADDING+ bar.getX()+barWidth,
//                    this.getHeight()-bar.getHeight()-Y_AXIS_PADDING,paint);

            canvas.drawRect( LEFT_PADDING+ bar.getX(),
                    this.getHeight()-bar.getHeight()-Y_AXIS_PADDING,
                    LEFT_PADDING+ bar.getX()+barWidth,
                    this.getHeight()-Y_AXIS_PADDING,paint);

        }

        /* Draw axis here
         */
        paint.setColor(Color.GRAY);
        canvas.drawText("Best", Y_TEXT_PADDING,Y_AXIS_PADDING+paint.getTextSize(), paint);
        canvas.drawText("Possible", Y_TEXT_PADDING,Y_AXIS_PADDING+(2*paint.getTextSize()), paint);

        canvas.drawText("Worst", Y_TEXT_PADDING,this.getHeight()-Y_AXIS_PADDING-paint.getTextSize(), paint);
        canvas.drawText("Possible", Y_TEXT_PADDING,this.getHeight()-Y_AXIS_PADDING, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        //canvas.drawRect( LEFT_PADDING ,Y_AXIS_PADDING,LEFT_PADDING + 150, this.getHeight()-Y_AXIS_PADDING,paint);

        canvas.drawLine(LEFT_PADDING,this.getHeight()-Y_AXIS_PADDING, LEFT_PADDING,Y_AXIS_PADDING, paint); //Y axis line.
        canvas.drawLine(LEFT_PADDING,this.getHeight()-Y_AXIS_PADDING, this.getWidth(),this.getHeight()-Y_AXIS_PADDING, paint); // X axis line.

    }



    // events when touching the screen
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch (eventaction) {

            case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on

                /* If we decide to tap a bar to select it for resizing the touch down detection will
                    have to be implemented here
                 */

                break;

            case MotionEvent.ACTION_MOVE: // touch drag with the marker

                    if(barSelected!=-1){
                        Bar cBar = bars.get(barSelected);
                        if(Y>=Y_AXIS_PADDING&& Y<=this.getHeight()-Y_AXIS_PADDING){ //prevents bar from going higher than view
                            cBar.setHeight(this.getHeight()-Y-Y_AXIS_PADDING);
                        }
                        else if( Y>this.getHeight()-Y_AXIS_PADDING){//added to make it easier to set a bar to max or min.
                            cBar.setHeight(0);

                        }else if(Y<Y_AXIS_PADDING){
                            cBar.setHeight(this.getHeight()-Y_AXIS_PADDING-Y_AXIS_PADDING);
                        }
                        cBar.setPercent(((double)bars.get(barSelected).getHeight())/(this.getHeight()-Y_AXIS_PADDING-Y_AXIS_PADDING));

                    }


                break;

            case MotionEvent.ACTION_UP:
                // touch drop - just do things here after dropping

                break;
        }
        // redraw the canvas
        invalidate();
        return true;

    }

    public double getBarValue(int barId){
        double val = -1;
        if(barId>-1 && barId<bars.size()) val = bars.get(barId).getPercent();
        return val;
    }

    public double[] getAllBarValues(){
        double[] vals = new double[bars.size()];
        for(int i=0;i<bars.size();i++){
            vals[i]=bars.get(i).getPercent();
        }
        return vals;
    }

    public void setBarSelected(int pos){
        this.barSelected=pos;
    }


    public class Bar{
        private int height;
        private double percent;
        private int c;
        private int x;


        public Bar(int x,double percent){
            this.percent = percent;
            c = Color.parseColor("#AADB1255");
            this.x = x;
        }

        public Bar(int x, double percent,String color){
            this.percent = percent;
            c = Color.parseColor(color);
            this.x=x;
        }

        public int getC(){
            return c;
        }

        public double getPercent(){
            return percent;
        }
        public void setPercent(double percent){
             this.percent =percent;
        }

        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
    }



}
