package android.app.taller.proyect.com.tallerappsandroid;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by trabajo on 19/09/2017. 19
 */

public class ViewPagerPersonality extends ViewPager{

    private IPaint iPaint;

    public ViewPagerPersonality(Context context) {
        super(context);
    }

    public ViewPagerPersonality(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(iPaint==null){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(iPaint==null){
            return super.onTouchEvent(ev);
        }else{
            iPaint.onTouch(ev);
            return true;
        }
    }

    public IPaint getiPaint() {
        return iPaint;
    }

    public void setiPaint(IPaint iPaint) {
        this.iPaint = iPaint;
    }
}
