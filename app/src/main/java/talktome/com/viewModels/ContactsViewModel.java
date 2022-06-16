package talktome.com.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import talktome.com.Contact;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;

public class ContactsViewModel extends ViewModel {
    private LiveData<List<Contact>> listOfContacts;
    private ContactDao contactDao;
    private ConversationDao conversationDao;

    public ContactsViewModel(String userName) {
    }
}
