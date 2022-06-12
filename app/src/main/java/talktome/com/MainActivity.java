package talktome.com;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.IsOkDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
import talktome.com.Dao.IsOkDao;
import talktome.com.Dao.MessageDao;
import talktome.com.api.ContactApi;

public class MainActivity extends AppCompatActivity {
    private AppDB db;
    private MessageDB messageDB;
    private ConversationDB conversationDB;
    private ContactDao contactDao;
    private MessageDao messageDao;
    private ConversationDao conversationDao;
    private IsOkDao isOkDao;
    private IsOkDB isOkDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //all room data bases
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();
        messageDB = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "MessageDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        messageDao = messageDB.messageDao();
        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        conversationDao = conversationDB.conversationDao();
        isOkDB = Room.databaseBuilder(getApplicationContext(), IsOkDB.class, "isOkDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        isOkDao = isOkDB.isOkDao();

        ContactApi contactApi = new ContactApi(messageDao, contactDao, conversationDao, isOkDao);

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

        /**
         * Send message from A to B
         *  contactApi.sendMessageFromUserIdToId("TSM_Omer","Avitalos","Test");
         */

        /**
         * Create a new user in the app db
         * contactApi.AddNewUserToApp("Nivnah","Niv","1111","gitchatp");
         */

        /**
         * Transfer
         * contactApi.Transfer(new TransferObj("from","to","content"));
         */

        /**
         * Invite
         * contactApi.Invite(new InviteObj("TSM_Omer","KIM","USA"));
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
            contactApi.checkPassword(user_name, user_password);
            List<isOk> list = isOkDao.index();
//            System.out.println("ht");
//            if (isOkDao.index().get(0).isIsok()==true) {
//                isOkDao.delete(isOkDao.index().get(0));
//                Intent i = new Intent(MainActivity.this, ContactsChatActivity.class);
//                i.putExtra("userName", user_name);
//                startActivity(i);
//            }
//            isOkDao.delete(isOkDao.index().get(0));
//            //notify if not true
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