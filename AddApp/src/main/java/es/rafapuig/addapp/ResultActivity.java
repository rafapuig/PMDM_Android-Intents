package es.rafapuig.addapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //TODO - get the caller intent and pull the data


        //TODO - Set this variable to the result
        String resultText = "";

        // textoResultado debe contener el texto a mostrar en lacaja de texto de la interfaz gráfica
        ((TextView) findViewById(R.id.txt_result)).setText(resultText);
    }


    public void onOk(View v) {
        //TODO set the result intent and call finish
    }

    public void onCancel(View v) {
        //TODO set the result intent and call finish
    }

}