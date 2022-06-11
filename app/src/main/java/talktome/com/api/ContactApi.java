package talktome.com.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import talktome.com.MyApplication;
import talktome.com.R;
import talktome.com.entities.Contact;
import talktome.com.entities.Message;
import talktome.com.entities.ServerName;

public class ContactApi {
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;

    public ContactApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
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
        Call<List<ServerName>> call = webServiceApi.getUserServerName(userId);
        call.enqueue(new Callback<List<ServerName>>() {
            @Override
            public void onResponse(Call<List<ServerName>> call, Response<List<ServerName>> response) {
                List<ServerName> serveName = response.body();
            }

            @Override
            public void onFailure(Call<List<ServerName>> call, Throwable t) {
                System.out.printf("here");
            }
        });
    }
}