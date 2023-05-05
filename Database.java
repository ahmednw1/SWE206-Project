import java.io.*;
import java.util.ArrayList;

public class Database {
    private String file;

    public Database() {
        file = "Data.exe";
    }

    public ArrayList<Student>getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream objStream = new ObjectInputStream(input);
            while (objStream.available() > 0) {
                Object obj = objStream.readObject();
                if (obj instanceof Student) {
                    Student student = (Student) obj;
                    students.add(student);
                }

            }
            input.close();
            objStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return students;
    }

    public Student getStudent(int ID) throws Exception {
        try {
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream objStream = new ObjectInputStream(input);
            while (objStream.available() > 0) {
                Object obj = objStream.readObject();
                if (obj instanceof Student) {
                    Student student = (Student) obj;
                    if (student.getID() == ID) {
                        return student;
                    }
                }

            }
            input.close();
            objStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        throw new Exception("Student not found");
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teams = new ArrayList<Team>();
        try {
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream objStream = new ObjectInputStream(input);
            while (objStream.available() > 0) {
                Object obj = objStream.readObject();
                if (obj instanceof Team) {
                    Team team = (Team) obj;
                    teams.add(team);
                }

            }
            input.close();
            objStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return teams;
    }

    public ArrayList<Tournament> getTournaments() {
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
        try {
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream objStream = new ObjectInputStream(input);
            while (objStream.available() > 0) {
                Object obj = objStream.readObject();
                if (obj instanceof Tournament) {
                    Tournament tournament = (Tournament) obj;
                    tournaments.add(tournament);
                }

            }
            input.close();
            objStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return tournaments;
    }

    public void write(Object obj) {
        try {
            FileOutputStream output = new FileOutputStream(file, true);
            ObjectOutputStream objStream = new ObjectOutputStream(output);
            objStream.writeObject(obj);
            output.close();
            objStream.close();

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void getUser(int parseInt) {
    }

    public void setCurrentUser(Admin admin) {
    }

    public void setCurrentUser(Student student) {
    }

}
