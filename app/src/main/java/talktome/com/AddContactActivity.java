package talktome.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
import talktome.com.Dao.MessageDao;
//import talktome.com.api.ContactApi;

public class AddContactActivity extends AppCompatActivity {
    private AppDB db;
    private ConversationDB conversationDB;
    private ContactDao contactDao;
    private ConversationDao conversationDao;
    private MessageDB messageDB;
    private MessageDao messageDao;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();
        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        conversationDao = conversationDB.conversationDao();
        messageDB = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "MessageDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        messageDao = messageDB.messageDao();

        Bundle user_registered = getIntent().getExtras();
        if (user_registered != null) {
            this.userName = user_registered.getString("userName");
        }


        Button addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener( v -> {
            EditText etUserName = findViewById(R.id.userName_AddContact);
            String userNameOther = etUserName.getText().toString();

            EditText etNickName = findViewById(R.id.nickName_AddContact);
            String nickNameOther = etNickName.getText().toString();

            EditText etServerName = findViewById(R.id.server_AddContact);
            String serverNameOther = etServerName.getText().toString();

            //need to validate the new contact before adding to list
            Contact newContact = new Contact( userNameOther, nickNameOther, "", serverNameOther);
            contactDao.insert(newContact);
            conversationDao.insert(new Conversation(this.userName,userNameOther));
            finish();
        });
    }
}