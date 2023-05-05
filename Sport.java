import java.io.Serializable;

public class Sport implements Serializable{
    private String description;
    private String name;

    public Sport(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Sport(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    
}
