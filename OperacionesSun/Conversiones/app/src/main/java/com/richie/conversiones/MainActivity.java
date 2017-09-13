package com.richie.conversiones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etValue1;
    private EditText etValue2;
    private EditText etBase;
    private Button btnSumar;
    private Button btnDivision;
    private Button btnModule;
    private Button btnExponentation;
    private Button btnMultiply;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setupLayout() {

        etValue1 = (EditText) findViewById(R.id.etValue1);
        etValue2 = (EditText) findViewById(R.id.etValue2);
        etBase = (EditText) findViewById(R.id.etBase);
        btnSumar = (Button) findViewById(R.id.btnSumar);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnModule = (Button) findViewById(R.id.btnModule);
        btnExponentation = (Button) findViewById(R.id.btnExponentation);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        tvResults = (TextView) findViewById(R.id.tvResults);

        btnSumar.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnModule.setOnClickListener(this);
        btnExponentation.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
    }

    private String reverseString(String output) {
        String reverse = "";
        for (int i = 1; i <= output.length(); i++) {
            reverse += output.charAt(output.length() - i);
        }
        return reverse;
    }

    //Completa con 0s al inicio de los numeros en binario
    private TwinsNumbers generateSameSizeInputs(String a, String b) {
        int sizeA = a.length();
        int sizeB = b.length();
        int range = (sizeA - sizeB);
        String zeros = "";

        range = range < 0 ? (-1) * range : range;

        while (range > 0) {
            zeros += "0";
            range--;
        }

        if (sizeA > sizeB) {
            b = zeros + b;
        } else {
            a = zeros + a;
        }

        return new TwinsNumbers(a, b);
    }

    //Agregamos 0s al final del numero binario
    private String addMissingZeros(int carry) {
        String zeros = "";
        for (int i = 0; i < carry; i++) {
            zeros += "0";
        }
        return zeros;
    }

    private int getValueFromEditText(EditText editText) {
        String value = editText.getText().toString();
        return value.isEmpty() ? 0 : Integer.parseInt(value);
    }

    //OPERATIONS
    private String getExpansion(int base, int value) {
        String output = "";
        int q = value;
        do {
            output += "" + q % base;
            q = q / base;
        } while (q != 0);
        return reverseString(output);
    }

    private String addition(String a, String b) {

        int c = 0;
        float d;
        String s = "";
        int size = a.length();

        for (int j = size - 1; j >= 0; j--) {
            int valueA = Integer.parseInt("" + a.charAt(j));
            int valueB = Integer.parseInt("" + b.charAt(j));
            d = (valueA + valueB + c) / 2;
            s = ((valueA + valueB + c) - (int) (2 * d)) + "" + s;
            c = (int) d;
        }

        s = c + "" + s;

        return s;
    }

    private String multiplication(String a, String b) {

        int acarreo = 0;
        int sizeA = a.length();
        int sizeB = b.length();
        String productString;
        List<String> multiplicandos = new ArrayList<>();

        while (sizeB > 0) {
            sizeB--;
            int bi = Integer.valueOf("" + b.charAt(sizeB));

            productString = "";
            while (sizeA > 0) {
                sizeA--;
                int ai = Integer.valueOf("" + a.charAt(sizeA));
                int multiplication = ai * bi;
                productString = multiplication + "" + productString;
            }
            sizeA = a.length(); //restaurar valor de aSize
            multiplicandos.add(productString);
            acarreo++;
        }

        //poner acarreo
        for (int i = 0; i < acarreo; i++) {
            String mul = multiplicandos.get(i);
            mul = mul + addMissingZeros(i);
            multiplicandos.set(i, mul);
        }

        //hacer suma
        for (int i = 0; i < multiplicandos.size() - 1; i++) {
            TwinsNumbers tn = generateSameSizeInputs(multiplicandos.get(i), multiplicandos.get(i + 1));
            multiplicandos.set(i + 1, addition(tn.getValueA(), tn.getValueB()));
        }

        return multiplicandos.get(multiplicandos.size() - 1);
    }

    private QuotientModule division(int a, int d) {

        int q = 0;
        int r = a < 0 ? (-1) * (a) : a;  //absolute value of 'a'

        while (r >= d) {
            //en el caso que a sea negativo
            /*if (a < 0 && r > 0) {
                r = r - d;// d - r;
                //q = (-1) * (q + 1);
                q--;
            }else{
                r = r - d;
                q++;
            }*/
            r = r - d;
            q++;
        }
        if(a<0 && r>0){
            r = d - r;
            q = (-1) * (q+1);
        }

        // (q,r) { q = a div d is the quotient, r = a mod d is the remainder }
        return new QuotientModule(q, r);
    }

    private int modularExponentation(int b, String value, int m) {
        value = reverseString(value);
        int x = 1;
        int power = b % m;

        for (int i = 0; i < value.length(); i++) {
            if (Integer.parseInt("" + value.charAt(i)) == 1) {
                x = (x * power) % m;
            }
            power = (power * power) % m;
        }

        return x;
    }

    @Override
    public void onClick(View view) {

        int valueA = getValueFromEditText(etValue1);
        int valueB = getValueFromEditText(etValue2);
        int base = getValueFromEditText(etBase);

        String expansionValueA = getExpansion(2, valueA);
        String expansionValueB = getExpansion(2, valueB);
        TwinsNumbers twinsNumbers = generateSameSizeInputs(expansionValueA, expansionValueB);

        switch (view.getId()) {
            case R.id.btnSumar:
                int additionValue = valueA + valueB;
                String result = addition(twinsNumbers.getValueA(), twinsNumbers.getValueB());
                tvResults.setText("a = " + expansionValueA + "\nb = " + expansionValueB +
                        " \nresult = " + result + "\nDec= " + additionValue);
                break;
            case R.id.btnDivision:
                if (valueB == 0) {
                    tvResults.setText("Ingresa valor2 diferente de 0");
                } else {
                    tvResults.setText("Quotient = " + division(valueA, valueB).getQuotient());
                }
                break;
            case R.id.btnModule:
                if (valueB == 0) {
                    tvResults.setText("Ingresa valor2 diferente de 0");
                } else {
                    tvResults.setText("Modulo = " + division(valueA, valueB).getModule());
                }
                break;
            case R.id.btnMultiply:
                int productValue = valueA * valueB;
                String productString = "Multiplicación \nBinario= " +
                        multiplication(twinsNumbers.getValueA(), twinsNumbers.getValueB())
                        + "\nDecimal = " + productValue;
                tvResults.setText(productString);
                break;
            case R.id.btnExponentation:
                if (base == 0 || valueB == 0) {
                    tvResults.setText("Error, ingresa base");
                } else {
                    String equation = "" + base + "^" + valueA + " mod " + valueB;
                    tvResults.setText(equation + " = " + modularExponentation(base, expansionValueA, valueB));
                }
                break;
        }
    }
}
