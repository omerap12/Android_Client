package talktome.com;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity (primaryKeys = {"To","From"})
public class Message {
    public String Id;
    @ColumnInfo
    @NonNull
    public String From;
    @ColumnInfo
    @NonNull
    public String To;
    public String Content;
    public String Created;

    public Message() {
    }

    public Message(String id, String from, String to, String content, String created) {
        Id = id;
        From = from;
        To = to;
        Content = content;
        Created = created;
    }
}
