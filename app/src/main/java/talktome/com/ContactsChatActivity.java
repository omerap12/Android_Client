package talktome.com;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import talktome.com.Adapters.ListItemAdapter;
import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
import talktome.com.Dao.MessageDao;
import talktome.com.api.ContactApi;

public class ContactsChatActivity extends AppCompatActivity {
    private ContactDao contactDao;
    private List<Contact> Contacts = new ArrayList<>();
    private List<Conversation> Conversations = new ArrayList<>();
    private ListItemAdapter adapterListItem;
    private ListView lvContacts;
    private ConversationDB conversationDB;
    private ConversationDao conversationDao;
    private String userName = null;
    private AppDB db;
    private MessageDB messageDB;
    private MessageDao messageDao;
    private ContactApi contactApi;
    private String newToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_chat);
        //get the user name that is registered
        Bundle user_registered = getIntent().getExtras();
        if (user_registered != null) {
            this.userName = user_registered.getString("userName");
        }
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ContactsChatActivity.this, instanceIdResult -> {
            newToken = instanceIdResult.getToken();
        });
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();

        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        conversationDao = conversationDB.conversationDao();

        messageDB = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "MessageDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        messageDao = messageDB.messageDao();
        contactDao.removeAll();
        conversationDao.removeAll();

        contactApi = new ContactApi(messageDao, contactDao, conversationDao);
        contactApi.SendTokenToServer(this.userName,newToken);

        contactApi.getContactsOfUser(this.userName);

        FloatingActionButton addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(v -> {
            Intent i = new Intent(this, AddContactActivity.class);
            i.putExtra("userName", userName);
            startActivity(i);
        });

        Conversations = conversationDao.index();
        Contacts = contactDao.index();
        lvContacts = findViewById(R.id.contacts_list);
        adapterListItem = new ListItemAdapter(getApplicationContext(), Conversations);
        lvContacts.setAdapter(adapterListItem);
        lvContacts.setClickable(true);

        TextView tvUserName = findViewById(R.id.chat_user_name_connected);
        tvUserName.setText(this.userName);
//        contactApi.getContactsOfUser(this.userName);
        onResume();

        //clicking on one of the contacts in the list of contacts
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onResume();
                if (!Conversations.isEmpty()) {
                    Conversation conversation = Conversations.get(position);
                    if (conversation.from.equals(userName)) {
                        Intent intent = new Intent(getApplicationContext(), ChatMessagesActivity.class);
                        intent.putExtra("userName", userName);
                        intent.putExtra("contactName", conversation.to);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        contactApi.getContactsOfUser(this.userName);
        Conversations.clear();
        Conversations.addAll(conversationDao.index());
        Contacts.clear();
        Contacts.addAll(contactDao.index());
        adapterListItem.notifyDataSetChanged();
        lvContacts.setVisibility(View.VISIBLE);
    }
}