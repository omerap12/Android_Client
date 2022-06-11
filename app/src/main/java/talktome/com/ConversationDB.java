package talktome.com;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Conversation.class}, version = 2)
public abstract class ConversationDB extends RoomDatabase {
    public abstract ConversationDao conversationDao();
}
