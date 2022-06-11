package talktome.com.Dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import talktome.com.Conversation;

@Dao
public interface ConversationDao {

    @Query("SELECT * FROM conversation")
    List<Conversation> index();

    @Query("SELECT * FROM conversation WHERE `to` == :myId AND `from` == :OtherId OR `from` == :myId AND `to` == :OtherId")
    Conversation getSpecificConversation(String myId, String OtherId);

    @Query("SELECT * FROM conversation WHERE `from` == :myId")
    List<Conversation> getAllConversation(String myId);

    @Insert
    void insert(Conversation ... conversations);

}
