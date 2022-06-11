package talktome.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import talktome.com.Adapters.ListItemAdapter;

public class ContactsChatActivity extends AppCompatActivity {
    private ContactDao contactDao;
    private List<Contact> Contacts = new ArrayList<>();
    private List<Conversation> Conversations = new ArrayList<>();
    private ListItemAdapter adapterListItem;
    private ListView lvContacts;
    private ConversationDB conversationDB;
    private ConversationDao conversationDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_chat);

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = db.contactDao();

        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().build();
        conversationDao = conversationDB.conversationDao();

        FloatingActionButton addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(v -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        Conversations = conversationDao.index();
        Contacts = contactDao.index();
        lvContacts = findViewById(R.id.contacts_list);
        adapterListItem = new ListItemAdapter(getApplicationContext(), Conversations);
        lvContacts.setAdapter(adapterListItem);
        lvContacts.setClickable(true);

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