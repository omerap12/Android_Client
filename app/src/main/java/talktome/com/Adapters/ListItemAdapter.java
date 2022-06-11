package talktome.com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import talktome.com.Contact;
import talktome.com.R;

public class ListItemAdapter extends ArrayAdapter<Contact> {
    LayoutInflater inflater;
    public ListItemAdapter(Context context, List<Contact> listOfContacts) {
        super(context, R.layout.contact_list_item, listOfContacts);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_list_item, parent, false);
        }
        TextView userName = convertView.findViewById(R.id.list_item_User_name);
        TextView lastMessage = convertView.findViewById(R.id.list_item_Last_message);
        TextView lastMessageTime = convertView.findViewById(R.id.list_item_time_message_sent);
        userName.setText(contact.getUserName());
        lastMessage.setText(contact.getLastMessage());
        lastMessageTime.setText("09:56");
        return convertView;
    }
}
