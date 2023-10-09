package es.rafapuig.subtractapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_ACTIVITY_REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onCalcuteClick(View v) {
        String ope1S = ((EditText) findViewById(R.id.txt_ope1)).getText().toString();
        String ope2S = ((EditText) findViewById(R.id.txt_ope2)).getText().toString();

        // ope1S y ope2S are the operands entered by the user in the UI

        //TODO ---

        int ope1 = ope1S.isEmpty() ? 0 : Integer.parseInt(ope1S);
        int ope2 = ope2S.isEmpty() ? 0 : Integer.parseInt(ope2S);

        Intent intent = new Intent("es.rafapuig.addapp.OPERATION-RESULT");
        intent.putExtra("OP1", ope1);
        intent.putExtra("OP2", ope2);
        intent.putExtra("OPERATION", "-");
        //intent.putExtra("RESULT", ope1 + ope2);

        //Only start without expecting a result back
        //startActivity(intent);

        //Old way to start Activity for result (deprecated)
        //startActivityForResult(intent, RESULT_ACTIVITY_REQUEST_CODE);

        //New and current way to do it
        startForResult.launch(intent);
    }


    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    processResultData(result.getResultCode(), result.getData());
                }
            }
    );

    private void processResultData(int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (data.hasExtra("result1")) {
                String info = data.getStringExtra("result1");
                //...
            }
        }


        boolean agreed = resultCode == RESULT_OK; //data.getBooleanExtra(ResultActivity.AGREE, false);

        int result = data.getIntExtra("es.rafapuig.addapp.resultactivity.OPERATION_RESULT", 0);

        String agreement = (agreed ? "Yes, I" : "No, I don't") + " agree with the solution: " + result;

        // String "agreement" should contain the text to show in the UI
        ((TextView) findViewById(R.id.txt_agreement)).setText(agreement);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        processResultData(resultCode, data);
    }
}