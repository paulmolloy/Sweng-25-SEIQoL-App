package com.example.sweng.seiqol_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by pablo on 04/03/17.
 */

public class DraggablePieChart extends View{


    public static final int LEFT_PADDING = 150;
    public static final int Y_AXIS_PADDING = 50;
    public static final int Y_TEXT_PADDING = 20;
    private ArrayList<Bar> bars = new ArrayList<Bar>();
    private int  barSelected= 2;//-1;
    private int barWidth = 70;
    private boolean isFirstDraw = true;
    private double scaleFactor;
    private int centerX;
    private int centerY;
    private int yCenteringPadding;

    Paint paint;
    Canvas canvas;

    private Path mArc;

    private Paint mPaintText;

    public DraggablePieChart(Context context) {
        super(context);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
        scaleFactor = 1;
    }

    public DraggablePieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addBars();
        scaleFactor = 1;
    }

    public DraggablePieChart(Context context, AttributeSet attrs) {
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
        bars.add(new Bar(50, 1, "#dcbd0d"));//yellow
        bars.add(new Bar(200,.4, "#909baf"));//grey
        bars.add(new Bar(350,1, "#de571d"));//red/orange
        bars.add(new Bar(500,.3, "#7f628e"));//purple
        bars.add(new Bar(650, .6, "#308754"));//green

//        bars.add(new Bar(50,0, "#dcbd0d"));//yellow
//        bars.add(new Bar(200,0, "#909baf"));//grey
//        bars.add(new Bar(350,1, "#de571d"));//red/orange
//        bars.add(new Bar(500,0, "#7f628e"));//purple
//        bars.add(new Bar(650, 0, "#308754"));//green
    }

    // the method that draws the individual bars
    // and shows where the marker will be.
    @Override
    protected void onDraw(Canvas canvas) {
        if(isFirstDraw){//scale bars to the size of the view
            scaleFactor = ((double) this.getHeight() -Y_AXIS_PADDING-Y_AXIS_PADDING)/this.getHeight();
            for(Bar b : bars){
                b.setHeight( (int) (b.getPercent()*(this.getHeight()-Y_AXIS_PADDING-Y_AXIS_PADDING)));
                yCenteringPadding = (this.getHeight()/2)-(this.getWidth()/2)-Y_AXIS_PADDING;
                centerY =yCenteringPadding+Y_AXIS_PADDING+(this.getWidth()-Y_AXIS_PADDING -Y_AXIS_PADDING)/2;
                centerX =yCenteringPadding+Y_AXIS_PADDING+(this.getWidth()-Y_AXIS_PADDING -Y_AXIS_PADDING)/2;

            }
            isFirstDraw=false;
        }

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);

        //draw stroke
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0,this.getHeight(),this.getWidth(), 0,paint); //draw outline of edges of view

        paint.setStyle(Paint.Style.FILL);

        // write testing vals to view
        paint.setColor(Color.BLUE);
        paint.setTextSize(25);
        paint.setStrokeWidth(0);
        canvas.drawText( bars.get(barSelected).getPercent() + "%  Bar Selected: " + barSelected
                + " Selected Bar Value: " + bars.get(barSelected).getHeight(), LEFT_PADDING,Y_AXIS_PADDING-paint.getTextSize(), paint);


        paint.setColor(Color.parseColor("#70DB4255"));
        RectF oval = new RectF();
        oval.set(Y_AXIS_PADDING+20, Y_AXIS_PADDING+20+yCenteringPadding, this.getWidth()-Y_AXIS_PADDING-20, this.getWidth()-Y_AXIS_PADDING-20+yCenteringPadding);

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);


        /*Draw degree markers here
         */
        RectF outerOval = new RectF();
        outerOval.set(Y_AXIS_PADDING, Y_AXIS_PADDING+yCenteringPadding, this.getWidth()-Y_AXIS_PADDING, this.getWidth()-Y_AXIS_PADDING+yCenteringPadding);
        Path mArc;
        mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(30);
        for(int i=0;i<360;i+=20){
            mArc = new Path();
            mArc.addArc(outerOval, i, i+5);
            canvas.drawTextOnPath(Integer.toString(i), mArc, 0, -20, mPaintText);
            canvas.drawArc(outerOval, i,(float) (1), true, mPaintText);
        }
        mPaintText.setColor(Color.WHITE);
        canvas.drawArc(oval, 0,(float) (360), true, mPaintText);


        for(Bar bar: bars){
            paint.setColor(bar.getC());


            canvas.drawArc(oval, 0,(float) (bar.getPercent()*360), true, paint);

        }




//
//
//        /* Draw axis here
//         */
//        paint.setColor(Color.GRAY);
//        canvas.drawText("Best", Y_TEXT_PADDING,Y_AXIS_PADDING+paint.getTextSize(), paint);
//        canvas.drawText("Possible", Y_TEXT_PADDING,Y_AXIS_PADDING+(2*paint.getTextSize()), paint);
//
//        canvas.drawText("Worst", Y_TEXT_PADDING,this.getHeight()-Y_AXIS_PADDING-paint.getTextSize(), paint);
//        canvas.drawText("Possible", Y_TEXT_PADDING,this.getHeight()-Y_AXIS_PADDING, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(4);
//
//        canvas.drawLine(LEFT_PADDING,this.getHeight()-Y_AXIS_PADDING, LEFT_PADDING,Y_AXIS_PADDING, paint); //Y axis line.
//        canvas.drawLine(LEFT_PADDING,this.getHeight()-Y_AXIS_PADDING, this.getWidth(),this.getHeight()-Y_AXIS_PADDING, paint); // X axis line.

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
                    double angle =Math.atan(((double)Y-centerY)/(X-centerX))*180/Math.PI;
                    System.out.println("The angle: " + angle);
                    System.out.println(X-centerX + "  " + (Y-centerY));
                    Bar cBar = bars.get(barSelected);

                    if( X-centerX>0 && (Y-centerY)>0){
                        cBar.setPercent(angle/360);

                    }
                    else if(X-centerX<0 && (Y-centerY)>0){
                        cBar.setPercent(.5+(angle/360));
                    }
                    else if(X-centerX<0 && (Y-centerY)<0){
                        cBar.setPercent(.5+(angle/360));
                    }
                    else if(X-centerX>0 && (Y-centerY)<0){
                        cBar.setPercent(1+(angle/360));
                    }
                    System.out.println("Percentage: " + cBar.getPercent());

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
