package com.example.app03brincandocominteiros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView txtGerado;
    private EditText edtTransfere;
    private TextView txtParidade;
    private TextView txtPrimo;
    private TextView txtDivisores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtGerado = findViewById(R.id.txtGerado);
        edtTransfere = findViewById(R.id.edtTransfere);
        txtParidade = findViewById(R.id.txtParidade);
        txtPrimo = findViewById(R.id.txtPrimo);
        txtDivisores = findViewById(R.id.txtDivisores);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("gerado", txtGerado.getText().toString());
        savedInstanceState.putString("paridade", txtParidade.getText().toString());
        savedInstanceState.putString("primo", txtPrimo.getText().toString());
        savedInstanceState.putString("divisores", txtDivisores.getText().toString());
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String s;
        s = savedInstanceState.getString("gerado");
        txtGerado.setText(s);
        s = savedInstanceState.getString("paridade");
        txtParidade.setText(s);
        s = savedInstanceState.getString("primo");
        txtPrimo.setText(s);
        s = savedInstanceState.getString("divisores");
        txtDivisores.setText(s);
    }
    public void btnGerarOnClick(View view) {
        Random gerador = new Random();
        int valor = gerador.nextInt(100000);
        String s = String.format("%d", valor);
        txtGerado.setText(s);
    }
    public void btnTransferirOnClick(View view) {
        String s = txtGerado.getText().toString();
        if (s != "") {
            edtTransfere.setText(txtGerado.getText().toString());
        }
    }
    public void btnParidadeOnClick(View view) {
        String s = edtTransfere.getText().toString();
        try {
            int valor = Integer.parseInt(s);
            if (valor % 2 == 0)
                txtParidade.setText(String.format("%d é Par", valor));
            else
                txtParidade.setText(String.format("%d é Ímpar", valor));
        }
        catch (Exception e) {
            //Toast.makeText(this, "É necessário fornecer um número inteiro.", Toast.LENGTH_SHORT).show();
            exibeToast("É necessário fornecer um número inteiro.");
        }
    }
    public void btnPrimoOnClick(View view) {
        String s = edtTransfere.getText().toString();
        try {
            int valor = Integer.parseInt(s);
            if (ePrimo(valor))
                txtPrimo.setText(String.format("%d é Primo", valor));
            else
                txtPrimo.setText(String.format("%d não é Primo", valor));
        }
        catch (Exception e) {
            //Toast.makeText(this, "É necessário fornecer um número inteiro.", Toast.LENGTH_SHORT).show();
            exibeToast("É necessário fornecer um número inteiro.");
        }
    }
    private boolean ePrimo (int pN) {
        int i;
        int r = 1;
        double raiz;
        if (pN == 2)
            return true;
        else if (pN % 2 == 0)
            return false;
        else {
            raiz = Math.sqrt(pN);
            i = 3;
            while (i <= raiz && r != 0) {
                r = pN % i;
                i += 2;
            }
            return r != 0;
        }
    }
    public void txtDivisoresOnClick(View view) {
        String s = edtTransfere.getText().toString();
        try {
            int valor = Integer.parseInt(s);
            s = produzDivisores(valor);
            txtDivisores.setText(s);
            txtDivisores.setMovementMethod(new ScrollingMovementMethod());
            txtDivisores.scrollTo(0, 0);
        }
        catch (Exception e) {
            //Toast.makeText(this, "É necessário fornecer um número inteiro.", Toast.LENGTH_SHORT).show();
            exibeToast("É necessário fornecer um número inteiro.");
        }
    }
    private String produzDivisores(int valor) {
        int i;
        double fim;
        String s = "";
        fim = valor / 2;
        i = 2;
        while (i <= fim) {
            if (valor % i == 0)
                s = s + i + "\n";
            i++;
        }
        return s;
    }
    public void btnLimpaTudo(View view) {
        TextView v;
        v = findViewById(R.id.txtGerado);
        v.setText("");
        v = findViewById(R.id.txtParidade);
        v.setText("");
        v = findViewById(R.id.txtPrimo);
        v.setText("");
        v = findViewById(R.id.txtDivisores);
        v.setText("");
        v = findViewById(R.id.edtTransfere);
        v.setText("");
    }
    private void exibeToast(String txt) {
        LayoutInflater inflater = getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.toast, (ViewGroup)findViewById(R.id.raiz_layout_toast));
        ImageView imagem_toast = toastLayout.findViewById(R.id.imagem_toast);
        imagem_toast.setImageResource(R.drawable.ic_alerta);
        TextView texto_toast = toastLayout.findViewById(R.id.texto_toast);
        texto_toast.setText(txt);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastLayout);
        toast.show();
    }
}