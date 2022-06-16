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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import talktome.com.Adapters.ListMessagesAdapter;
import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
import talktome.com.Dao.MessageDao;
import talktome.com.api.ContactApi;
import talktome.com.api.WebServiceApi;
import talktome.com.entities.MessageAPI;

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

        ContactApi contactApi = new ContactApi(messageDao,contactDao,conversationDao);
        getMessagesBetweenUsers(this.userName,this.contactName);
        listOfMessages = messageDao.getMessagesBetweenUsers(this.userName, this.contactName);
        /**
         * DO-NOT change the removeAll() position.
         */
        messageDao.removeAll();
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
                textInput.setText("");
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date();
                contactApi.sendMessageFromUserIdToId(this.userName,this.contactName,text);
                Conversation conversation = conversationDao.getSpecificConversation(this.userName, this.contactName);
                conversation.last = text;
                listOfMessages.add(new Message(this.userName, this.contactName, text, formatter.format(date)));
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

    public void getMessagesBetweenUsers(String userName, String contactName) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WebServiceApi webServiceApi = retrofit.create(WebServiceApi.class);

        Call<List<MessageAPI>> call = webServiceApi.getMessagesBetweenUsers(userName,contactName);
        call.enqueue(new Callback<List<MessageAPI>>() {
            @Override
            public void onResponse(Call<List<MessageAPI>> call, Response<List<MessageAPI>> response) {
                List<MessageAPI> messageAPIS = response.body();
                for (int i = 0; i < messageAPIS.size(); i++) {
                    Message message;
                    if (messageAPIS.get(i).sent) {
                        message = new Message(userName, contactName, messageAPIS.get(i).content, messageAPIS.get(i).created);
                    } else {
                        message = new Message(contactName, userName, messageAPIS.get(i).content, messageAPIS.get(i).created);
                    }
                    messageDao.insert(message);
                    listOfMessages.clear();
                    listOfMessages.addAll(messageDao.index());
                    MessageAdapter.setListMessages(listOfMessages);
                    MessageRecycler.setAdapter(MessageAdapter);
                    MessageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MessageAPI>> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
}