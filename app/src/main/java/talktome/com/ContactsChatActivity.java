package talktome.com;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import talktome.com.Adapters.ContactsListAdapter;
import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
import talktome.com.Dao.MessageDao;
import talktome.com.api.ContactApi;
import talktome.com.api.WebServiceApi;
import talktome.com.entities.ContactAPI;

public class ContactsChatActivity extends AppCompatActivity {
    private ContactDao contactDao;
    private List<Contact> Contacts = new ArrayList<>();
    private List<Conversation> Conversations = new ArrayList<>();
    private RecyclerView lvContacts;
    private ConversationDB conversationDB;
    private ConversationDao conversationDao;
    public String userName = null;
    private AppDB db;
    private MessageDB messageDB;
    private MessageDao messageDao;
    private ContactApi contactApi;
    private ContactsListAdapter contactsListAdapter;
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

        FloatingActionButton addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(v -> {
            Intent i = new Intent(this, AddContactActivity.class);
            i.putExtra("userName", userName);
            startActivity(i);
        });

        TextView tvUserName = findViewById(R.id.chat_user_name_connected);
        tvUserName.setText(this.userName);


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
        contactsListAdapter = new ContactsListAdapter(this);
        lvContacts = findViewById(R.id.contacts_list);
        lvContacts.setLayoutManager(new LinearLayoutManager(this));
        lvContacts.setHasFixedSize(true);

        contactApi.SendTokenToServer(this.userName,newToken);

        //contactApi.getContactsOfUser(this.userName);

        Conversations = conversationDao.index();
        Contacts = contactDao.index();
        lvContacts = findViewById(R.id.contacts_list);
        lvContacts.setAdapter(contactsListAdapter);
        lvContacts.setLayoutManager(new LinearLayoutManager(this));
        lvContacts.setClickable(true);
        lvContacts.setHasFixedSize(true);
        getContactsOfUser(this.userName);

        //contactApi.getContactsOfUser(this.userName);

//        contactsListAdapter.setContactsList(Contacts);
//        contactsListAdapter.setConversationsList(Conversations);

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
        getContactsOfUser(this.userName);
        Contacts.clear();
        Contacts.addAll(contactDao.index());
        Conversations.clear();
        Conversations.addAll(conversationDao.index());
        contactsListAdapter.notifyDataSetChanged();
        lvContacts.setVisibility(View.VISIBLE);
    }

    public void getContactsOfUser(String user_name) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WebServiceApi webServiceApi = retrofit.create(WebServiceApi.class);

        Call<List<ContactAPI>> call = webServiceApi.getContactsOfUser(this.userName);
        call.enqueue(new Callback<List<ContactAPI>>() {
            @Override
            public void onResponse(Call<List<ContactAPI>> call, Response<List<ContactAPI>> response) {
                List<ContactAPI> contactAPIS = response.body();
                List<Contact> contacts = new ArrayList<>();
                for (int i=0; i < contactAPIS.size(); i++) {
                    Contact contact = new Contact(contactAPIS.get(i).id, contactAPIS.get(i).last);
                    contacts.add(contact);
                    Conversation conversation = new Conversation(user_name, contactAPIS.get(i).id, contactAPIS.get(i).last, contactAPIS.get(i).lastDate);
                    Conversation list = conversationDao.getSpecificConversation(user_name, contactAPIS.get(i).id);
                    if (list == null) {
                        conversationDao.insert(conversation);
                    }
                    contactDao.insert(contact);
                }
                Conversations.clear();
                Conversations.addAll(conversationDao.index());
                Contacts.clear();
                Contacts.addAll(contactDao.index());
                contactsListAdapter.setContactsList(Contacts);
                contactsListAdapter.setConversationsList(Conversations);
                lvContacts.setAdapter(contactsListAdapter);
                contactsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ContactAPI>> call, Throwable t) {
            }
    });
}
}