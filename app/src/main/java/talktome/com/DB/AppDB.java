package talktome.com.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import talktome.com.Contact;
import talktome.com.Dao.ContactDao;

@Database(entities = {Contact.class}, version = 2)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
}
