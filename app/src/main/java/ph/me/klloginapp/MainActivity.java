package ph.me.klloginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText email = findViewById(R.id.email);
        EditText pass1 = findViewById(R.id.pass1);
        EditText pass2 = findViewById(R.id.pass2);
        EditText passLength = findViewById(R.id.ViewPassLength);
        Button btnZatwierdz = findViewById(R.id.btnZatwierdz);
        Button btnMoc = findViewById(R.id.btnMoc);
        Button btnGeneruj = findViewById(R.id.btnGeneruj);
        TextView bottomtext = findViewById(R.id.bottomText);
        TextView TextDuze = findViewById(R.id.textView4);
        TextView TextMale = findViewById(R.id.textView5);
        TextView TextSpecjalne = findViewById(R.id.textView6);
        TextView TextCyfry = findViewById(R.id.textView7);
        TextView TextDlugosc = findViewById(R.id.textView8);

        btnZatwierdz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(CheckEmailValidity(email,bottomtext)){
                            if (CheckPasswordMatching(pass1,pass2,bottomtext) && !pass1.getText().toString().isEmpty()){
                                String finalText = "Witaj " + email.getText().toString();
                                bottomtext.setText(finalText);
                            }
                        }
                    }
                }
        );
        btnMoc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String password = pass1.getText().toString();
                        ChangeColor(TextDuze,ContainsUppercase(password));
                        ChangeColor(TextMale,ContainsLowercase(password));
                        ChangeColor(TextSpecjalne,ContainsSpecials(password));
                        ChangeColor(TextCyfry,ContainsNumbers(password));
                        ChangeColor(TextDlugosc,CheckStringLength(password,12));
                    }
                }
        );
        btnGeneruj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!passLength.getText().toString().isEmpty()){
                            if (Integer.parseInt(passLength.getText().toString()) >= 4){
                                String password = GeneratePass(Integer.parseInt(passLength.getText().toString()));
                                pass1.setText(password);
                                pass2.setText(password);
                            }
                        }
                    }
                }
        );
    }
    public boolean CheckEmailValidity(EditText email, TextView errorTextView){
        char[] emailArray = email.getText().toString().toCharArray();
        boolean gut = false;
        for (char c : emailArray){
            if (c == '@'){
                gut = true;
                break;
            }
        }
        if(gut){
            errorTextView.setText("");
            return true;
        }
        else {
            errorTextView.setText("Nieprawidlowy adres email");
            return false;
        }
    }
    public boolean CheckPasswordMatching(EditText first, EditText second, TextView errorTextView){
        if (first.getText().toString().equals(second.getText().toString())){
            errorTextView.setText("");
            return true;
        }
        else {
            errorTextView.setText("Hasla sie roznia");
            return false;
        }
    }

    public boolean ContainsUppercase(String string){
        char[] uppercaseLetters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] stringArray = string.toCharArray();
        boolean gut = false;
        for (char sChar : stringArray) {
            for (char letter : uppercaseLetters){
                if (sChar == letter){
                    gut = true;
                    break;
                }
            }
        }
        return gut;
    }
    public boolean ContainsLowercase(String string){
        char[] lowercaseLetters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] stringArray = string.toCharArray();
        boolean gut = false;
        for (char sChar : stringArray) {
            for (char letter : lowercaseLetters){
                if (sChar == letter){
                    gut = true;
                    break;
                }
            }
        }
        return gut;
    }
    public boolean ContainsNumbers(String string){
        char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
        char[] stringArray = string.toCharArray();
        boolean gut = false;
        for (char sChar : stringArray) {
            for (char number : numbers){
                if (sChar == number){
                    gut = true;
                    break;
                }
            }
        }
        return gut;
    }
    public boolean ContainsSpecials(String string){
        char[] specials = {'?','!','@','#','$','%','^','&','*'};
        char[] stringArray = string.toCharArray();
        boolean gut = false;
        for (char sChar : stringArray) {
            for (char special : specials){
                if (sChar == special){
                    gut = true;
                    break;
                }
            }
        }
        return gut;
    }
    public boolean CheckStringLength(String string, int minLength){
        return (string.length() >= minLength);
    }
    public void ChangeColor(TextView textView, boolean color){
        if (color){
            textView.setTextColor(getColor(R.color.Green));
        }
        else{
            textView.setTextColor(getColor(R.color.Red));
        }
    }

    public String GeneratePass(int length){
        Random random = new Random();
        String password = "";
        char[] characters = {'?','!','@','#','$','%','^','&','*','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        while(!(ContainsLowercase(password)&&ContainsNumbers(password)&&ContainsSpecials(password)&&ContainsUppercase(password))){
            password = "";
            for (int i = 0; i < length; i++) {
                password = password + characters[random.nextInt(characters.length)];
            }
        }
        return password;
    }
}