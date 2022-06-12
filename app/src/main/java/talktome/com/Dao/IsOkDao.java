package talktome.com.Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import talktome.com.isOk;
@Dao
public interface IsOkDao {
    @Query("SELECT * FROM isOk")
    List<isOk> index();

    @Insert
    void insert(isOk... isOks);

    @Delete
    void delete(isOk... isOks);
}
