package talktome.com;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
//import talktome.com.api.ContactApi;

public class AddContactActivity extends AppCompatActivity {
    private AppDB db;
    private ConversationDB conversationDB;
    private ContactDao contactDao;
    private ConversationDao conversationDao;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();
        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        conversationDao = conversationDB.conversationDao();

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

            //TODO - need to validate the new contact before adding to list
            Contact newContact = new Contact( userNameOther, "");
            if (contactDao.get(userNameOther)==null) {
                contactDao.insert(newContact);
                conversationDao.insert(new Conversation(this.userName,userNameOther, "", ""));
            } else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddContactActivity.this);
                builder1.setMessage("User already exists");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "return",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                etUserName.setText("");
                                etNickName.setText("");
                                etServerName.setText("");
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}