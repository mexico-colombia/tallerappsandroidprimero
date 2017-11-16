package android.app.taller.proyect.com.tallerappsandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trabajo on 19/09/2017. 19
 */

public class ViewPaint extends View implements IPaint{

    private Paint p;
    private float x,y;
    private List<PointEvent> pointList;
    private PaintObjet paintObjet = PaintObjet.LAPIZ;
    private LinearLayout linearLayout;
    private int color = Color.BLUE;

    public ViewPaint(Context context) {
        super(context);
        pointList = new ArrayList<>();
        p = new Paint();
    }

    public ViewPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pointList = new ArrayList<>();
        p = new Paint();
    }

    public ViewPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        pointList = new ArrayList<>();
        p = new Paint();
    }

    public ViewPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        pointList = new ArrayList<>();
        p = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        canvas.drawPaint(p);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        for(int i=0;i<pointList.size();i++){
            if(i<pointList.size()-1) pointList.get(i).drawLine(canvas,pointList.get(i+1));
            else if(i>0){
                pointList.get(i-1).drawLine(canvas,pointList.get(i));
            }
        }
    }

    @Override
    public void onTouch(MotionEvent v) {
        x=v.getX();
        y=v.getY();
        switch (v.getAction()){
            case MotionEvent.ACTION_DOWN:
                agregarPunto(x,y,true);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                agregarPunto(x,y,true);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                agregarPunto(x,y,false);
                invalidate();
                break;
        }
    }

    public void agregarPunto(float x, float y,boolean draw){
        pointList.add(new PointEvent(x,y-(linearLayout.getVisibility()==VISIBLE?linearLayout.getHeight():0),draw,color,paintObjet));
    }

    public void clear() {
        pointList.clear();
        invalidate();
    }

    class PointEvent{

        private float x;
        private float y;
        private boolean draw;
        private int colorPoint;
        private PaintObjet paintObjet;

        public PointEvent() {
        }

        public PointEvent(float x, float y, boolean draw, int color, PaintObjet paintObjet) {
            this.x = x;
            this.y = y;
            this.draw = draw;
            this.colorPoint = color;
            this.paintObjet = paintObjet;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public boolean isDraw() {
            return draw;
        }

        public void setDraw(boolean draw) {
            this.draw = draw;
        }

        public int getColor() {
            return colorPoint;
        }

        public void setColor(int color) {
            this.colorPoint = color;
        }

        public void drawLine(Canvas canvas, PointEvent post){
            if(isDraw()){
                switch (paintObjet){
                    case BORRADOR:
                        p.setColor(Color.WHITE);
                        break;
                    case LAPIZ:
                        p.setColor(colorPoint);
                        break;
                }
                canvas.drawLine(getX(),getY(),post.getX(),post.getY(),p);
            }
        }

    }

    public enum PaintObjet{
        LAPIZ,BORRADOR
    }

    public PaintObjet getPaintObjet() {
        return paintObjet;
    }

    public void setPaintObjet(PaintObjet paintObjet) {
        this.paintObjet = paintObjet;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
