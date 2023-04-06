package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button buttonUm, buttonDois, buttonTres, buttonQuatro, buttonCinco,
            buttonSeis, buttonSete, buttonOito, buttonNove, buttonZero,
            buttonIgual, buttonMultiplicacao, buttonDivisao, buttonSoma, buttonSubtracao,
            buttonPorcento, buttonVirgula,
            buttonReset, buttonDelete;
    private TextView textViewResultado, textViewUltimaExpressao;
    private String ultimaExpressao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);
        buttonDelete = findViewById(R.id.buttonDeleteID);
        buttonReset = findViewById(R.id.buttonResetID);
        buttonIgual = findViewById(R.id.buttonIgualID);
        buttonMultiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        buttonDivisao = findViewById(R.id.buttonDivisaoID);
        buttonPorcento = findViewById(R.id.buttonPorcentoID);
        buttonSoma = findViewById(R.id.buttonSomaID);
        buttonSubtracao = findViewById(R.id.buttonSubtracaoID);
        buttonVirgula = findViewById(R.id.buttonVirgulaID);
        buttonUm = findViewById(R.id.buttonUmID);
        buttonDois = findViewById(R.id.buttonDoisID);
        buttonTres = findViewById(R.id.buttonTresID);
        buttonQuatro = findViewById(R.id.buttonQuatroID);
        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSete = findViewById(R.id.buttonSeteID);
        buttonOito = findViewById(R.id.buttonOitoID);
        buttonNove = findViewById(R.id.buttonNoveID);
        buttonZero = findViewById(R.id.buttonZeroID);

        buttonSoma.setOnClickListener(this);
        buttonSubtracao.setOnClickListener(this);
        buttonMultiplicacao.setOnClickListener(this);
        buttonDivisao.setOnClickListener(this);
        buttonPorcento.setOnClickListener(this);
        buttonVirgula.setOnClickListener(this);
        buttonZero.setOnClickListener(this);
        buttonUm.setOnClickListener(this);
        buttonDois.setOnClickListener(this);
        buttonTres.setOnClickListener(this);
        buttonQuatro.setOnClickListener(this);
        buttonCinco.setOnClickListener(this);
        buttonSeis.setOnClickListener(this);
        buttonSete.setOnClickListener(this);
        buttonOito.setOnClickListener(this);
        buttonNove.setOnClickListener(this);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ultimaExpressao = "";
                textViewUltimaExpressao.setText("");
                textViewResultado.setText("0");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewResultado.setText(" ");
                if (!ultimaExpressao.isEmpty())
                    ultimaExpressao = ultimaExpressao.substring(0, ultimaExpressao.length() - 1);
                if(ultimaExpressao.isEmpty())
                    textViewResultado.setText("0");
                textViewUltimaExpressao.setText(ultimaExpressao);
            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculable avaliadorExpressao = null;
                try {

                    if (!ultimaExpressao.isEmpty() && (ultimaExpressao.endsWith("+") || ultimaExpressao.endsWith("-") || ultimaExpressao.endsWith("*") || ultimaExpressao.endsWith("/"))) {
                        ultimaExpressao = ultimaExpressao.substring(0, ultimaExpressao.length() - 1);
                    }

                    avaliadorExpressao = new ExpressionBuilder(ultimaExpressao).build();

                    Double resultado = avaliadorExpressao.calculate();
                    long longResult = (long) resultado.doubleValue();
                    if (resultado == (double) longResult) {
                        textViewResultado.setText((CharSequence) String.valueOf(longResult));
                    } else {
                        textViewResultado.setText((CharSequence) String.valueOf(resultado));
                    }

                    ultimaExpressao = textViewResultado.getText().toString();

                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }

    public void AdicionaValor(String string) {
        if (ultimaExpressao == null)
            ultimaExpressao = "";

        if (string == "." && ultimaExpressao == "")
            ultimaExpressao = "0";

        if(string == "+" || string == "-" || string == "*" || string == "/") {
            if (!ultimaExpressao.isEmpty() && (ultimaExpressao.endsWith("+") || ultimaExpressao.endsWith("-") || ultimaExpressao.endsWith("*") || ultimaExpressao.endsWith("/"))) {
                ultimaExpressao = ultimaExpressao.substring(0, ultimaExpressao.length() - 1);
            }
        }
        ultimaExpressao += string;
        textViewUltimaExpressao.setText("");

        textViewUltimaExpressao.setText(ultimaExpressao);
        textViewResultado.setText(" ");

    }


    private static final Map<Integer, String> VALORES_BOTOES = new HashMap<Integer, String>() {{
        put(R.id.buttonSomaID, "+");
        put(R.id.buttonSubtracaoID, "-");
        put(R.id.buttonMultiplicacaoID, "*");
        put(R.id.buttonDivisaoID, "/");
        put(R.id.buttonPorcentoID, "%");
        put(R.id.buttonVirgulaID, ".");
        put(R.id.buttonZeroID, "0");
        put(R.id.buttonUmID, "1");
        put(R.id.buttonDoisID, "2");
        put(R.id.buttonTresID, "3");
        put(R.id.buttonQuatroID, "4");
        put(R.id.buttonCincoID, "5");
        put(R.id.buttonSeisID, "6");
        put(R.id.buttonSeteID, "7");
        put(R.id.buttonOitoID, "8");
        put(R.id.buttonNoveID, "9");
    }};

    @Override
    public void onClick(View view) {
        String valor = VALORES_BOTOES.get(view.getId());
        if (valor != null) {
            AdicionaValor(valor);
        }
    }
}