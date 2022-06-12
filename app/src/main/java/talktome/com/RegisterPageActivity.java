package talktome.com;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

            EditText userNameInput = (EditText)findViewById(R.id.editTextUserNameRegister);
            String user_name = userNameInput.getText().toString();

            EditText nickNameInput = (EditText)findViewById(R.id.ditTextNickNameRegister);
            String nick_name = nickNameInput.getText().toString();

            EditText password1Input = (EditText)findViewById(R.id.editTextTextPassword);
            String password1 = password1Input.getText().toString();

            EditText password2Input = (EditText)findViewById(R.id.editTextTextPassword2);
            String password2 = password2Input.getText().toString();

            //validation
            String outputValidation = validation(user_name, nick_name, password1, password2);
            if (!outputValidation.equals("Ok")) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterPageActivity.this);
                builder1.setMessage(outputValidation);
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "return",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userNameInput.setText("");
                                nickNameInput.setText("");
                                password1Input.setText("");
                                password2Input.setText("");
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
            else {
                //if validation is ok - go to the sign in page
                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterPageActivity.this);
                builder1.setMessage("Go to sign in page");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userNameInput.setText("");
                                nickNameInput.setText("");
                                password1Input.setText("");
                                password2Input.setText("");
                                dialog.cancel();
                                // go to the sign in page
                                Intent a = new Intent(RegisterPageActivity.this, MainActivity.class);
                                startActivity(a);
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        //upload photo button
        Button btn_upload_photo = findViewById(R.id.button_upload_photo);
        btn_upload_photo.setOnClickListener(v -> {
            imageChooser();
        });
    }

    String validation(String userName, String nickName, String password1, String password2) {
        if (!password1.equals(password2)) {
            return "Passwords are not the same";
        }
        if (userName.length() < 8 || nickName.length() < 8) {
            return "user name or nick name less than 8 characters";
        }
        if (!password1.matches("([A-Za-z]+[0-9]|[0-9]+[A-Za-z])[A-Za-z0-9]*")) {
            return "password need to contain letters and numbers";
        }
        return "Ok";
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