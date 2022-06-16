package talktome.com.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import talktome.com.Dao.MessageDao;
import talktome.com.Message;

@Database(entities = {Message.class}, version = 12)
public abstract class MessageDB extends RoomDatabase {
    public abstract MessageDao messageDao();
}
