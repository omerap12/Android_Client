package talktome.com;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import talktome.com.Adapters.ListMessagesAdapter;

public class ChatMessagesActivity extends AppCompatActivity {
    private RecyclerView MessageRecycler;
    private ListMessagesAdapter MessageAdapter;
    private List<Message> listOfMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);

        //need to get here all the messages between user & contact.
        //put here an example to check it tits work
        String userId = "Omer";
        listOfMessages = new ArrayList<>();
        listOfMessages.add(new Message("1", "Omer", "Avital", "hey", "10:00"));
        listOfMessages.add(new Message("2", "Avital", "Omer", "Shalom"," 06:15"));

        MessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        MessageAdapter = new ListMessagesAdapter(this, listOfMessages, userId);
        MessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        MessageRecycler.setAdapter(MessageAdapter);

    }
}