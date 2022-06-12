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
    private String lastMessage;


    public Contact( String userName, String lastMessage) {
        this.userName = userName;
        if (lastMessage.isEmpty()) {
            this.lastMessage="";
        } else {
            this.lastMessage = lastMessage;
        }
    }

    public Contact(){
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

}
