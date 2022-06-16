package talktome.com.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import talktome.com.entities.ContactAPI;
import talktome.com.entities.InviteObj;
import talktome.com.entities.MessageAPI;
import talktome.com.entities.TransferObj;

public interface WebServiceApi {

    @GET("Contacts")
    Call <List<ContactAPI>> getContacts();

    @GET("{userId}/Contacts")
    Call <List<ContactAPI>> getContactsOfUser(@Path("userId") String userId);

    @GET("{userId}/Contacts/{otherId}/Messages")
    Call <List<MessageAPI>> getMessagesBetweenUsers(@Path("userId") String userId, @Path("otherId") String otherId);

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

    @POST("{user_id}/{token}")
    Call <Void> SendTokenToServer(@Path("user_id") String user_id,@Path("token") String token);

    @GET("token/{user_id}")
    Call <Void> GetTokenFronServer(@Path("user_id") String user_id);

    @POST("Transfer")
    Call <Void> Transfer(@Body TransferObj transferObj);

    @POST("invitations")
    Call <Void> Invite (@Body InviteObj inviteObj);


}
