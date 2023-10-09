package es.rafapuig.addapp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onCalcuteClick(View v) {
        String ope1S = ((EditText) findViewById(R.id.txt_ope1)).getText().toString();
        String ope2S = ((EditText) findViewById(R.id.txt_ope2)).getText().toString();

        // ope1S y ope2S are the operands entered by the user in the UI

        //TODO - put data to the intent
        //TODO - Call the ResultActivity
    }


}