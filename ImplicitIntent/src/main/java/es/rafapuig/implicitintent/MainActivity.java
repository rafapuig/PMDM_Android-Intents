package es.rafapuig.implicitintent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int CALL_PHONE_PERMISSION_REQUEST = 9;
    final static int INTERNET_PERMISSION_REQUEST = 8;

    EditText etPhone;
    EditText etNavigateURL;
    EditText etEmailAddress;
    EditText etEmailSubject;
    EditText etEmailBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = findViewById(R.id.editTextPhone);
        etNavigateURL = findViewById(R.id.et_url_to_navigate);
        etEmailAddress = findViewById(R.id.editEmailAddress);
        etEmailSubject = findViewById(R.id.editTextSubject);
        etEmailBody = findViewById(R.id.editMailBody);
    }

    public void onTakingAPhoto(View view) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }


    public void onMakingACall(View view) {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST);
        } else {
            makePhoneCall();
        }
    }

    void makePhoneCall() {
        String phoneNumber = etPhone.getText().toString();
        callPhone(phoneNumber);
    }

    void callPhone(String phoneNumber) {
        Intent phoneCall = new Intent();
        phoneCall.setAction(Intent.ACTION_CALL);
        phoneCall.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(phoneCall);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        boolean permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

        switch (requestCode) {
            case CALL_PHONE_PERMISSION_REQUEST:
                if (permissionGranted) {
                    makePhoneCall();
                }
                break;
            case INTERNET_PERMISSION_REQUEST:
                if (permissionGranted) {
                    navigateTo(etNavigateURL.getText().toString());
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onNavigatingToURL(View view) {
        String url = etNavigateURL.getText().toString();
        navigateTo(url);
    }


    void navigateTo(String url) {
        Intent navigate = new Intent();
        navigate.setAction(Intent.ACTION_VIEW);
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://" + url;
        }
        navigate.setData(Uri.parse(url));
        startActivity(navigate);
    }

    public void onSendingEmail(View view) {
        String[] addresses = new String[]{etEmailAddress.getText().toString()};
        String subject = etEmailSubject.getText().toString();
        String message = etEmailBody.getText().toString();

        composeEmail(addresses, subject, message);
    }

    void composeEmail(String[] addresses, String subject, String message) {
        Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
        sendEmail.setData(Uri.parse("mailto:"));

        sendEmail.setType("*/*");
        sendEmail.putExtra(Intent.EXTRA_EMAIL, addresses);
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendEmail.putExtra(Intent.EXTRA_TEXT, message);
        if (sendEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmail);
        }
    }


}