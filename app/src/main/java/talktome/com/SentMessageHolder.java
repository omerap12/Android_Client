package talktome.com;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class SentMessageHolder extends RecyclerView.ViewHolder{
    TextView messageText, timeText;
    public SentMessageHolder(View itemView) {
        super(itemView);
        messageText = (TextView) itemView.findViewById(R.id.text_chat_message_me);
        timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
    }
    
    public void bind(Message message) {
        messageText.setText(message.Content);
        timeText.setText(message.Created);
    }
}
