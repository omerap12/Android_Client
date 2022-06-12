package talktome.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import talktome.com.Adapters.ListItemAdapter;
import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;

public class ContactsChatActivity extends AppCompatActivity {
    private ContactDao contactDao;
    private List<Contact> Contacts = new ArrayList<>();
    private List<Conversation> Conversations = new ArrayList<>();
    private ListItemAdapter adapterListItem;
    private ListView lvContacts;
    private ConversationDB conversationDB;
    private ConversationDao conversationDao;
    private String userName = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_chat);

        //get the user name that is registered
        Bundle user_registered = getIntent().getExtras();
        if (user_registered != null) {
            this.userName = user_registered.getString("userName");
        }
        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();

        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        conversationDao = conversationDB.conversationDao();

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

        //clicking on one of the contacts in the list of contacts
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = Contacts.get(position);
                Intent intent = new Intent(getApplicationContext(), ChatMessagesActivity.class);
                //need to complete that
                intent.putExtra("userName", userName);
                intent.putExtra("contactName", contact.getUserName());
                startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();
        Conversations.clear();
        Conversations.addAll(conversationDao.index());
        adapterListItem.notifyDataSetChanged();
    }
}