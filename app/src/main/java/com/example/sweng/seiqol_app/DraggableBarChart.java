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

    ArrayList<Bar> bars = new ArrayList<Bar>();
    int  barSelected= 2;//-1;
    int barWidth = 70;
    public static final int LEFT_PADDING = 150;
    public static final int TOP_PADDING = 200;
    public static final int BOTTOM_PADDING = 500;
    public static final int Y_AXIS_PADDING = 100;
    public static final int Y_TEXT_PADDING = 20;
    private double scaleFactor;

    Paint paint;
    Canvas canvas;
    BarMarker m;
    public DraggableBarChart(Context context) {
        super(context);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
        scaleFactor = ((double) this.getHeight() -TOP_PADDING-Y_AXIS_PADDING)/this.getHeight();
    }

    public DraggableBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addBars();
        scaleFactor = ((double) this.getHeight() -TOP_PADDING-Y_AXIS_PADDING)/this.getHeight();
    }

    public DraggableBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
        scaleFactor = ((double) this.getHeight() -TOP_PADDING-Y_AXIS_PADDING)/this.getHeight();

    }

    private void addBars(){
        /*
            Add the five bars for the SEiQoL the colors are picked from the photo of the bar chart
         */
        //second param isnt used
        bars.add(new Bar(50,150,400, "#dcbd0d"));//yellow
        bars.add(new Bar(200,350,600, "#909baf"));//grey
        bars.add(new Bar(350,550,400, "#de571d"));//red/orange
        bars.add(new Bar(500,750,600, "#7f628e"));//purple
        bars.add(new Bar(650,950,1000, "#308754"));//gree
    }

    // the method that draws the individual bars
    // and shows where the marker will be.
    @Override
    protected void onDraw(Canvas canvas) {

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
        //canvas.drawText("Testing View Height: "+ this.getHeight() +  " View Width: " + this.getWidth() + " Bar Selected: " + barSelected + " Selected Bar Value: " + bars.get(barSelected).getHeight(), 100, 100, paint);
        canvas.drawText( ((double)bars.get(barSelected).getHeight())/(this.getHeight()-Y_AXIS_PADDING-Y_AXIS_PADDING) + "%  Bar Selected: " + barSelected + " Selected Bar Value: " + bars.get(barSelected).getHeight(), LEFT_PADDING,TOP_PADDING-Y_AXIS_PADDING-paint.getTextSize(), paint);


        paint.setColor(Color.parseColor("#70DB4255"));


        for(Bar bar: bars){
            paint.setColor(bar.getC());
            canvas.drawRect( LEFT_PADDING+ bar.getX(),this.getHeight()-Y_AXIS_PADDING,LEFT_PADDING+ bar.getX()+barWidth,
                    this.getHeight()-bar.getHeight()-Y_AXIS_PADDING,paint);
        }
        
        
        
        /* Draw axis here
         */
        paint.setColor(Color.GRAY);
        canvas.drawText("Best", Y_TEXT_PADDING,TOP_PADDING-Y_AXIS_PADDING+paint.getTextSize(), paint);
        canvas.drawText("Possible", Y_TEXT_PADDING,TOP_PADDING-Y_AXIS_PADDING+(2*paint.getTextSize()), paint);

        canvas.drawText("Worst", Y_TEXT_PADDING,this.getHeight()-Y_AXIS_PADDING-paint.getTextSize(), paint);
        canvas.drawText("Possible", Y_TEXT_PADDING,this.getHeight()-Y_AXIS_PADDING, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);

        canvas.drawLine(LEFT_PADDING,this.getHeight()-Y_AXIS_PADDING, LEFT_PADDING,TOP_PADDING-Y_AXIS_PADDING, paint); //Y axis line.
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

    public void setBarSelected(int pos){
        this.barSelected=pos;
    }


    public class Bar{
        private int height;
        private Point p;
        private int c;



        public Bar(int x,int y,int height){
            this.p = new Point(x,y);
            this.height = height;
            c = Color.parseColor("#AADB1255");
        }
        public Bar(int x,int y,int height, String color){
            this.p = new Point(x,y);
            this.height = height;
            c = Color.parseColor(color);
        }

        public int getC(){
            return c;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
        public int getX() {
            return p.x;
        }

        public void setX(int x) {
            this.p.x = x;
        }
        public int getY() {
            return p.y;
        }

        public void setY(int y) {
            this.p.y = y;
        }
    }



    public static class Rect {
        Bitmap bitmap;

    }


    public static class BarMarker {

        Bitmap bitmap;
        Context mContext;
        Point point;
        int id;
        static int count = 0;
        public BarMarker(Context context, int resourceId, Point point) {
            this.id = count++;
            bitmap = BitmapFactory.decodeResource(context.getResources(),
                    resourceId);
            mContext = context;
            this.point = point;
        }

        public int getWidthOfMarker() {
            return bitmap.getWidth();
        }

        public int getHeightOfMarker() {
            return bitmap.getHeight();
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public int getX() {
            return point.x;
        }

        public int getY() {
            return point.y;
        }

        public int getID() {
            return id;
        }

        public void setX(int x) {
            point.x = x;
        }

        public void setY(int y) {
            point.y = y;
        }
    }

}
