import java.util.ArrayList;
import java.util.List;

// Abstract class
abstract class Role {
    String name;

    public Role(String name) {
        this.name = name;
    }

    
}

// Subclasses
class Godfather extends Role {
    public Godfather() {
        super("Godfather");
    }

    
}

class Sheriff extends Role {
    public Sheriff() {
        super("Sheriff");
    }

    
}

public class Main {
    public static void main(String[] args) {
        // Create a list of roles
        List<Role> roles = new ArrayList<>();

        // Add different role objects
        roles.add(new Godfather());
        roles.add(new Sheriff());

        // Loop through and use polymorphism
        for (Role role : roles) {
            System.out.println("Role: " + role.name);
            
        }
    }
}
