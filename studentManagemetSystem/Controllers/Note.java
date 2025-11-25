package Controllers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note{
    public int id = 0;
    public String type;
    public String description;
    public String date;

    public Note(String type, String description) {
        this.id = this.id + 1;
        this.type = type;
        this.description = description;
        this.date = this.formattedDate();
    }

    public String formattedDate () {
        var dateWithoutFromat = LocalDateTime.now();
        var prepareFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var dateWithFormat = dateWithoutFromat.format(prepareFormatter);
        return dateWithFormat;
    }
}