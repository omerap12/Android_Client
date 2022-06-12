package talktome.com;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        listOfMessages.add(new Message("2", "Avital", "Omer", "Shalom", " 06:15"));

        listOfMessages.add(new Message("3", "Avital", "Omer", "hemi is out big enemy", " 20:23"));
        listOfMessages.add(new Message("4", "Omer", "Avital", "i hate hemi", "11:03"));

        MessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        MessageAdapter = new ListMessagesAdapter(this, listOfMessages, userId);
        MessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        MessageRecycler.setAdapter(MessageAdapter);

        Button button_send = findViewById(R.id.button_send);
        button_send.setOnClickListener(v -> {
            EditText textInput = (EditText) findViewById(R.id.send_message_bar);
            String text = textInput.getText().toString();
            if (!text.equals("")) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date();
                listOfMessages.add(new Message(getMessageNextId(), "Omer", "Avital", text, formatter.format(date)));
            }
        });
    }

    String getMessageNextId() {
        Message lastMessage = listOfMessages.get(listOfMessages.size()-1);
        int newId = Integer.parseInt(lastMessage.Id) + 1;
        return Integer.toString(newId);
    }
}