package es.rafapuig.addapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public static final String OPERATION_RESULT = "es.rafapuig.addapp.resultactivity.OPERATION_RESULT";

    int operationResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent caller = getIntent();
        int op1 = caller.getIntExtra("OP1", 0);
        int op2 = caller.getIntExtra("OP2", 0);
        String operation = caller.getStringExtra("OPERATION");

        String resultText = "";

        operationResult = calculate(op1, op2, operation);
        resultText =  String.format("El resultado de %s%s%s es igual a %d", op1, operation, op2, operationResult);

        // textoResultado debe contener el texto a mostrar en lacaja de texto de la interfaz gráfica
        ((TextView) findViewById(R.id.txt_result)).setText(resultText);
    }

    int calculate(int operand1, int operand2, String operation) {
        return switch (operation) {
            case "+" -> operand1 + operand2;
            default -> 0;
        };
    }

    boolean agreed;

    public void onOk(View v) {
        agreed = true;
        finish();
    }

    public void onCancel(View v) {
        agreed = false;
        finish();
    }

    @Override
    public void finish() {
        Intent result = new Intent();
        result.putExtra(OPERATION_RESULT, operationResult);
        int resultCode = agreed ? AppCompatActivity.RESULT_OK : AppCompatActivity.RESULT_CANCELED;
        setResult(resultCode, result);

        super.finish();
    }
}