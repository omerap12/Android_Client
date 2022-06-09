package talktome.com;

import android.widget.ImageView;

public class Contact {
    private String id;
    private String userName;
    private String nickName;
    private User userTalkingTo;
    private String lastMessage;
    private ImageView imageView;

    public Contact(String id, String userName, String nickName, User userTalkingTo, String lastMessage, ImageView imageView) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.userTalkingTo = userTalkingTo;
        this.lastMessage = lastMessage;
        this.imageView = imageView;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getUserTalkingTo() {
        return userTalkingTo;
    }

    public void setUserTalkingTo(User userTalkingTo) {
        this.userTalkingTo = userTalkingTo;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
