package talktome.com;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class isOk {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userId;
    private boolean isok;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
    }

    public isOk(String userId, boolean isok) {
        this.isok = isok;
        this.userId = userId;
    }

    public isOk() {
    }
}
