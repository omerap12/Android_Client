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

public class AddContactActivity extends AppCompatActivity {
    private AppDB db;
    private ConversationDB conversationDB;
    private ContactDao contactDao;
    private ConversationDao conversationDao;
    private MessageDB messageDB;
    private MessageDao messageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        //example

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();
        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        conversationDao = conversationDB.conversationDao();
        messageDB = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "MessageDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        messageDao = messageDB.messageDao();


        Button addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener( v -> {
            EditText etUserName = findViewById(R.id.userName_AddContact);
            String userName = etUserName.getText().toString();

            EditText etNickName = findViewById(R.id.nickName_AddContact);
            String nickName = etNickName.getText().toString();

            EditText etServerName = findViewById(R.id.server_AddContact);
            String serverName = etServerName.getText().toString();

            //need to validate the new contact before adding to list
            Contact newContact = new Contact( userName, nickName, "", serverName);
            contactDao.insert(newContact);
            conversationDao.insert(new Conversation("test",userName));
            finish();
        });
    }
}