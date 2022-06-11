package talktome.com;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity (primaryKeys = {"to","from"})
public class Conversation {
    public List<Message> Messages;
    @ColumnInfo
    @NonNull
    public String from;
    @ColumnInfo
    @NonNull
    public String to;
    public String last;
    public String id;
    public String lastdate;

    public Conversation(String from, String to) {
        this.from = from;
        this.to = to;
        Messages = new ArrayList<>(Messages);
    }

    public List<Message> getMessages() {
        return Messages;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getLast() {
        return last;
    }

    public String getId() {
        return id;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setMessages(List<Message> messages) {
        Messages = messages;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }


    /**
     * functionality functions
     */

    public int GetMessageLength()
    {
        return Messages.size();
    }

    public void addMessage(Message message){
        this.last = message.Content;
        message.Id = String.valueOf(( GetMessageLength()+1 ));
        this.Messages.add(message);
        this.lastdate = message.Created;
    }

    public boolean isMe(String name){
        if (this.from == name || this.to == name)
            return true;
        return false;
    }
}
