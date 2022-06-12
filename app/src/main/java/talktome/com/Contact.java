package talktome.com;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @NonNull
    @PrimaryKey(autoGenerate=false)
    private String userName;
    private String nickName;
    private String lastMessage;
    private String server;


    public Contact( String userName, String nickName, String lastMessage,String server) {
        this.userName = userName;
        this.nickName = nickName;
        if (lastMessage.isEmpty()) {
            this.lastMessage="";
        } else {
            this.lastMessage = lastMessage;
        }
        this.server = server;
    }

    public Contact(){
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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

    @Override
    public String toString() {
        return "Contact{" +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", server='" + server + '\'' +
                '}';
    }
}
