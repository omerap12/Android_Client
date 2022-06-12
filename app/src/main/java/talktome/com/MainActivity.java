package talktome.com;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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

//import talktome.com.api.ContactApi;
import talktome.com.DB.AppDB;
import talktome.com.Dao.ContactDao;
import talktome.com.entities.PostContact;

public class MainActivity extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ContactApi contactApi = new ContactApi();

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();

        /**
         * Getting the contacts of specific User
         * contactApi.getContactsOfUser("TSM_Omer");
         */

        /**
         * Getting the messages between users
         *  contactApi.getMessagesBetweenUsers("TSM_Omer","Avitalos");
         */
        /**
         * Check user password -> see status code
         *  contactApi.checkPassword("TSM_Omer","123");
         */

        /**
         * Get user server name -> doesn't work
         *  contactApi.getUserServerName("TSM_Omer");
         */

        /**
         * Add contact to specific contact -> userId the one to add too and all the other fields
         * are the added user data
         * contactApi.addContactToUser("TSM_Omer","Test2","testtest","localhost:7030");
         */


        // login button handler
        Button btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(v -> {
            EditText userNameInput = (EditText)findViewById(R.id.editTextTextPersonName);
            String user_name = userNameInput.getText().toString();
            EditText passwordInput = (EditText)findViewById(R.id.editTextTextPassword);
            String user_password = passwordInput.getText().toString();
            userNameInput.setText("");
            passwordInput.setText("");
            //check validation of user name & password (need to send to server side)

            Intent i = new Intent(MainActivity.this, ContactsChatActivity.class);
            i.putExtra("userName", user_name);
            startActivity(i);
        });



        TextView textView = findViewById(R.id.textView1);
        String text = "New User? Click here";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            public void onClick(View widget) {
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