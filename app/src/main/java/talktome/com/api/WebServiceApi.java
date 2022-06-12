package talktome.com.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import talktome.com.entities.Contact;
import talktome.com.entities.Message;
import talktome.com.entities.PostContact;
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

    @POST("{userId}/Contacts")
    Call <Void> addContactToUser(@Path("userId") String userId,@Query("id") String id, @Query("name") String name, @Query("server") String server);

    @POST("{userId}/Contacts/{id}/Messages")
    Call <Void> sendMessageFromUserIdToId(@Path("userId") String userId, @Path("id") String id, @Query("content") String content);

    @POST("Contacts/{user_id}/{nick_name}/{password}/{server}")
    Call <Void> AddNewUserToApp(@Path("user_id") String user_id, @Path("nick_name") String nick_name, @Path("password") String password, @Path("server") String server);




}
