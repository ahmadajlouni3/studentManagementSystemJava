package Controllers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Students {
    public int id = 0;
    public String name;
    public String department;
    public int age;
    public int classLevel;
    public String date;
    public Note[] BadThings = new Note[3];
    public LinkedList<Note> goodThings = new LinkedList<Note>();

    public Students(
        String name,
        int age, 
        int classLevel, 
        String department
    )
    {
        this.id = this.id + 1;
        this.name = name;
        this.age = age;
        this.classLevel = classLevel;
        this.department = department;
        this.date = this.formattedDate();
    }

    public String formattedDate(){
        var dateWithoutFromat = LocalDateTime.now();
        var prepareFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        var dateWithFormat = dateWithoutFromat.format(prepareFormatter);
        return dateWithFormat;
    }

    public String addNotes(String type, String description){
        if (type == null || description == null) return "Invalid Operate in addNotes";
        switch (type) {
            case "Good":
                this.goodThings.add(new Note(type, description));
                break;
            case "Bad":
                System.out.println(this.addItemToBadThings(new Note(type, description)));
                break;
            default:
                System.out.println("Invalid Type");
                break;
        }
        return "Added note successful";
    }

    public String addItemToBadThings (Note note){
        boolean added = false;
        for(int i = 0; i < this.BadThings.length; i++){
            if (this.BadThings[i] == null){
                this.BadThings[i] = note;
                added = true;
                break;
            }
        }
        if (added){
            return "Added Successfull";
        } else {
            return "You must kickout this bad student";
        }
        
    };

    public int existLength() {
        int total = 0;
        for (int i = 0 ; i < this.BadThings.length; i++){
            if(this.BadThings[i] != null){
                total += 1;
            }else {
                break;
            }
        }
        return total;
    }


    public void getDocs(){
        System.out.println("==================================================");
        if (this.existLength() > 0){
            System.out.println("The bad things this student do is: ");
            for ( int i = 0 ; i < this.existLength(); i++){
                System.out.println(" [-] " + this.BadThings[i].description);
            }
        }

        if (this.goodThings.size() > 0){
            System.out.println("The good things this hero do is: ");
            for (Note goodThing : this.goodThings){
                System.out.println(" [+] " + goodThing.description);
            }
        }

        if (this.existLength() == 0 && this.goodThings.size() == 0){
            System.out.println("Not found any notes");
        }
        System.out.println("==================================================");
    }
}