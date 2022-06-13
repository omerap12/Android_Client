package talktome.com;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import talktome.com.Adapters.ListMessagesAdapter;
import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
import talktome.com.Dao.MessageDao;

public class ChatMessagesActivity extends AppCompatActivity {
    private RecyclerView MessageRecycler;
    private ListMessagesAdapter MessageAdapter;
    private AppDB db;
    private MessageDB messageDB;
    private ConversationDB conversationDB;
    private ContactDao contactDao;
    private MessageDao messageDao;
    private ConversationDao conversationDao;
    private List<Message> listOfMessages;
    private String userName;
    private String contactName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        contactDao = db.contactDao();
        conversationDB = Room.databaseBuilder(getApplicationContext(), ConversationDB.class, "ConversationDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        conversationDao = conversationDB.conversationDao();

        Bundle fromIntent = getIntent().getExtras();
        if (fromIntent != null) {
            this.userName = fromIntent.getString("userName");
            this.contactName = fromIntent.getString("contactName");
        }
        //need to get here all the messages between user & contact.
        //put here an example to check it tits work
        listOfMessages = new ArrayList<>();
        messageDB = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "MessageDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        messageDao = messageDB.messageDao();

        //getting all the messages from the server
        //contactApi.getMessagesBetweenUsers(this.userName,this.contactName);
        listOfMessages = messageDao.getMessagesBetweenUsers(this.userName, this.contactName);
        MessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        MessageAdapter = new ListMessagesAdapter(this, listOfMessages, this.userName);
        MessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        MessageRecycler.setAdapter(MessageAdapter);


        TextView contactTalkingTo = findViewById(R.id.chat_user_name_connected);
        contactTalkingTo.setText(this.contactName);

        Button button_send = findViewById(R.id.button_send);
        button_send.setOnClickListener(v -> {
            EditText textInput = (EditText) findViewById(R.id.send_message_bar);
            String text = textInput.getText().toString();
            if (!text.equals("")) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date();
                //                messageDao.insert(new Message(this.userName, this.contactName, text, formatter.format(date)));

                //sending the message to the server
                contactApi.sendMessageFromUserIdToId(this.userName,this.contactName,text);
                listOfMessages.add(new Message(this.userName, this.contactName, text, formatter.format(date)));
                textInput.setText("");
                onResume();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        MessageAdapter.notifyDataSetChanged();
        MessageRecycler.setVisibility(View.VISIBLE);
    }
}