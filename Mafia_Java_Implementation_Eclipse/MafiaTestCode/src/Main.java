import java.util.*;
import Model.*;

public class Main {
    private static final int NUMBER_OF_PLAYERS = 8;

    public static final ArrayList<Player> transporterSecondTargets = new ArrayList<>();
    public static final ArrayList<NightRecap> gameLog = new ArrayList<>();
    private static int night = 0;

    public static void main(String[] args) {
        ArrayList<Player> playersList = enterPlayersNames();
        Collections.shuffle(playersList);
        startGame(playersList);
    }

    private static ArrayList<Player> enterPlayersNames() {
        ArrayList<Player> playersList = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
            playersList.add(new Player("Player" + i, i, null));
        }
        return playersList;
    }

    private static void printPlayers(ArrayList<Player> playersList) {
        for (Player player : playersList) {
            System.out.printf("\nPlayer name: %s\nPlayer number: %d\nPlayer isAlive: %b\nPlayer role: %s\n",
                    player.name, player.number, player.isAlive, player.role.name);

            if (player.target != null) {
                System.out.printf("Player's target: %s (%s)\n", player.target.name, player.target.role.name);
            } else {
                System.out.println("Player's target: No target");
            }

            System.out.println("Player role-blocked: " + player.role.isRoleBlocked);
        }
        System.out.println("--------------------------------");
    }

    private static void startGame(ArrayList<Player> playersList) {
        ArrayList<Role> rolesList = declareAndChooseRoles();
        ArrayList<Player> playersWithRoles = assignRolesToPlayers(playersList, rolesList);

        while (!checkWinCondition(playersWithRoles)) {
            night++;
            choosingTargets(playersWithRoles);
            performActionsAndVisits(playersWithRoles);
            resetRound(playersWithRoles);
            dayPhase(playersWithRoles);
        }
    }

    private static ArrayList<Player> assignRolesToPlayers(ArrayList<Player> PlayersList,
			ArrayList<Model.Role> RolesList) {

		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			PlayersList.get(i).role = RolesList.get(i);
		}

		return PlayersList;

	}

	// Deklaracija uloga
	private static ArrayList<Model.Role> declareAndChooseRoles() {

		ArrayList<Model.Role> RolesList = new ArrayList<>();

		RolesList.add(new Godfather()); // Mafia attacking

		RolesList.add(new Mafioso()); // Mafia attacking

		RolesList.add(randomBoolean() ? new Consort() : new Framer()); // Mafia backing

//		RolesList.add(new Consort());

		RolesList.add(randomBoolean() ? new Bodyguard() : new Doctor()); // Town Protective

//		RolesList.add(new Bodyguard());

		RolesList.add(randomBoolean() ? new Vigilante() : new Veteran()); // Town Aggresive

//		RolesList.add(new Vigilante());

//		RolesList.add(randomBoolean() ? new Mayor() : new Transporter()); // Town Support

		RolesList.add(new Transporter());

		RolesList.add(randomBoolean() ? new Jester() : new Survivor()); // Unaligned Evil

//		RolesList.add(new Survivor());

		// Town investigative bi trebalo poslednji da biraju zato sto oni dobijaju
		// povratnu informaciju odmah
		RolesList.add(randomBoolean() ? new Sheriff() : new Tracker()); // Town Investigative

//		RolesList.add(new Tracker());

		return RolesList;
	}

	private static boolean randomBoolean() {

		return new Random().nextBoolean();

	}

	private static void dayPhase(ArrayList<Player> playersList) {
        System.out.println("\nPick a player to lynch\n-----------------------------");
        Map<String, Player> mafiaRoles = new HashMap<>();

        for (Player player : playersList) {
            if (player.isAlive) {
                System.out.printf("%d %s (%s)\n", player.number, player.name, player.role.name);
            }
            mafiaRoles.putIfAbsent(player.role.name, player);
        }

        System.out.println("\nChoose a number: ");
        Scanner scanner = new Scanner(System.in);
        int targetIndex = scanner.nextInt() - 1;

        if (targetIndex >= 0 && targetIndex < NUMBER_OF_PLAYERS) {
            Player target = playersList.get(targetIndex);
            target.isAlive = false;

            if (target.role.name.equals("godfather") && mafiaRoles.containsKey("mafioso")) {
                mafiaRoles.get("mafioso").role = new Godfather();
                System.out.println(mafiaRoles.get("mafioso").name + " is the new Godfather.");
            }
        }
        System.out.println("--------------------------------");
    }

    private static void resetRound(ArrayList<Player> playersList) {
        for (Player player : playersList) {
            player.role.isFramed = false;
            player.role.isRoleBlocked = false;
            player.target = null;
            player.role.defence = player.role.name.equals("godfather") ? 1 : 0;
        }
    }

    private static boolean checkWinCondition(ArrayList<Player> playersList) {
        long townCount = playersList.stream().filter(p -> p.isAlive && p.role.alignment.equals("town")).count();
        long mafiaCount = playersList.stream().filter(p -> p.isAlive && p.role.alignment.equals("mafia")).count();
        boolean survivorAlive = playersList.stream().anyMatch(p -> p.isAlive && p.role.name.equals("survivor"));

        if (mafiaCount == 0) {
            System.out.println(survivorAlive ? "Survivor wins!" : "Town wins!");
            return true;
        } else if (townCount == 0) {
            System.out.println(survivorAlive ? "Survivor wins!" : "Mafia wins!");
            return true;
        }
        return false;
    }

    private static void performActionsAndVisits(ArrayList<Player> playersList) {
        Map<String, Player> roles = new HashMap<>();
        for (Player player : playersList) {
            roles.put(player.role.name, player);
        }

        String[] actionOrder = {
                "veteran", "transporter", "consort", "doctor", "bodyguard",
                "framer", "sheriff", "mafioso", "godfather", "vigilante", "tracker"
        };

        for (String role : actionOrder) {
            if (roles.containsKey(role) && roles.get(role).isAlive) {
                roles.get(role).role.action(roles.get(role).target, playersList);
            }
        }

        printPlayers(playersList);
    }

    private static void choosingTargets(ArrayList<Player> playersList) {
        Scanner scanner = new Scanner(System.in);

        for (Player player : playersList) {
            if (!player.isAlive) continue;
            
            

            // Check if player has actions left (Jester, Survivor, Veteran, Vigilante, Doctor, Bodyguard)
            if ((player.role.name.equals("Jester") || player.role.name.equals("Survivor") ||
                 player.role.name.equals("Veteran") || player.role.name.equals("Vigilante")) && player.role.hasAction <= 0) {
                System.out.println(player.name + " (" + player.role.name + ") has no actions left.");
                continue;
            }

            if ((player.role.name.equals("Doctor") || player.role.name.equals("Bodyguard")) && player.role.hasAction <= 0) {
                // If doctor or bodyguard has no actions left, treat them as Default type
                System.out.println(player.name + " (" + player.role.name + ") has no actions left but can still choose.");
            }

            System.out.println(player.name + " (" + player.role.name + ") is choosing.");
            ArrayList<Player> availableTargets = new ArrayList<>();

            // Determine target type
            String targetType = getTargetType(player);

            for (Player target : playersList) {
                if (!target.isAlive || target.equals(player)) continue; // Can't target dead players or themselves (unless allowed)

                // **Properly filter based on target type**
                if (targetType.equals("None")) {
                    continue; // Mayor cannot choose anyone, so don't add targets
                } 
                if (targetType.equals("Only-self")) {
                    if (target.equals(player)) availableTargets.add(target);
                } 
                else if (targetType.equals("Mafia")) {
                    if (!target.role.alignment.equals("mafia")) { 
                        availableTargets.add(target); // Mafia can only target non-mafia
                    }
                } 
                else if (targetType.equals("Default")) {
                    availableTargets.add(target);
                } 
                else if (targetType.equals("Default + self")) {
                    availableTargets.add(target);
                    if (!availableTargets.contains(player)) {
                        availableTargets.add(player); // Allow self-targeting
                    }
                }
            }

            // **Final Check: Remove Mafia targets if player is Mafia**
            if (player.role.alignment.equals("mafia")) {
                availableTargets.removeIf(target -> target.role.alignment.equals("mafia"));
            }

            // If no valid targets, skip selection
            if (availableTargets.isEmpty()) {
                System.out.println("No valid targets available. Skipping selection.");
                continue;
            }

            // Allow player to skip choosing
            System.out.println("0. Skip choosing a target");

            for (int i = 0; i < availableTargets.size(); i++) {
                System.out.printf("%d. %s (%s)\n", i + 1, availableTargets.get(i).name, availableTargets.get(i).role.name);
            }

            System.out.println("Choose a number: ");
            int choice = scanner.nextInt();
            if (choice == 0) continue; // Player decided not to choose anyone

            if (choice > 0 && choice <= availableTargets.size()) {
                player.target = availableTargets.get(choice - 1);
            }

            // Transporter exception: Choose another target
            if (player.role.name.equals("Transporter")) {
                System.out.println("Choose another number: ");
                int secondChoice = scanner.nextInt();
                if (secondChoice > 0 && secondChoice <= availableTargets.size() && secondChoice != choice) {
                    transporterSecondTargets.add(availableTargets.get(secondChoice - 1));
                }
            }
        }
    }

    // Method to determine target type based on role
    private static String getTargetType(Player player) {
        switch (player.role.name) {
            case "Mayor":
                return "None";
            case "Survivor":
            case "Jester":
            case "Veteran":
                return "Only-self";
            case "Godfather":
            case "Mafioso":
            case "Consort":
            case "Framer":
                return "Mafia";
            case "Vigilante":
            case "Tracker":
            case "Sheriff":
            case "Doctor":
            case "Bodyguard":
                return "Default";
            default:
                return "Default + self"; // Any other role can choose anyone
        }
    }


    


    

}
