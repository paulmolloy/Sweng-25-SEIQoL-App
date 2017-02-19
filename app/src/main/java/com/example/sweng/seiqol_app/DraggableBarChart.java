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
    int barWidth = 80;
    public static final int RIGHT_PADDING = 50;

    Paint paint;
    Canvas canvas;
    BarMarker m;
    public DraggableBarChart(Context context) {
        super(context);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
    }

    public DraggableBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addBars();
    }

    public DraggableBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
    }

    private void addBars(){
        /*
            Add the five bars for the SEiQoL the colors are picked from the photo of the bar chart
         */
        bars.add(new Bar(0,100,400, "#dcbd0d"));//yellow
        bars.add(new Bar(200,300,600, "#909baf"));//grey
        bars.add(new Bar(400,500,400, "#de571d"));//red/orange
        bars.add(new Bar(600,700,600, "#7f628e"));//purple
        bars.add(new Bar(800,900,1000, "#308754"));//gree
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
        canvas.drawText("Testing View Height: "+ this.getHeight() +  " View Width: " + this.getWidth() + " Bar Selected: " + barSelected + " Selected Bar Value: " + bars.get(barSelected).getHeight(), 100, 100, paint);


        paint.setColor(Color.parseColor("#70DB4255"));


        for(Bar bar: bars){
            paint.setColor(bar.getC());
            canvas.drawRect( RIGHT_PADDING+ bar.getX(),this.getHeight(),RIGHT_PADDING+ bar.getX()+barWidth,
                    this.getHeight()-bar.getHeight(),paint);
        }

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
                        if(Y>=0){ //prevents bar from going higher than view
                            cBar.setHeight(this.getHeight()-Y);
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
