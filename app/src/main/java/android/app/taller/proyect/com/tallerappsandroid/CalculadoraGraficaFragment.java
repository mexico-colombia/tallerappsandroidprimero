package android.app.taller.proyect.com.tallerappsandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trabajo on 17/09/2017. 17
 */

public class CalculadoraGraficaFragment extends Fragment {
    //Declaro Variables
    private LineGraphSeries<DataPoint> series;
    private EditText txtxIn;
    private EditText txtxFn;
    private EditText txtyIn;
    private EditText txtyFn;
    private Button recta;
    private Button parabola;
    private Button zoomMas;
    private Button zoomMenos;
    private TextView zoomState;

    private static double Zoom = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_calculadora_grafica,container,false);

        final GraphView graph = (GraphView) v.findViewById(R.id.graph1);
        txtxIn = (EditText) v.findViewById(R.id.ptxIn);
        txtxFn = (EditText) v.findViewById(R.id.ptxFn);
        txtyIn = (EditText) v.findViewById(R.id.ptyIn);
        txtyFn = (EditText) v.findViewById(R.id.ptyFn);
        recta = (Button) v.findViewById(R.id.recta);
        parabola = (Button) v.findViewById(R.id.parabola);
        zoomMas = (Button) v.findViewById(R.id.zoomMas);
        zoomMenos = (Button) v.findViewById(R.id.zoomMenos);
        zoomState = (TextView) v.findViewById(R.id.stateZoom);
        series = new LineGraphSeries<DataPoint>();


        //Recta
        recta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graph.removeAllSeries();
                double xi,xf,yi,yf;
                double ancho,altura;

                try {
                    xi = Double.parseDouble(txtxIn.getText().toString());
                    xf = Double.parseDouble(txtxFn.getText().toString());
                    yi = Double.parseDouble(txtyIn.getText().toString());
                    yf = Double.parseDouble(txtyFn.getText().toString());
                    series.resetData(new DataPoint[] {});
                    ancho = Math.abs(xf-xi);
                    altura = Math.abs(yf-yi);
                    if(xi==xf){
                        if(yi==yf){
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-Zoom);
                            graph.getViewport().setMaxX(xi+Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-Zoom);
                            graph.getViewport().setMaxY(yi+Zoom);
                        }else if(yi>yf){
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-Zoom);
                            graph.getViewport().setMaxX(xi+Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-altura*Zoom);
                            graph.getViewport().setMaxY(yi+altura*Zoom);
                        }else{
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-Zoom);
                            graph.getViewport().setMaxX(xi+Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-altura*Zoom);
                            graph.getViewport().setMaxY(yf+altura*Zoom);
                        }
                    }
                    else if(xi>xf){//xinicial es mayor que xfinal
                        if(yi==yf){
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-ancho*Zoom);
                            graph.getViewport().setMaxX(xi+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-Zoom);
                            graph.getViewport().setMaxY(yi+Zoom);
                        }
                        else if(yi>yf){//yinicial es mayor que yfinal
                            //(3,2)(2,1)
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-ancho*Zoom);
                            graph.getViewport().setMaxX(xi+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-altura*Zoom);
                            graph.getViewport().setMaxY(yi+altura*Zoom);
                        }else{
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-ancho*Zoom);
                            graph.getViewport().setMaxX(xi+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-altura*Zoom);
                            graph.getViewport().setMaxY(yf+altura*Zoom);
                        }
                    }else{
                        if(yi==yf){
                            series.appendData(new DataPoint(xi, yi), true, 100);
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xi-ancho*Zoom);
                            graph.getViewport().setMaxX(xf+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-Zoom);
                            graph.getViewport().setMaxY(yf+Zoom);
                        }
                        else if(yi>yf){
                            series.appendData(new DataPoint(xi, yi), true, 100);
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xi-ancho*Zoom);
                            graph.getViewport().setMaxX(xf+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-altura*Zoom);
                            graph.getViewport().setMaxY(yi+altura*Zoom);
                        }else{
                            series.appendData(new DataPoint(xi, yi), true, 100);
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xi-ancho*Zoom);
                            graph.getViewport().setMaxX(xf+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-altura*Zoom);
                            graph.getViewport().setMaxY(yf+altura*Zoom);
                        }
                    }

                    series.setTitle("Recta");

                    LineGraphSeries<DataPoint> seriesTitlePoint1 = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(xi,yi)
                    });
                    LineGraphSeries<DataPoint> seriesTitlePoint2 = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(xf,yf)
                    });
                    seriesTitlePoint1.setTitle("P( XI , YI )\nP( "+xi+" , "+yi+" )");
                    seriesTitlePoint2.setTitle("P( XF , YF )\nP( "+xf+" , "+yf+" )");
                    seriesTitlePoint1.setColor(Color.GREEN);
                    seriesTitlePoint2.setColor(Color.RED);
                    seriesTitlePoint1.setDrawDataPoints(true);
                    seriesTitlePoint2.setDrawDataPoints(true);
                    seriesTitlePoint1.setDataPointsRadius(10f);
                    seriesTitlePoint2.setDataPointsRadius(10f);
                    graph.getLegendRenderer().setVisible(true);
                    graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graph.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
                    graph.addSeries(series);
                    graph.addSeries(seriesTitlePoint1);
                    graph.addSeries(seriesTitlePoint2);

                }catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Parabola
        parabola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graph.removeAllSeries();
                double xi,xf,yi,yf;
                double ancho,altura;

                try {
                    xi = Double.parseDouble(txtxIn.getText().toString());
                    xf = Double.parseDouble(txtxFn.getText().toString());
                    yi = Double.parseDouble(txtyIn.getText().toString());
                    yf = Double.parseDouble(txtyFn.getText().toString());
                    series.resetData(new DataPoint[]{});
                    ancho = Math.abs(xf-xi);
                    altura = Math.abs(yf-yi);
                    if(xi==xf){
                        if(yi==yf){
                            double[] dx = getNumeros(xf-ancho,xi);

                            for(int i=0;i<dx.length;i++){
                                series.appendData(new DataPoint(dx[i]+ancho,(Math.sin(Math.PI*(double)i/100)*altura)+yf), true, 100);
                            }

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xi-Zoom);
                            graph.getViewport().setMaxX(xf+Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-Zoom);
                            graph.getViewport().setMaxY(yf+Zoom);
                        }else if(yi>yf){
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-Zoom);
                            graph.getViewport().setMaxX(xi+Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-altura*Zoom);
                            graph.getViewport().setMaxY(yi+altura*Zoom);
                        }else{
                            series.appendData(new DataPoint(xf, yf), true, 100);
                            series.appendData(new DataPoint(xi, yi), true, 100);

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-Zoom);
                            graph.getViewport().setMaxX(xi+Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-altura*Zoom);
                            graph.getViewport().setMaxY(yf+altura*Zoom);
                        }
                    }
                    else if(xi>xf){//xinicial es mayor que xfinal
                        if(yi==yf){
                            double[] dx = getNumeros(xf-ancho,xi);

                            for(int i=0;i<dx.length;i++){
                                series.appendData(new DataPoint(dx[i]+ancho,(Math.sin(Math.PI*(double)i/100)*altura)+yf), true, 100);
                            }

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-ancho*Zoom);
                            graph.getViewport().setMaxX(xi+ancho+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-Zoom);
                            graph.getViewport().setMaxY(yi+Zoom);
                        }
                        else if(yi>yf){//yinicial es mayor que yfinal
                            //(3,2)(3,2)
                            double[] dx = getNumeros(xf-ancho,xi);

                            for(int i=0;i<dx.length;i++){
                                series.appendData(new DataPoint(dx[i]+ancho,(Math.sin(Math.PI*(double)i/100)*altura)+yf), true, 100);
                            }

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-ancho*Zoom);
                            graph.getViewport().setMaxX(xi+ancho+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-altura*Zoom);
                            graph.getViewport().setMaxY(yi+altura*Zoom);

                        }else{
                            double[] dx = getNumeros(xf,xi+ancho);

                            for(int i=0;i<dx.length;i++){
                                series.appendData(new DataPoint(dx[i],(Math.sin(Math.PI*(double)i/100)*-altura)+yf), true, 100);
                            }

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX(xf-ancho*Zoom);
                            graph.getViewport().setMaxX(xi+ancho+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-altura*Zoom);
                            graph.getViewport().setMaxY(yf+altura*Zoom);

                        }
                    }else{
                        if(yi==yf){
                            double[] dx = getNumeros(xi-ancho,xf);

                            for(int i=0;i<dx.length;i++){
                                series.appendData(new DataPoint(dx[i],(Math.sin(Math.PI*(double)i/100)*altura)+yf), true, 100);
                            }

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX((xi-(xf-xi))-ancho*Zoom);
                            graph.getViewport().setMaxX(xf+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-Zoom);
                            graph.getViewport().setMaxY(yi+Zoom);
                        }
                        else if(yi>yf){
                            double[] dx = getNumeros(xi-ancho,xf);

                            for(int i=0;i<dx.length;i++){
                                series.appendData(new DataPoint(dx[i],(Math.sin(Math.PI*(double)i/100)*altura)+yf), true, 100);
                            }

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX((xi-(xf-xi))-ancho*Zoom);
                            graph.getViewport().setMaxX(xf+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yf-altura*Zoom);
                            graph.getViewport().setMaxY(yi+altura*Zoom);
                        }else{
                            double[] dx = getNumeros(xi-ancho,xf);

                            for(int i=0;i<dx.length;i++){
                                series.appendData(new DataPoint(dx[i],(Math.sin(Math.PI*(double)i/100)*-altura)+yf), true, 100);
                            }

                            graph.getViewport().setXAxisBoundsManual(true);
                            graph.getViewport().setMinX((xi-(xf-xi))-ancho*Zoom);
                            graph.getViewport().setMaxX(xf+ancho*Zoom);

                            graph.getViewport().setYAxisBoundsManual(true);
                            graph.getViewport().setMinY(yi-altura*Zoom);
                            graph.getViewport().setMaxY(yf+altura*Zoom);
                        }
                    }

                    series.setTitle("Parabola");

                    LineGraphSeries<DataPoint> seriesTitlePoint1 = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(xi,yi)
                    });
                    LineGraphSeries<DataPoint> seriesTitlePoint2 = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(xf,yf)
                    });
                    seriesTitlePoint1.setTitle("P( XI , YI )\nP( "+xi+" , "+yi+" )");
                    seriesTitlePoint2.setTitle("P( XF , YF )\nP( "+xf+" , "+yf+" )");
                    seriesTitlePoint1.setColor(Color.GREEN);
                    seriesTitlePoint2.setColor(Color.RED);
                    seriesTitlePoint1.setDrawDataPoints(true);
                    seriesTitlePoint2.setDrawDataPoints(true);
                    seriesTitlePoint1.setDataPointsRadius(10f);
                    seriesTitlePoint2.setDataPointsRadius(10f);
                    graph.getLegendRenderer().setVisible(true);
                    graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graph.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
                    graph.addSeries(series);
                    graph.addSeries(seriesTitlePoint1);
                    graph.addSeries(seriesTitlePoint2);

                }catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        //zoom
        zoomMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(Zoom<=0.5)){
                    Zoom-=0.5;
                    double ancho = graph.getViewport().getMaxX(true)-graph.getViewport().getMinX(true);
                    double altura = graph.getViewport().getMaxY(true)-graph.getViewport().getMinY(true);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setMinX(graph.getViewport().getMinX(true)-(ancho!=0?ancho:1)*Zoom);
                    graph.getViewport().setMaxX(graph.getViewport().getMaxX(true)+(ancho!=0?ancho:1)*Zoom);

                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setMinY(graph.getViewport().getMinY(true)-(altura!=0?altura:1)*Zoom);
                    graph.getViewport().setMaxY(graph.getViewport().getMaxY(true)+(altura!=0?altura:1)*Zoom);

                    List<Series> temp = new ArrayList<Series>();
                    for(Series e : graph.getSeries()){
                        temp.add(e);
                    }
                    graph.removeAllSeries();
                    for(Series e : temp){
                        graph.addSeries(e);
                    }

                    zoomState.setText(" Zoom: "+(5.5-Zoom)+" ");
                }
            }
        });

        zoomMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(Zoom>=5)){
                    Zoom+=0.5;
                    double ancho = graph.getViewport().getMaxX(true)-graph.getViewport().getMinX(true);
                    double altura = graph.getViewport().getMaxY(true)-graph.getViewport().getMinY(true);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setMinX(graph.getViewport().getMinX(true)-(ancho!=0?ancho:1)*Zoom);
                    graph.getViewport().setMaxX(graph.getViewport().getMaxX(true)+(ancho!=0?ancho:1)*Zoom);

                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setMinY(graph.getViewport().getMinY(true)-(altura!=0?altura:1)*Zoom);
                    graph.getViewport().setMaxY(graph.getViewport().getMaxY(true)+(altura!=0?altura:1)*Zoom);

                    List<Series> temp = new ArrayList<Series>();
                    for(Series e : graph.getSeries()){
                        temp.add(e);
                    }
                    graph.removeAllSeries();
                    for(Series e : temp){
                        graph.addSeries(e);
                    }

                    zoomState.setText(" Zoom: "+(5.5-Zoom)+" ");
                }
            }
        });

        return v;
    }

    private double[] getNumeros(double xi, double xf){
        double xWidth = Math.abs(xf-xi);
        double[] matrizD = new double[100];
        if(xf>xi){
            for(double i=xi,j=0;i<xf && j<100;i+=xWidth/100,j++){
                matrizD[(int)j]=i;
            }
        }
        return matrizD;
    }
}
