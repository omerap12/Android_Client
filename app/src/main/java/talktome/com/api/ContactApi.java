package talktome.com.api;
import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import talktome.com.Contact;
import talktome.com.Conversation;
import talktome.com.DB.AppDB;
import talktome.com.DB.ConversationDB;
import talktome.com.DB.MessageDB;
import talktome.com.Dao.ContactDao;
import talktome.com.Dao.ConversationDao;
import talktome.com.Dao.MessageDao;
import talktome.com.MyApplication;
import talktome.com.R;
import talktome.com.entities.ContactAPI;
import talktome.com.entities.InviteObj;
import talktome.com.entities.Message;
import talktome.com.entities.TransferObj;

public class ContactApi {
    private static Retrofit retrofit;
    private static WebServiceApi webServiceApi;
    private ContactDao contactDao;
    private MessageDao messageDao;
    private ConversationDao conversationDao;

    public ContactApi(MessageDao messageDao1, ContactDao contactDao1, ConversationDao conversationDao1) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
        contactDao = contactDao1;
        messageDao = messageDao1;
        conversationDao = conversationDao1;
    }

    public void getAllContacts(){
        Call<List<ContactAPI>> call = webServiceApi.getContacts();
        call.enqueue(new Callback<List<ContactAPI>>() {
            @Override
            public void onResponse(Call<List<ContactAPI>> call, Response<List<ContactAPI>> response) {
                List<ContactAPI> contactAPIS = response.body();
            }

            @Override
            public void onFailure(Call<List<ContactAPI>> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }

    public void getContactsOfUser(String userId){
        Call<List<ContactAPI>> call = webServiceApi.getContactsOfUser(userId);
        call.enqueue(new Callback<List<ContactAPI>>() {
            @Override
            public void onResponse(Call<List<ContactAPI>> call, Response<List<ContactAPI>> response) {
                List<ContactAPI> contactAPIS = response.body();
                for (int i=0; i < contactAPIS.size(); i++) {
                    conversationDao.insert(new Conversation(userId, contactAPIS.get(i).id, contactAPIS.get(i).last, contactAPIS.get(i).lastDate));
                    contactDao.insert(new Contact(contactAPIS.get(i).id, contactAPIS.get(i).last));
                }
            }

            @Override
            public void onFailure(Call<List<ContactAPI>> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void getMessagesBetweenUsers(String userId, String otherId){
        Call<List<Message>> call = webServiceApi.getMessagesBetweenUsers(userId,otherId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void checkPassword(String userId, String password){
        Call<Void> call = webServiceApi.checkPassword(userId,password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Void check = response.body();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void getUserServerName(String userId){
        Call<String> call = webServiceApi.getUserServerName(userId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call <String> call, Response<String> response) {
                String serveName = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void addContactToUser(String userId, String id, String name, String server){
        Call<Void> call = webServiceApi.addContactToUser(userId,id,name,server);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call <Void> call, Response<Void> response) {
                Void check = response.body();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void sendMessageFromUserIdToId(String userId, String id,String content){
        Call<Void> call = webServiceApi.sendMessageFromUserIdToId(userId,id,content);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call <Void> call, Response<Void> response) {
                Void check = response.body();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void AddNewUserToApp(String user_id,String nick_name,String password,String server){
        Call<Void> call = webServiceApi.AddNewUserToApp(user_id,nick_name,password,server);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call <Void> call, Response<Void> response) {
                Void check = response.body();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void Transfer(TransferObj transferObj){
        Call<Void> call = webServiceApi.Transfer(transferObj);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call <Void> call, Response<Void> response) {
                Void check = response.body();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
    public void Invite(InviteObj inviteObj){
        Call<Void> call = webServiceApi.Invite(inviteObj);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call <Void> call, Response<Void> response) {
                Void check = response.body();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }




}