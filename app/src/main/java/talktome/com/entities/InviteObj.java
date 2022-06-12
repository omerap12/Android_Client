package talktome.com.entities;

public class InviteObj {
    private String to;
    private String from;
    private String server;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public InviteObj(String to, String from, String server) {
        this.to = to;
        this.from = from;
        this.server = server;
    }
}
