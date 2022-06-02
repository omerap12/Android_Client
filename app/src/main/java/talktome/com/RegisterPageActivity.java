package talktome.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        //the click to go back to main activity
        String text = "Already registered? click here";
        TextView textView = findViewById(R.id.textView2);
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            public void onClick(View widget) {
                Toast.makeText(RegisterPageActivity.this, "here", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(RegisterPageActivity.this, MainActivity.class);
                startActivity(a);
            }
        };
        ss.setSpan(clickableSpan1, 26, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        //register button
        Button btn_login = findViewById(R.id.register_btn);
        btn_login.setOnClickListener(v -> {
            //get user+nick name and passwords - validate all and send to the server
        });

        //upload photo button
        Button btn_upload_photo = findViewById(R.id.button_upload_photo);
        btn_upload_photo.setOnClickListener(v -> {

        });
    }
}