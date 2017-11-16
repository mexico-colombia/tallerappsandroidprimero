package android.app.taller.proyect.com.tallerappsandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
/**
 * Created by trabajo on 17/09/2017. 17
 */
public class AdapterApp extends FragmentPagerAdapter {
    private PaintFragment paintFragment;
    public AdapterApp(FragmentManager fm) {
        super(fm);
        paintFragment = new PaintFragment();
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CalculatorFragment();
            case 1:
                return new CalculadoraGraficaFragment();//el adaptador
               // return null;
            case 2:
                return paintFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    public PaintFragment getPaintFragment() {
        return paintFragment;
    }
}

