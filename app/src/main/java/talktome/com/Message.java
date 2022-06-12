package talktome.com;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    public String From;
    public String To;
    public String Content;
    public String Created;

    public Message() {
    }

    public Message(String from, String to, String content, String created) {
        From = from;
        To = to;
        Content = content;
        Created = created;
    }
}
