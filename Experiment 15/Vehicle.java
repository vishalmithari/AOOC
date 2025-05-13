import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle {
    private String number;
    private Date entryTime;

    public Vehicle(String number) {
        this.number = number;
        this.entryTime = new Date();
    }

    public String getNumber() {
        return number;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public String getFormattedEntryTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entryTime);
    }
}
