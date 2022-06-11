package talktome.com.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import talktome.com.entities.Contact;
import talktome.com.entities.Message;

public interface WebServiceApi {

    @GET("Contacts")
    Call <List<Contact>> getContacts();

    @GET("{userId}/Contacts")
    Call <List<Contact>> getContactsOfUser(@Path("userId") String userId);

    @GET("{userId}/Contacts/{otherId}/Messages")
    Call <List<Message>> getMessagesBetweenUsers(@Path("userId") String userId, @Path("otherId") String otherId);







}
