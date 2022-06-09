package talktome.com;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate=false)
    @NonNull
    private String id;
    private String userName;
    private String nickName;
    private String lastMessage;

    public Contact(String id, String userName, String nickName, String lastMessage) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.lastMessage = lastMessage;
    }

    public Contact(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

}
