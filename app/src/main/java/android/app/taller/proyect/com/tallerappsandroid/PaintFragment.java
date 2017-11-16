package android.app.taller.proyect.com.tallerappsandroid;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by trabajo on 19/09/2017. 19
 */

public class PaintFragment extends Fragment{

    private ViewPaint viewPaint;
    private ImageButton btnClear;
    private ImageButton btnBorrador;
    private ImageButton btnLapiz;
    private ImageButton btnPalette;
    private ImageButton btnLock;
    private IViewPagerSwipe viewPagerSwipe;
    private LinearLayout layoutPalette;
    private Button color1,color2,color3,color4,color5,color6,color7,color8,color9,color10;
    private Button color11,color12,color13,color14,color15,color16,color17,color18,color19,color20;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_paint,container,false);
        viewPaint = (ViewPaint) v.findViewById(R.id.viewPaint);
        btnClear = (ImageButton) v.findViewById(R.id.btnClearPaint);
        btnBorrador = (ImageButton) v.findViewById(R.id.btnBorrador);
        btnLapiz = (ImageButton) v.findViewById(R.id.btnLapiz);
        btnPalette = (ImageButton) v.findViewById(R.id.btnPalete);
        btnLock = (ImageButton) v.findViewById(R.id.btnPintar);
        viewPagerSwipe = (IViewPagerSwipe) getActivity();
        layoutPalette = (LinearLayout) v.findViewById(R.id.colorsLayout);
        color1 = (Button) v.findViewById(R.id.color1);
        color2 = (Button) v.findViewById(R.id.color2);
        color3 = (Button) v.findViewById(R.id.color3);
        color4 = (Button) v.findViewById(R.id.color4);
        color5 = (Button) v.findViewById(R.id.color5);
        color6 = (Button) v.findViewById(R.id.color6);
        color7 = (Button) v.findViewById(R.id.color7);
        color8 = (Button) v.findViewById(R.id.color8);
        color9 = (Button) v.findViewById(R.id.color9);
        color10 = (Button) v.findViewById(R.id.color10);
        color11 = (Button) v.findViewById(R.id.color11);
        color12 = (Button) v.findViewById(R.id.color12);
        color13 = (Button) v.findViewById(R.id.color13);
        color14 = (Button) v.findViewById(R.id.color14);
        color15 = (Button) v.findViewById(R.id.color15);
        color16 = (Button) v.findViewById(R.id.color16);
        color17 = (Button) v.findViewById(R.id.color17);
        color18 = (Button) v.findViewById(R.id.color18);
        color19 = (Button) v.findViewById(R.id.color19);
        color20 = (Button) v.findViewById(R.id.color20);

        addButtonColor(color1,color2,color3,color4,color5,color6,color7,color8,color9,color10,
                color11,color12,color13,color14,color15,color16,color17,color18,color19,color20);

        viewPaint.setLinearLayout(layoutPalette);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPaint.clear();
            }
        });

        btnBorrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPaint.setPaintObjet(ViewPaint.PaintObjet.BORRADOR);
            }
        });

        btnLapiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPaint.setPaintObjet(ViewPaint.PaintObjet.LAPIZ);
            }
        });

        btnPalette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layoutPalette.getVisibility()==View.VISIBLE){
                    layoutPalette.setVisibility(View.GONE);
                }else{
                    layoutPalette.setVisibility(View.VISIBLE);
                }
            }
        });

        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerSwipe.setLockSwipe(!viewPagerSwipe.isLockSwipe());
                if(viewPagerSwipe.isLockSwipe()){
                    btnLock.setImageResource( R.drawable.ic_action_lock_closed);
                }else{
                    btnLock.setImageResource(R.drawable.ic_action_lock_open);
                }}
        });

        return v;
    }

    public ViewPaint getViewPaint() {
        return viewPaint;
    }

    private void addButtonColor(Button... button){
        for(final Button b : button){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorDrawable buttonBackground = (ColorDrawable) b.getBackground();
                    viewPaint.setColor(buttonBackground.getColor());
                }
            });
        }
    }

}
