package com.eastesh.darkdialer;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Activity _activity=this;
    private Intent intent = new Intent(Intent.ACTION_CALL);
    Button btnOne;
    ImageButton btnRate;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnStar;
    Button btnZero;
    Button btnHash;
    ImageView btnDelete;
    Button btnPlus;
    Button btnCall;
    EditText input;
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnRate=findViewById(R.id.imageButton);
        btnOne = findViewById(R.id.buttonOne);
        btnTwo = findViewById(R.id.buttonTwo);
        btnThree = findViewById(R.id.buttonThree);
        btnFour = findViewById(R.id.buttonFour);
        btnFive = findViewById(R.id.buttonFive);
        btnSix = findViewById(R.id.buttonSix);
        btnSeven = findViewById(R.id.buttonSeven);
        btnEight = findViewById(R.id.buttonEight);
        btnNine = findViewById(R.id.buttonNine);
        btnStar = findViewById(R.id.buttonStar);
        btnZero = findViewById(R.id.buttonZero);
        btnHash = findViewById(R.id.buttonHash);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCall = findViewById(R.id.buttonCall);
        btnPlus=findViewById(R.id.buttonPlus);
        input = findViewById(R.id.editText);
        input.setShowSoftInputOnFocus(false);
        input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(_activity);
                return false;
            }
        });
        btnDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                input.setText("");
                return false;
            }
        });
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager imn=(InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view=activity.getCurrentFocus();
        if(view==null){
            view = new View(activity);
        }
        imn.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    public void rateMe(View v){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id="+getPackageName())));
        }catch(ActivityNotFoundException e)
        {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName())));
        }
    }

    public void one(View v) {
        onButtonClick(input, "1");
    }

    public void two(View v) {
        onButtonClick(input, "2");
    }

    public void three(View v) {
        onButtonClick(input, "3");
    }

    public void four(View v) {
        onButtonClick(input, "4");
    }

    public void five(View v) {
        onButtonClick(input, "5");
    }

    public void six(View v) {
        onButtonClick(input, "6");
    }

    public void seven(View v) {
        onButtonClick(input, "7");
    }

    public void eight(View v) {
        onButtonClick(input, "8");
    }

    public void nine(View v) {
        onButtonClick(input, "9");
    }

    public void star(View v) {
        onButtonClick(input, "*");
    }

    public void zero(View v) {
        onButtonClick(input, "0");
    }

    public void hash(View v) {
        onButtonClick( input, "#");
    }
    public void plus(View v){
        onButtonClick(input,"+");
    }

    public void onDelete(View v) {
        String text=input.getText().toString();
        if(text.length()>0){
            input.setText(text.substring(0,text.length()-1));
            input.setSelection(input.getText().length());
        }else{
            Toast.makeText(this,"Empty Number",Toast.LENGTH_SHORT).show();
        }
    }

    public void onDial(View v) {
        if (input.getText().length() <3) {
            Toast.makeText(this, "Please Enter the Valid Number", Toast.LENGTH_SHORT).show();
        } else {
            String hash = input.getText().toString();
            intent.setData(Uri.parse("tel:" +Uri.encode(hash)));
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            }else{
                requestCallPermission();
            }
        }
    }

    private void requestCallPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==MY_PERMISSIONS_REQUEST_CALL_PHONE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"PERMISSION GRANTED",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }else{
                Toast.makeText(this,"PERMISSION DENIED",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onButtonClick(EditText inputNumber, String number){
        inputNumber.append(number);
    }
}
