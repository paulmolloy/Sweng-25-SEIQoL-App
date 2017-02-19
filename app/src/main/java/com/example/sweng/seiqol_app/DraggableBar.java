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


/**
This class is no longer used DraggableBarChart replaces it I'll delete it later.
 */
//Beginnings of code for a barChart where the bars are dragable to increase or decrease them.

public class DraggableBar extends View {

    Point[] points = new Point[4];
    Paint paint;
    Canvas canvas;
    BarMarker m;
    public DraggableBar(Context context) {
        super(context);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
    }

    public DraggableBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DraggableBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
    }


    // the method that draws the rectangle and shows where the marker will be.
    @Override
    protected void onDraw(Canvas canvas) {
        if(points[3]==null) //point4 null when user did not touch and move on screen.

            return;
        int left, top, right, bottom;
        left = points[0].x;
        top = points[0].y;
        right = points[0].x;
        bottom = points[0].y;
        for (int i = 1; i < points.length; i++) {
            left = left > points[i].x ? points[i].x:left;
            top = top > points[i].y ? points[i].y:top;
            right = right < points[i].x ? points[i].x:right;
            bottom = bottom < points[i].y ? points[i].y:bottom;

        }
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5);

        //draw stroke
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#AADB1255"));
        paint.setStrokeWidth(2);
        canvas.drawRect(
                left + m.getWidthOfMarker() / 2,
                top + m.getWidthOfMarker() / 2,
                right + m.getWidthOfMarker() / 2,
                bottom + m.getWidthOfMarker() / 2, paint);
        //fill the rectangle
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#55DB1255"));
        paint.setStrokeWidth(0);
        canvas.drawRect(
                left + m.getWidthOfMarker() / 2,
                top + m.getWidthOfMarker() / 2,
                right + m.getWidthOfMarker() / 2,
                bottom + m.getWidthOfMarker() / 2, paint);

        //draw the corners
        BitmapDrawable bitmap = new BitmapDrawable();
        // draw 0 where the marker is
        paint.setColor(Color.BLUE);
        paint.setTextSize(18);
        paint.setStrokeWidth(0);
            canvas.drawText("" + (0), m.getX(), m.getY(), paint);

    }



    // events when touching the screen
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch (eventaction) {

            case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on
                // the marker
                if (points[0] == null) {
                    //initialize rectangle.
                    points[0] = new Point();
                    points[0].x = X;
                    points[0].y = Y;

                    points[1] = new Point();
                    points[1].x = X;
                    points[1].y = Y + 300;

                    points[2] = new Point();
                    points[2].x = X + 90;
                    points[2].y = Y + 300;

                    points[3] = new Point();
                    points[3].x = X +90;
                    points[3].y = Y;


                    // declare the marker with the BarMarker class
                    for (Point pt : points) {
                        m=new BarMarker(getContext(), R.drawable.iconinfo, pt);
                    }
                } else {
                    //resize rectangle

                        // check if inside the bounds of the marker (circle)
                        // get the center for the marker
                        int centerX = m.getX() + m.getWidthOfMarker();
                        int centerY = m.getY() + m.getHeightOfMarker();
                        paint.setColor(Color.CYAN);
                        // calculate the radius from the touch to the center of the
                        // marker
                        double radCircle = Math
                                .sqrt((double) (((centerX - X) * (centerX - X)) + (centerY - Y)
                                        * (centerY - Y)));

                        if (radCircle < m.getWidthOfMarker()) {


                            invalidate();
                            break;
                        }
                        invalidate();

                }
                break;

            case MotionEvent.ACTION_MOVE: // touch drag with the marker


                    // move the marker wherever the finger is on the y axis.
                    if(m.getY() > Y){
                        m.setY(Y);  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!figure this out !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                        paint.setColor(Color.CYAN);

                        m.setY(m.getY());
                           // m.setY(points[0].y-300);

                        invalidate();
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
