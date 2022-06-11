package talktome.com;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class}, version = 2)
public abstract class MessageDB extends RoomDatabase {
    public abstract MessageDao messageDao();
}
