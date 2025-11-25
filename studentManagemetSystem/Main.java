import Controllers.Students;
import java.util.*;

public class Main {
    public static void clearCli(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void showOptions(){
        System.out.println("[1] add student");
        System.out.println("[2] show student information");
        System.out.println("[3] add note about student");
        System.out.println("[4] remove student");
        System.out.println("[5] show Docs of specific student");
        System.out.println("[6] exit");
    }

    public static Students addStudent(){
        var studentName = new Scanner(System.in); 
        var studentAge = new Scanner(System.in);
        var studentClassLevel = new Scanner(System.in);
        var studentDepartment = new Scanner(System.in);

        clearCli();
        System.out.println("=====================================================");
        System.out.print("[+] Write student name: ");
        var name = studentName.nextLine();
        System.out.print("[+] Write student age: ");
        var age = studentAge.nextInt();
        System.out.print("[+] Write class level of student: ");
        var classLevel = studentClassLevel.nextInt();
        System.out.print("[+] Write field the student need: ");
        var department = studentDepartment.nextLine();

        var student = new Students(name, age, classLevel, department);
        System.out.println("------------------------------");
        System.out.println("added successfull student name: "+ student.name);
        System.out.println("=====================================================");
        return student;
    }

    public static void addNote(LinkedList<Students> studentsList, int id) {
        boolean found = false;
        System.out.println("=================================================");
        for (Students student: studentsList){
            if(student.id == id){
                clearCli();
                var noteType = new Scanner(System.in);
                var noteDescription = new Scanner(System.in);

                System.out.print("[+] Write type of note (Bad/Good): ");
                var type = noteType.nextLine();
                System.out.println("[+] Write note description: ");
                var description = noteDescription.nextLine();

                student.addNotes(type, description);
                found = true;
                break;
            }
        }
        
        String message = found ? 
        "the note added successfull":
        "the student not found";
        System.out.println("------------------------------");
        System.out.println(message);
        System.out.println("=================================================");
    }

    public static void getStudentInformation(LinkedList<Students> studentsList, int id){
        boolean found = false;
        System.out.println("====================================================");
        for (Students student: studentsList){
            if(student.id == id){
                clearCli();
                System.out.println("The student found. here his information: ");
                System.out.println("ID student: " + student.id);
                System.out.println("Student name: " + student.name);
                System.out.println("Student age: " + student.age);
                System.out.println("Student class level: " + student.classLevel);
                System.out.println("Student field: " + student.department);
                System.out.println("Student added in: " + student.date);
                found = true;
                break;
            }
        }
        if (!found){System.out.println("The student not found.");}
        System.out.println("====================================================");
    }

    public static void getStudentDocs(LinkedList<Students> studentsList, int id){
        boolean found = false;
        for (Students student: studentsList){
            if(student.id == id){
                clearCli();
                student.getDocs();
                found = true;
                break;
            }
        }
        if (!found){System.out.println("The student not found.");}
    }

    public static void removeStudent(LinkedList<Students> studentsList, int id){
        boolean found = false;
        System.out.println("====================================================");
        for (Students student: studentsList){
            if(student.id == id){
                clearCli();
                studentsList.remove(student);
                found = true;
                break;
            }
        }
        String message = found ? 
        "Student remove successfull":
        "Student not found";
        System.out.println(message);
        System.out.println("====================================================");
    }

    public static int createIdInput() {
        var idStudent = new Scanner(System.in);
        clearCli();
        System.out.print("What is student ID: ");
        var id = idStudent.nextInt();

        return id;
    }




    public static void main(String[] args){

        // initial list for student
        var studentsList = new LinkedList<Students>();

        // welcome messages
        System.out.println("Welcome to students management system.");
        System.out.println("===============================================");

        // initial inputs
        var userChoice = new Scanner(System.in);


        boolean running = true;
        while(running){
            showOptions();
            System.out.print("Your option > ");
            var userOption = userChoice.nextInt();
            switch (userOption) {
                case 1:
                    studentsList.add(addStudent());
                    break;
                case 2:
                    getStudentInformation(studentsList, createIdInput());
                    break;
                case 3: 
                    addNote(studentsList, createIdInput());
                    break;
                case 4:
                    removeStudent(studentsList, createIdInput());
                    break;
                case 5:
                    getStudentDocs(studentsList, createIdInput());
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option.");
                    break;
            }
        }
    }
}