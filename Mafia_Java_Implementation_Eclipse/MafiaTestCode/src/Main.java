import java.util.*;
import Model.*;

//TODO:
//optimizovan kod od strane chatGPT ima sledece probleme:
//mafija moze da izabere mafiju (problem i u mom kodu)
//u logovima nakon sto je svako izabrao, ne prikazuje se uloga od targeta
//nema isprekidanih linija kod onoga ko bira - nepregledno

public class Main {
    final static int numberOfPlayers = 8;
    public static ArrayList<Player> transporterSecondTargets = new ArrayList<>();
    public static ArrayList<NightRecap> gameLog = new ArrayList<>();
    public static int night = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Player> playersList = initializePlayers();
        Collections.shuffle(playersList);
        startGame(playersList);
    }

    private static ArrayList<Player> initializePlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player("Player" + i, i, null));
        }
        return players;
    }

    private static void printPlayers(List<Player> players) {
        for (Player player : players) {
            System.out.printf("\nPlayer: %s (Number: %d, Role: %s, Alive: %b)%n",
                    player.name, player.number, player.role.name, player.isAlive);
            System.out.println("Target: " + (player.target != null ? player.target.name : "None"));
        }
        System.out.println("--------------------------------");
    }

    private static void startGame(ArrayList<Player> players) {
        ArrayList<Model.Role> roles = declareAndChooseRoles();
        assignRoles(players, roles);

        while (!checkWinCondition()) {
            chooseTargets(players);
            performActions(players);
        }
    }

    private static boolean checkWinCondition() {
        // TODO: Implement win condition logic
        return false;
    }

    private static void assignRoles(ArrayList<Player> players, ArrayList<Model.Role> roles) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.get(i).role = roles.get(i);
        }
    }

    private static void performActions(ArrayList<Player> players) {
        Map<String, Player> roleMap = new HashMap<>();
        for (Player player : players) {
            roleMap.put(player.role.name, player);
        }

        executeRoleAction("veteran", roleMap, players);
        executeRoleAction("transporter", roleMap, players);
        executeRoleAction("consort", roleMap, players);
        executeRoleAction("doctor", roleMap, players);
        executeRoleAction("bodyguard", roleMap, players);
        executeRoleAction("framer", roleMap, players);
        executeRoleAction("sheriff", roleMap, players);
        executeRoleAction("vigilante", roleMap, players);

        printPlayers(players);
    }

    private static void executeRoleAction(String roleName, Map<String, Player> roleMap, ArrayList<Player> players) {
        if (roleMap.containsKey(roleName)) {
            roleMap.get(roleName).role.action(roleMap.get(roleName).target, players);
        }
    }

    private static void chooseTargets(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.role.alignment.equals("unaligned") || player.role.name.equals("mayor")) {
                System.out.println(player.name + " " + player.role.name + " does not choose.");
                continue;
            }
            
            System.out.println(player.name + " " + player.role.name + " is choosing");
            displayPlayerChoices(players, player);

            System.out.print("Choose a number: ");
            int target = scanner.nextInt() - 1;
            player.target = players.get(target);
            System.out.println("--------------------------------");

            if (player.role.name.equals("transporter")) {
                displayPlayerChoices(players, player);
                System.out.print("Choose another number: ");
                int secondTarget = scanner.nextInt() - 1;
                transporterSecondTargets.add(players.get(secondTarget));
            }
        }
    }

    private static void displayPlayerChoices(ArrayList<Player> players, Player chooser) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != chooser)
                System.out.println((i + 1) + " " + players.get(i).name + " " + players.get(i).role.name);
        }
    }

    private static ArrayList<Model.Role> declareAndChooseRoles() {
        ArrayList<Model.Role> roles = new ArrayList<>();
        roles.add(new Godfather());
        roles.add(new Mafioso());
        roles.add(randomBoolean() ? new Consort() : new Framer());
        roles.add(randomBoolean() ? new Bodyguard() : new Doctor());
        roles.add(randomBoolean() ? new Vigilante() : new Veteran());
        roles.add(randomBoolean() ? new Sheriff() : new Tracker());
        roles.add(randomBoolean() ? new Mayor() : new Transporter());
        roles.add(randomBoolean() ? new Jester() : new Survivor());
        return roles;
    }

    private static boolean randomBoolean() {
        return new Random().nextBoolean();
    }
}

