package talktome.com;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message WHERE `To` == :myId AND `From` == :OtherId OR `From` == :myId AND `To` == :OtherId")
    List<Message> getMessagesBetweenUsers(String myId, String OtherId);

    @Insert
    void insert(Message ... messages);

}
