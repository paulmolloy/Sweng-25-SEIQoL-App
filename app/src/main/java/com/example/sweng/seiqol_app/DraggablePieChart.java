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

    public static final int CUR_BAR_TAB_WIDTH = 10;
    public static final int CUR_BAR_TAB_HEIGHT = 10;

    public static final int Y_AXIS_PADDING = 50;
    public static final int TEXT_PADDING = 20;
    public static final int NUM_DEGREES = 360;
    public static final int MARKER_INTERVAL = 10;
    public static final int MARKER_TEXT_PADDING = 5;
    public static final int MARKER_TEXT_SIZE = 30;
    private ArrayList<Bar> bars = new ArrayList<Bar>();
    private int  barSelected= 2;//-1;
    private boolean isFirstDraw = true;
    private int centerX;
    private int centerY;
    private int yCenteringPadding;
    private Paint paint;
    private Canvas canvas;
    private Path mArc;
    private Paint mPaintText;

    public DraggablePieChart(Context context) {
        super(context);
        paint = new Paint();
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();
    }

    public DraggablePieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addBars();
    }

    public DraggablePieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        addBars();

    }

    private void addBars(){
        /*
            Add the five bars for the SEiQoL the colors are picked from the photo of the bar chart
         */
        //first param doesnt matter for pie chart
        bars.add(new Bar(50, 1, "#dcbd0d"));//yellow
        bars.add(new Bar(200,.8, "#909baf"));//grey
        bars.add(new Bar(350,.6, "#de571d"));//red/orange
        bars.add(new Bar(500,.4, "#7f628e"));//purple
        bars.add(new Bar(650, .2, "#308754"));//green

    }

    // the method that draws the individual bars
    // and shows where the marker will be.
    @Override
    protected void onDraw(Canvas canvas) {
        if(isFirstDraw){
            //Get padding  and center of pie the first time its drawn
            yCenteringPadding = (this.getHeight()/2)-(this.getWidth()/2)-Y_AXIS_PADDING;
            centerY =yCenteringPadding+Y_AXIS_PADDING+(this.getWidth()-Y_AXIS_PADDING -Y_AXIS_PADDING)/2;
            centerX =yCenteringPadding+Y_AXIS_PADDING+(this.getWidth()-Y_AXIS_PADDING -Y_AXIS_PADDING)/2;

            isFirstDraw=false;
        }

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);

        //draw stroke
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.LTGRAY);
        //canvas.drawRect(0,this.getHeight(),this.getWidth(), 0,paint); //draw outline of edges of view

        paint.setStyle(Paint.Style.FILL);

        // write testing vals to view
        paint.setColor(Color.BLUE);
        paint.setTextSize(25);
        paint.setStrokeWidth(0);
        canvas.drawText( getBarValue(barSelected) + "%  Seg Selected: " + barSelected, Y_AXIS_PADDING,Y_AXIS_PADDING-paint.getTextSize(), paint);


        RectF oval = new RectF();
        oval.set(Y_AXIS_PADDING+TEXT_PADDING, Y_AXIS_PADDING+TEXT_PADDING+yCenteringPadding, this.getWidth()-Y_AXIS_PADDING-TEXT_PADDING, this.getWidth()-Y_AXIS_PADDING-TEXT_PADDING+yCenteringPadding);

        /*Draw degree markers here
         */
        RectF outerOval = new RectF();
        outerOval.set(Y_AXIS_PADDING, Y_AXIS_PADDING+yCenteringPadding, this.getWidth()-Y_AXIS_PADDING, this.getWidth()-Y_AXIS_PADDING+yCenteringPadding);
        Path mArc;
        mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(MARKER_TEXT_SIZE);

        for(int i=0;i<100;i+=MARKER_INTERVAL){
            mArc = new Path();
            mArc.addArc(outerOval, ((float)i/100)*NUM_DEGREES, i+MARKER_TEXT_PADDING);
            canvas.drawTextOnPath(Integer.toString(i), mArc, 0, -TEXT_PADDING, mPaintText);
            canvas.drawArc(outerOval, ((float)i/100)*NUM_DEGREES,(float) (1), true, mPaintText);
        }

        mPaintText.setColor(Color.WHITE);
        canvas.drawArc(oval, 0,(float) (NUM_DEGREES), true, mPaintText);

        oval.set(Y_AXIS_PADDING+TEXT_PADDING - CUR_BAR_TAB_HEIGHT, Y_AXIS_PADDING+TEXT_PADDING+yCenteringPadding - CUR_BAR_TAB_HEIGHT, this.getWidth()-Y_AXIS_PADDING-TEXT_PADDING + CUR_BAR_TAB_HEIGHT, this.getWidth()-Y_AXIS_PADDING-TEXT_PADDING+yCenteringPadding + CUR_BAR_TAB_HEIGHT);
        paint.setColor(bars.get(barSelected).getC());
        canvas.drawArc(oval, (float) (bars.get(barSelected).getPercent()*NUM_DEGREES)-CUR_BAR_TAB_WIDTH ,CUR_BAR_TAB_WIDTH, true, paint);


        oval.set(Y_AXIS_PADDING+TEXT_PADDING, Y_AXIS_PADDING+TEXT_PADDING+yCenteringPadding, this.getWidth()-Y_AXIS_PADDING-TEXT_PADDING, this.getWidth()-Y_AXIS_PADDING-TEXT_PADDING+yCenteringPadding);
        for(Bar bar: bars){
            paint.setColor(bar.getC());
            canvas.drawArc(oval, 0,(float) (bar.getPercent()*NUM_DEGREES), true, paint);
        }

        //canvas.drawA



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

                if(barSelected!=-1 && barSelected!=0 ){
                    double angle =Math.atan(((double)Y-centerY)/(X-centerX))*180/Math.PI;
                    System.out.println("The angle: " + angle);
                    System.out.println(X-centerX + "  " + (Y-centerY));
                    Bar cBar = bars.get(barSelected);

                    double barBeforeAngle = 0;
                    if(barSelected!=bars.size()-1) barBeforeAngle=bars.get(barSelected+1).getPercent();


                    if(true){
                    //if(angle-barBeforeAngle>((float)CUR_BAR_TAB_WIDTH)/NUM_DEGREES) {
                    //if(angle-CUR_BAR_TAB_WIDTH>((float)CUR_BAR_TAB_WIDTH)/NUM_DEGREES    && ) {
                        //Determining which quadrant the angle is in
                        if (angle-CUR_BAR_TAB_WIDTH>((float)CUR_BAR_TAB_WIDTH)/NUM_DEGREES  && X - centerX > 0 && (Y - centerY) > 0) {
                            cBar.setPercent(angle / 360);
                        } else if (X - centerX < 0 && (Y - centerY) > 0) {
                            cBar.setPercent(.5 + (angle / 360));
                        } else if (X - centerX < 0 && (Y - centerY) < 0) {
                            cBar.setPercent(.5 + (angle / 360));
                        } else if (X - centerX > 0 && (Y - centerY) < 0) {
                            cBar.setPercent(1 + (angle / 360));
                        }
                        System.out.println("Percentage: " + cBar.getPercent());
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

//    public double getBarValue(int barId){
//        double val = -1;
//        if(barId>-1 && barId<bars.size()) val = bars.get(barId).getPercent();
//        return val;
//    }

    public double getBarValue(int barId){
        double val = -1;
        if(barId>-1 && barId<bars.size()){
            if(barId==bars.size()-1){
                val = bars.get(barId).getPercent();
            }else{
                val = bars.get(barId).getPercent() - bars.get(barId+1).getPercent();
            }
        }
        return val;
    }

    public double[] getAllBarValues(){
        double[] vals = new double[bars.size()];
        for(int i=0;i<bars.size();i++){
            vals[i]=getBarValue(i);
        }
        return vals;
    }

    public void setBarSelected(int pos){
        this.barSelected=pos;
        invalidate();
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
