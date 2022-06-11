package talktome.com.entities;

public class Contact {
    public String id;
    public String name;
    public String server;
    public String last;
    public String lastDate;

    public Contact(String id, String name, String server, String last, String lastDate)
    {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastDate = lastDate;
    }
}
