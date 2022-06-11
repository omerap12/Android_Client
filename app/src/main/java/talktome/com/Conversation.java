package talktome.com;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity (primaryKeys = {"to","from"})
public class Conversation {
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
    public boolean isMe(String name){
        if (this.from == name || this.to == name)
            return true;
        return false;
    }
}
