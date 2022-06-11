package talktome.com.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import talktome.com.entities.Contact;

public interface WebServiceApi {

    @GET("Contacts")
    Call <List<Contact>> getContacts();

    @GET("{userId}/Contacts")
    Call <List<Contact>> getContactsOfUser(@Path("userId") String userId);






}
