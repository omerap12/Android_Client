package talktome.com;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("SELECT * FROM contact WHERE userName = :id")
    Contact get(String id);

    @Insert
    void insert(Contact ... contacts);
}
