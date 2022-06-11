package talktome.com.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import talktome.com.ChatMessagesActivity;
import talktome.com.Message;
import talktome.com.R;
import talktome.com.ReceivedMessageHolder;
import talktome.com.SentMessageHolder;

public class ListMessagesAdapter extends RecyclerView.Adapter {

    private static final int SENT_FROM_ME = 1;
    private static final int RECEIVED_FROM_OTHER = 2;

    LayoutInflater inflater;
    private Context mContext;
    private List<Message> messages;
    private String currentUser;
    public ListMessagesAdapter(Context context, List<Message> listOfMessages, String currentUserID) {
        this.messages = listOfMessages;
        this.mContext = context;
        this.currentUser = currentUserID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == SENT_FROM_ME) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_from_me, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == RECEIVED_FROM_OTHER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_from_other, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = (Message) messages.get(position);

        switch (holder.getItemViewType()) {
            case SENT_FROM_ME:
                ((SentMessageHolder) holder).bind(message);
                break;
            case RECEIVED_FROM_OTHER:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        if (messages != null) {
            return this.messages.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = (Message) messages.get(position);

        if (message.From.equals(currentUser)) {
            // If the current user is the sender of the message
            return SENT_FROM_ME;
        } else {
            // If some other user sent the message
            return RECEIVED_FROM_OTHER;
        }
    }

    List<Message> getListMessages(){
        return messages;
    }
}
