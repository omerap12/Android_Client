package talktome.com.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import talktome.com.Conversation;
import talktome.com.Dao.ConversationDao;

@Database(entities = {Conversation.class}, version = 6)
public abstract class ConversationDB extends RoomDatabase {
    public abstract ConversationDao conversationDao();
}
