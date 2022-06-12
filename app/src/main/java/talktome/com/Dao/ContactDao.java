package talktome.com.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import talktome.com.Contact;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("SELECT * FROM contact WHERE userName = :id")
    Contact get(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact ... contacts);
}
