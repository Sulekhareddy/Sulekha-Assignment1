package example.code.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private Button mLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   mButton = (Button) findViewById(R.id.createaccbutton);
   mLoginButton = findViewById(R.id.loginbutton);

    mButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
        }
    });

    mLoginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        }
    });

    }
}
