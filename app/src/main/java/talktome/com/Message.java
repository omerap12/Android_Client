package talktome.com;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    public Boolean Sent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message(String Id, String From, String To, Boolean Sent) {
        Id = Id;
        Sent = Sent;
        From = From;
        To = To;
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Created = myDateObj.format(myFormatObj);
    }
}
