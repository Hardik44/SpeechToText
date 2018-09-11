package com.example.hardik.tryspeechtotext;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements RecognitionListener {

    EditText editText;
    Button btn;
    private SpeechRecognizer recognizer;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.button);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        recognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        recognizer.setRecognitionListener(MainActivity.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recognizer.startListening(intent);
               // Toast.makeText(getApplicationContext(),"HIIIIIIIIIIIIIIIIII",Toast.LENGTH_LONG).show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECORD_AUDIO )!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.RECORD_AUDIO}, 156);
        }

    }


    @Override
    public void onReadyForSpeech(Bundle bundle) {
       // Toast.makeText(getApplicationContext(),"Ready for speech",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBeginningOfSpeech() {
       // Toast.makeText(getApplicationContext(),"Begginig of speech",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
      //Toast.makeText(getApplicationContext(),"End of speech",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(int i) {
       //    Toast.makeText(getApplicationContext(),i+"",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResults(Bundle bundle) {
        String result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
        editText.setText(result);
    }

    @Override
    public void onPartialResults(Bundle bundle) {
        String result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
        editText.setText(result);
    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 156) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "HO HO", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
