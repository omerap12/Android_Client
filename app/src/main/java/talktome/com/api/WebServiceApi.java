package talktome.com.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import talktome.com.entities.Contact;
import talktome.com.entities.Message;
import talktome.com.entities.ServerName;

public interface WebServiceApi {

    @GET("Contacts")
    Call <List<Contact>> getContacts();

    @GET("{userId}/Contacts")
    Call <List<Contact>> getContactsOfUser(@Path("userId") String userId);

    @GET("{userId}/Contacts/{otherId}/Messages")
    Call <List<Message>> getMessagesBetweenUsers(@Path("userId") String userId, @Path("otherId") String otherId);

    @GET("{userId}/Contacts/{password}")
    Call <Void> checkPassword(@Path("userId") String userId, @Path("password") String password);

    @GET("Contacts/servername/{userId}")
    Call <String> getUserServerName(@Path("userId") String userId);





}
