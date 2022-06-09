package talktome.com;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // login button handler
        Button btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(v -> {
            EditText userNameInput = (EditText)findViewById(R.id.editTextTextPersonName);
            String user_name = userNameInput.getText().toString();
            EditText passwordInput = (EditText)findViewById(R.id.editTextTextPassword);
            String user_password = passwordInput.getText().toString();
            
            //check validation of user name & password (need to send to server side)
            Intent i = new Intent(MainActivity.this, ContactsChatActivity.class);
            startActivity(i);
        });


        TextView textView = findViewById(R.id.textView1);
        String text = "New User? Click here";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "here", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(MainActivity.this, RegisterPageActivity.class);
                startActivity(a);
            }
        };
        ss.setSpan(clickableSpan1, 16, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}