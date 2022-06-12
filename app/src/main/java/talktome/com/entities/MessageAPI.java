package talktome.com.entities;

public class MessageAPI {
    public String id;
    public String content;
    public String created;
    public boolean sent;
    public MessageAPI(String id, String content, String created, boolean sent)
    {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
    }
}
