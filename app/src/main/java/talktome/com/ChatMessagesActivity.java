package talktome.com;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import talktome.com.Adapters.ListMessagesAdapter;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.MessageDao;

public class ChatMessagesActivity extends AppCompatActivity {
    private RecyclerView MessageRecycler;
    private ListMessagesAdapter MessageAdapter;
    private MessageDao messageDao;
    private MessageDB messageDB;
    private List<Message> listOfMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);

        //need to get here all the messages between user & contact.
        //put here an example to check it tits work
        String userId = "Omer";
        listOfMessages = new ArrayList<>();
        messageDB = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "MessageDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        messageDao = messageDB.messageDao();

        MessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        MessageAdapter = new ListMessagesAdapter(this, messageDao.index(), userId);
        MessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        MessageRecycler.setAdapter(MessageAdapter);

        Button button_send = findViewById(R.id.button_send);
        button_send.setOnClickListener(v -> {
            EditText textInput = (EditText) findViewById(R.id.send_message_bar);
            String text = textInput.getText().toString();
            if (!text.equals("")) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date();
                messageDao.insert(new Message("Omer", "Avital", text, formatter.format(date)));
                textInput.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}