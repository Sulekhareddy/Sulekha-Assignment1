package example.code.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserListActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        mTextView = (TextView) findViewById(R.id.usertextView);
        mTextView2 = (TextView) findViewById(R.id.welcometextView);

        final Intent intent = getIntent();

        String emailVal = intent.getStringExtra(LoginActivity.TEXT_KEY);
        mTextView.setText(emailVal);
    }
}
