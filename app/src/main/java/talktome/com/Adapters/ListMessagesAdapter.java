package talktome.com.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import talktome.com.Message;
import talktome.com.R;
import talktome.com.ViewHolders.ReceivedMessageHolder;
import talktome.com.ViewHolders.SentMessageHolder;

public class ListMessagesAdapter extends RecyclerView.Adapter {

    private static final int SENT_FROM_ME = 1;
    private static final int RECEIVED_FROM_OTHER = 2;
    LayoutInflater inflater;
    private Context mContext;
    private List<Message> messages;
    private String currentUser;
    private SentMessageHolder sentMessageHolder;
    private ReceivedMessageHolder receivedMessageHolder;

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
            sentMessageHolder = new SentMessageHolder(view);
            return sentMessageHolder;
        } else if (viewType == RECEIVED_FROM_OTHER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_from_other, parent, false);
            receivedMessageHolder = new ReceivedMessageHolder(view);
            return receivedMessageHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = (Message) messages.get(position);
        if (message.From.equals(this.currentUser)) {
            sentMessageHolder.bind(message);
        }
        else {
            receivedMessageHolder.bind(message);
        }
//        switch (holder.getItemViewType()) {
//            case SENT_FROM_ME:
//                ((SentMessageHolder) holder).bind(message);
//                break;
//            case RECEIVED_FROM_OTHER:
//                ((ReceivedMessageHolder) holder).bind(message);
//        }
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
        Message message = messages.get(position);
        if (message.From.equals(this.currentUser)) {
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

    public void setListMessages(List<Message> list) {
        this.messages = list;
    }
}
