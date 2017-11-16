package android.app.taller.proyect.com.tallerappsandroid;
//Librerias importadas
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created by trabajo on 17/09/2017. 17
 */

public class CalculatorFragment extends Fragment {


    //Declaro variables
    private TextView _screen;
    private String display = "";
    private String operador = "";
    private String resultado = "";
    private Button btnNumero0, btnNumero1, btnNumero2, btnNumero3, btnNumero4, btnNumero5, btnNumero6, btnNumero7, btnNumero8, btnNumero9, limpiar,igual,suma,resta,division,multiplicacion;




    // Creo las vistas
    private View.OnClickListener onClickNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClickEnNumero(view);
        }
    };

    private View.OnClickListener onClickOperator = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClickEnOperador(view);
        }
    };

    private View.OnClickListener onClickClear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClickEnClear(view);
        }
    };

    private View.OnClickListener onClickigual = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClickEnIgual(view);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculadora,container,false);


        //Instancio, para Reconocer el id del xml
        _screen = (TextView)v.findViewById(R.id.textView);
        btnNumero0 = (Button) v.findViewById(R.id.btn0);
        btnNumero1 = (Button) v.findViewById(R.id.btn1);
        btnNumero2 = (Button) v.findViewById(R.id.btn2);
        btnNumero3 = (Button) v.findViewById(R.id.btn3);
        btnNumero4 = (Button) v.findViewById(R.id.btn4);
        btnNumero5 = (Button) v.findViewById(R.id.btn5);
        btnNumero6 = (Button) v.findViewById(R.id.btn6);
        btnNumero7 = (Button) v.findViewById(R.id.btn7);
        btnNumero8 = (Button) v.findViewById(R.id.btn8);
        btnNumero9 = (Button) v.findViewById(R.id.btn9);

        limpiar = (Button) v.findViewById(R.id.btnClear);
        igual = (Button) v.findViewById(R.id.btnEqual);
        suma = (Button) v.findViewById(R.id.btnPlus);
        resta = (Button) v.findViewById(R.id.btnMinus);
        multiplicacion = (Button) v.findViewById(R.id.btnMult);
        division = (Button) v.findViewById(R.id.btnDiv);


        //Relaciono el evento, para Reconocer el id del xml
        btnNumero0.setOnClickListener(onClickNumber);
        btnNumero1.setOnClickListener(onClickNumber);
        btnNumero2.setOnClickListener(onClickNumber);
        btnNumero3.setOnClickListener(onClickNumber);
        btnNumero4.setOnClickListener(onClickNumber);
        btnNumero5.setOnClickListener(onClickNumber);
        btnNumero6.setOnClickListener(onClickNumber);
        btnNumero7.setOnClickListener(onClickNumber);
        btnNumero8.setOnClickListener(onClickNumber);
        btnNumero9.setOnClickListener(onClickNumber);

        limpiar.setOnClickListener(onClickClear);
        igual.setOnClickListener(onClickigual);
        suma.setOnClickListener(onClickOperator);
        resta.setOnClickListener(onClickOperator);
        division.setOnClickListener(onClickOperator);
        multiplicacion.setOnClickListener(onClickOperator);

        _screen.setText(display);
        return v;
    }

    private void ActualizarPantalla(){
        _screen.setText(display);//.setText para asignar
    }

    //metodo para el evento ClickEnNumero
    public void ClickEnNumero(View v){
        if(resultado != ""){
            clear();
            ActualizarPantalla();
        }
        Button b = (Button) v;
        display += b.getText();
        ActualizarPantalla();
    }


    private boolean TipoOperador(char op){
        switch (op){
            case '+':
            case '-':
            case 'x':
            case '÷':
                return true;
            default: return false;
        }
    }


    //metodo para el evento ClickEnOperador
    public void ClickEnOperador(View v){
        if(display == "") return;
        Button b = (Button)v;
        if(resultado != ""){
            String _display = resultado;
            clear();
            display = _display;
        }

        if(operador != ""){
            Log.d("CalcX", ""+display.charAt(display.length()-1));
            if(TipoOperador(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1), b.getText().charAt(0));
                ActualizarPantalla();
                return;
            }else{
                ObtenerResultado();
                display = resultado;
                resultado = "";
            }
            operador = b.getText().toString();
        }
        display += b.getText();
        operador = b.getText().toString();
        ActualizarPantalla();
    }

    private void clear(){
        display = "";
        operador = "";
        resultado = "";
    }


    //metodo para el evento ClickEnClear
    public void ClickEnClear(View v){
        clear();
        ActualizarPantalla();
    }


    //metodo para el evento operacion
    private double operacion(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "÷": try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.d("Calc", e.getMessage());
            }
            default: return -1;
        }
    }


    //metodo para el evento ObtenerResultado
    private boolean ObtenerResultado(){
        if(operador == "") return false;
        String[] operation = display.split(Pattern.quote(operador));
        if(operation.length < 2) return false;
        resultado = String.valueOf(operacion(operation[0], operation[1], operador));
        return true;
    }



    //metodo para el evento ClickEnIgual
    public void ClickEnIgual(View v){
        if(display == "") return;
        if(!ObtenerResultado()) return;
        _screen.setText(display + "\n" + String.valueOf(resultado));
    }

}
