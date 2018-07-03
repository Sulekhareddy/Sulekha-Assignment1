package example.code.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    public static final String TEXT_KEY = "user_object_key";

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    Button buttonLogin;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);

        initViews();
        buttonLogin = (Button) findViewById(R.id.loginbutton2);
        editTextEmail = (EditText) findViewById(R.id.emaillogineditText);
        editTextPassword = (EditText) findViewById(R.id.passlogineditText);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();
                    Log.e("LoginActivity", "In validate" + Email);


                    User currentUser = sqliteHelper.Authenticate(new User(null,  Email, Password));


                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
                        intent.putExtra(TEXT_KEY, currentUser.getmEmail());
                        Log.i("LoginActivity","Login " + TEXT_KEY);
                        startActivity(intent);
                    } else {
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                }

            }
        });


    }


    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.emaillogineditText);
        editTextPassword = (EditText) findViewById(R.id.passlogineditText);
         buttonLogin = (Button) findViewById(R.id.loginbutton2);

    }


    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }


    public boolean validate() {
        boolean valid = false;


        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }


        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is too short!");
            }
        }

        return valid;
    }
}

