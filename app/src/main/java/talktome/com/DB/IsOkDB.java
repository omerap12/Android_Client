package talktome.com.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import talktome.com.Dao.IsOkDao;
import talktome.com.isOk;

@Database(entities = {isOk.class}, version = 9)
public abstract class IsOkDB  extends RoomDatabase {
    public abstract IsOkDao isOkDao();
}
