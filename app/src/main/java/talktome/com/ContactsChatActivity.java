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

public class ContactsChatActivity extends AppCompatActivity {
    private ContactDao contactDao;
    private List<Contact> Contacts;
    private List<Conversation> conversations;
    private ArrayAdapter<Contact> adapter;
    private ArrayAdapter<Conversation> conversationArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_chat);

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = db.contactDao();

        FloatingActionButton addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(v -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        conversations = new ArrayList<>();
        Contacts = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.Contacts);
        conversationArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.conversations);
        ListView lvContacts = findViewById(R.id.contacts_list);
        lvContacts.setAdapter(adapter);

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
        Contacts.clear();
        Contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }
}