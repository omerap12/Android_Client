package talktome.com.ViewHolders;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import talktome.com.Message;
import talktome.com.R;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder{
    TextView messageText, timeText, nameText;

    public ReceivedMessageHolder(View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.other_user_id_for_message);
        messageText = itemView.findViewById(R.id.text_message_other);
        timeText = itemView.findViewById(R.id.message_time_other);
    }

    public void bind(Message message) {
        messageText.setText(message.Content);
        timeText.setText(message.Created);
        nameText.setText(message.From);
    }
}
