package talktome.com;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String nickName;
    private String password;
    private String id;
    private ImageView imageView;
    private List<Contact> listOfContacts;
    private String server;

    public User(String userName, String nickName, String password, String id, ImageView imageView, String server) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.id = id;
        this.imageView = imageView;
        this.listOfContacts = new ArrayList<Contact>();
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public List<Contact> getListOfContacts() {
        return listOfContacts;
    }

    public void setListOfContacts(List<Contact> listOfContacts) {
        this.listOfContacts = listOfContacts;
    }
}
