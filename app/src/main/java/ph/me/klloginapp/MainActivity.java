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
        Button btnZatwierdz = findViewById(R.id.btnZatwierdz);
        TextView bottomtext = findViewById(R.id.bottomText);

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
            return true;
        }
        else {
            errorTextView.setText("Nieprawidlowy adres email");
            return false;
        }
    }
    public boolean CheckPasswordMatching(EditText first, EditText second, TextView errorTextView){
        if (first.getText().toString().equals(second.getText().toString())){
            return true;
        }
        else {
            errorTextView.setText("Hasla sie roznia");
            return false;
        }
    }
}