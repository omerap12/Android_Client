package talktome.com;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {
    public  String Id;
    public String Content;
    public String Created;
    public Boolean Sent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message(String id, Boolean sent) {
        Id = id;
        Sent = sent;
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Created = myDateObj.format(myFormatObj);
    }
}
