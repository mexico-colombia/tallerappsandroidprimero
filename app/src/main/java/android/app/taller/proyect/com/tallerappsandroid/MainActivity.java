package android.app.taller.proyect.com.tallerappsandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IViewPagerSwipe{


    private boolean lockSwipe = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.calculator:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.calculatorGraphics:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.paint:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

    private ViewPagerPersonality viewPager;
    private AdapterApp adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPagerPersonality) findViewById(R.id.viewpager);
        adapter = new AdapterApp(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        viewPager.setiPaint(null);
                        navigation.setSelectedItemId(R.id.calculator);
                        break;
                    case 1:
                        viewPager.setiPaint(null);
                        navigation.setSelectedItemId(R.id.calculatorGraphics);
                        break;
                    case 2:
                        viewPager.setiPaint(lockSwipe ? adapter.getPaintFragment().getViewPaint() : null);
                        navigation.setSelectedItemId(R.id.paint);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public ViewPagerPersonality getViewPager() {
        return viewPager;
    }

    @Override
    public boolean isLockSwipe() {
        return lockSwipe;
    }

    @Override
    public void setLockSwipe(boolean lockSwipe) {
        this.lockSwipe = lockSwipe;
        viewPager.setiPaint(lockSwipe ? adapter.getPaintFragment().getViewPaint() : null);
    }
}
