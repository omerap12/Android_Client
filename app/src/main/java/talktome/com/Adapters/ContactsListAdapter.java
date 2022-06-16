package talktome.com.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import talktome.com.ChatMessagesActivity;
import talktome.com.Contact;
import talktome.com.Conversation;
import talktome.com.R;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactsListViewHolder> {
    private LayoutInflater inflater;
    private List<Contact> contactsList;
    private List<Conversation> conversationList;
    private Context mContext;

    public ContactsListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    class ContactsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView lastMessage, contactName, time;
        public ContactsListViewHolder(View viewItem) {
            super(viewItem);
            lastMessage = (TextView) itemView.findViewById(R.id.list_item_Last_message);
            time = (TextView) itemView.findViewById(R.id.list_item_time_message_sent);
            contactName = (TextView) itemView.findViewById(R.id.list_item_User_name);
            itemView.setOnClickListener(this);
        }
        public void bind(Contact contact) {
            contactName.setText(contact.getUserName());
            lastMessage.setText(contact.getLastMessage());
            //TODO - need to fix all time issues
            time.setText("09:45");
        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public ContactsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //maybe need to change it
        View viewItem = inflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ContactsListViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!conversationList.isEmpty()) {
                    Conversation conversation = conversationList.get(position);
                    String userName = conversation.from;
                    Intent intent = new Intent(mContext.getApplicationContext(), ChatMessagesActivity.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("contactName", conversation.to);
                    mContext.startActivity(intent);
                }
            }
        });
        if (contactsList != null) {
            Contact contact = contactsList.get(position);
            ((ContactsListViewHolder) holder).bind(contact);
        }
    }


    @Override
    public int getItemCount() {
        if (contactsList != null) {
            return this.contactsList.size();
        }
        else {
            return 0;
        }
    }

    public void setContactsList(List<Contact> list) {
        contactsList = list;
        notifyDataSetChanged();
    }
    public void setConversationsList(List<Conversation> list) {
        conversationList = list;
        notifyDataSetChanged();
    }

}
