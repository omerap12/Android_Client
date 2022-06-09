package talktome.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class ContactsChatActivity extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_chat);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = db.contactDao();


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
    }
}