package talktome.com;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class RegisterPageActivity extends AppCompatActivity {
    ImageView imageView;

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
            EditText userNameInput = (EditText)findViewById(R.id.editTextPersonName3);
            String user_name = userNameInput.getText().toString();

            EditText nickNameInput = (EditText)findViewById(R.id.editTextTextPersonName3);
            String nick_name = nickNameInput.getText().toString();

            EditText firstPasswordInput = (EditText)findViewById(R.id.editTextTextPassword3);
            String first_password = firstPasswordInput.getText().toString();

            EditText secondPasswordInput = (EditText)findViewById(R.id.editTextTextPassword2);
            String second_password = secondPasswordInput.getText().toString();


        });

        //upload photo button
        Button btn_upload_photo = findViewById(R.id.button_upload_photo);
        btn_upload_photo.setOnClickListener(v -> {
            imageChooser();
        });
    }

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        imageView.setImageBitmap(selectedImageBitmap);
                    }
                }
            });

    //need to complete this and to check how to do alert
    int validation(String userName, String nickName, String p1, String p2) {
        if (p1.equals(p2)) {
            return 3;
        }
        if (userName.length()<8 || nickName.length()<8 || p1.length()<8 || p2.length()<8) {
            return 2;
        }
        return 0;
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