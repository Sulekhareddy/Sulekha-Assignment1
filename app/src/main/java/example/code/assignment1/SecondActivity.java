package example.code.assignment1;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonRegister;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        sqliteHelper = new SqliteHelper(this);

        initViews();
        buttonRegister = (Button) findViewById(R.id.nextbutton);
        editTextEmail = (EditText) findViewById(R.id.emaileditText);
        editTextPassword = (EditText)  findViewById(R.id.passwordeditText);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {

                    String Email = editTextEmail.getText().toString();
                    Log.e("SecondActivity", "In validate" + Email);
                    String Password = editTextPassword.getText().toString();


                    if (!sqliteHelper.isEmailExists(Email)) {

                        sqliteHelper.addUser(new User(null, Email, Password));
                        Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {
                        Snackbar.make(buttonRegister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });
    }


    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.emaileditText);
        editTextPassword = (EditText) findViewById(R.id.passwordeditText);
        buttonRegister = (Button) findViewById(R.id.nextbutton);

    }


    public boolean validate() {
        boolean valid = false;

        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;

            Log.e("SecondActivity", "Please enter valid email!");
        } else {
            valid = true;

            Log.e("SecondActivity", "Email ok");
        }


        if (Password.isEmpty()) {
            valid = false;

            Log.e("SecondActivity", "Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;

                Log.e("SecondActivity", "password ok");
            } else {
                valid = false;

                Log.e("SecondActivity", "Password is to short!");
            }
        }


        return valid;
    }
}