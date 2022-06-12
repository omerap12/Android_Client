package talktome.com.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import talktome.com.MyApplication;
import talktome.com.R;
import talktome.com.entities.Contact;
import talktome.com.entities.Message;
import talktome.com.entities.PostContact;
import talktome.com.entities.ServerName;

public class ContactApi {
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;

    public ContactApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
    }
    public void getAllContacts(){
        Call<List<Contact>> call = webServiceApi.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }

    public void getContactsOfUser(String userId){
        Call<List<Contact>> call = webServiceApi.getContactsOfUser(userId);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
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




}