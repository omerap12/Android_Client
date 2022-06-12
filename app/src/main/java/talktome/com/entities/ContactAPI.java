package talktome.com.entities;

public class ContactAPI {
    public String id;
    public String name;
    public String server;
    public String last;
    public String lastDate;

    public ContactAPI(String id, String name, String server, String last, String lastDate)
    {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastDate = lastDate;
    }
}
