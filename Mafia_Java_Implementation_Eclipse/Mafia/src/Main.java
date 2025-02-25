import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import Model.*;

public class Main {
	// Number of players
	final static int numberOfPlayers = 8;

	// Ovde cuvam svaki second target od transportera zbog logova
	public static ArrayList<Player> transporterSecondTargets = new ArrayList<>();

	// roundRecaps(night, action(targetedBy, target))
	public static ArrayList<NightRecap> gameLog = new ArrayList<>();

	public static int night = 1;

	public static void main(String[] args) {

		ArrayList<Player> PlayersList = enterPlayersNames();

		shufflePlayers(PlayersList);

		startGame(PlayersList);
	}

	public static ArrayList<Player> enterPlayersNames() {
		// Lista igraca:
		ArrayList<Player> PlayersList = new ArrayList<>();

		for (int i = 1; i <= numberOfPlayers; i++) {

			Scanner myObj = new Scanner(System.in); // Create a Scanner object
//			System.out.println("Unesite ime igraca: ");

//			String playerName = myObj.nextLine(); // Read user input

			String playerName = "Player" + i;

			Player player = new Player(playerName, i, null);

			PlayersList.add(player);

		}
		return PlayersList;
	}

	// Prikazivanje igraca
	public static void printPlayers(ArrayList<Player> PlayersList) {

		for (int i = 0; i < PlayersList.size(); i++) {
			System.out.println("\nPlayer name: " + PlayersList.get(i).name);
			System.out.println("Player number: " + PlayersList.get(i).number);
			System.out.println("Player role: " + PlayersList.get(i).role.name);
			System.out.println("Player isAlive: " + PlayersList.get(i).isAlive);
			if (PlayersList.get(i).target != null) {
				System.out.println("Players target: " + PlayersList.get(i).target.name + " "
						+ PlayersList.get(i).target.role.name);
			} else {
				System.out.println("Players target: no target");
			}
		}
		System.out.println("--------------------------------");
	}

	public static void shufflePlayers(ArrayList<Player> PlayersList) {
		Collections.shuffle(PlayersList);
	}

	public static ArrayList<Player> assignRoles(ArrayList<Player> PlayersList) {

		ArrayList<Player> PlayersWithRoles = null;

		return PlayersWithRoles;
	}

	public static void startGame(ArrayList<Player> PlayersList) {

		// Deklaracija uloga
		ArrayList<Model.Role> RolesList = declareAndChooseRoles();

		ArrayList<Player> PlayersListWithRoles = assignRolesToPlayers(PlayersList, RolesList);

		while (checkWinCondition() == false) {
			choosingTragets(PlayersListWithRoles);

			performActionsAndVisits(PlayersListWithRoles, RolesList);
		}

	}

	private static boolean checkWinCondition() {
		// TODO Auto-generated method stub
		return false;
	}

	private static void performActionsAndVisits(ArrayList<Player> PlayersList, ArrayList<Model.Role> RolesList) {

		Player veteran = null;
		Player transporter = null;
		Player consort = null;
		Player doctor = null;
		Player bodyguard = null;
		Player framer = null;
		Player sheriff = null;
		Player vigilante = null;
		Player mafioso = null;
		Player godfather = null;
		Player tracker = null;

		for (Player player : PlayersList) {
			switch (player.role.name) {
			case "veteran":
				veteran = player;
				break;
			case "transporter":
				transporter = player;
				break;
			case "consort":
				consort = player;
				break;
			case "doctor":
				doctor = player;
				break;
			case "bodyguard":
				bodyguard = player;
				break;
			case "framer":
				framer = player;
				break;
			case "sheriff":
				sheriff = player;
				break;
			case "vigilante":
				vigilante = player;
				break;
			case "mafioso":
				mafioso = player;
				break;
			case "godfather":
				godfather = player;
				break;
			case "tracker":
				tracker = player;
				break;
			case "mayor":
				break;
			case "survivor":
				break;
			case "jester":
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + player.role.name);
			}
		}

		// TODO: testirati da li veteran umire.
		// TODO: red akcija ce biti definisan listom: prvi role u listi ima priotitet
		// TODO: ako veteran nema alertova ne moze da alertuje
		if (veteran != null) {
			veteran.role.action(veteran.target, PlayersList);
		}

		if (transporter != null) {
			transporter.role.action(transporterSecondTargets.get(night - 1), PlayersList);
		}

		if (consort != null) {
			consort.role.action(consort.target, PlayersList);
		}

		if (doctor != null) {
			doctor.role.action(doctor.target, PlayersList);
		}
		
		//TODO: svaki napadac treba da proveri da li bodyguard cuva njegov target pre napada
		if (bodyguard != null) {
			bodyguard.role.action(bodyguard.target, PlayersList);
		}

		if (framer != null) {
			framer.role.action(framer.target, PlayersList);
		}

		if (sheriff != null) {
			sheriff.role.action(sheriff.target, PlayersList);
		}

		if (vigilante != null) {
			vigilante.role.action(vigilante.target, PlayersList);
		}

		if (mafioso != null) {
			mafioso.role.action(mafioso.target, PlayersList);
		}
		
		if (godfather != null) {
			godfather.role.action(godfather.target, PlayersList);
		}
		
		if (tracker != null) {
			tracker.role.action(tracker.target, PlayersList);
		}

		printPlayers(PlayersList);

	}
	// Execution order
	// veteran
	// transporter
	// consort
	// doctor/bodyguard
	// framer
	// sheriff
	// vigilante/mafioso/godfather
	// tracker

	private static void choosingTragets(ArrayList<Player> PlayersList) {
		for (Player player : PlayersList) {

			// Survivor, Mayor, Jester - None
			if (player.role.alignment == "unaligned" || player.role.name == "mayor") {
				System.out.println("\n-----------------------------\n");
				System.out.println(player.name + " " + player.role.name + " does not choose");
				System.out.println("\n-----------------------------\n");
				continue;
			}

			// TODO: mafija moze da izabere mafiju iako se ne prikazuje
			// Mafioso, Godfather, Framer, Consort - Mafia choosing
			if (player.role.alignment == "mafia") {
				// Ako je Godfather ziv, mafioso ne bira
				if (player.role.name == "mafioso" && PlayersList.get(0).isAlive == true) {
					continue;
				}

				System.out.println(
						player.name + " " + player.role.name + " is choosing" + "\n-----------------------------\n");

				for (int i = 0; i < numberOfPlayers; i++) {
					Player pl = PlayersList.get(i);
					if (pl.role.alignment == "mafia") {
						continue;
					}
					System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
				}

				System.out.println("\nChoose a number: ");

				Scanner scanner = new Scanner(System.in); // Create a Scanner object
				int target = scanner.nextInt(); // Reads integer input
				player.target = PlayersList.get(target - 1);
				System.out.println("--------------------------------");

				continue;
			}

			// Transporter - Chooses anyone (two targets)
			if (player.role.name == "transporter") {
				System.out.println(
						player.name + " " + player.role.name + " is choosing" + "\n-----------------------------\n");

				// Get throught all players
				for (int i = 0; i < numberOfPlayers; i++) {
					Player pl = PlayersList.get(i);

					System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
				}

				System.out.println("\nChoose a number: ");

				Scanner scanner = new Scanner(System.in); // Create a Scanner object
				int target = scanner.nextInt(); // Reads integer input
				player.target = PlayersList.get(target - 1);
				System.out.println("--------------------------------");

				// Get throught all players
				for (int i = 0; i < numberOfPlayers; i++) {
					Player pl = PlayersList.get(i);
					if (!pl.equals(player.target))
						System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
				}

				System.out.println("\nChoose another number: ");

				Scanner secondScanner = new Scanner(System.in); // Create a Scanner object
				int secondTarget = scanner.nextInt(); // Reads integer input
				transporterSecondTargets.add(PlayersList.get(secondTarget - 1));
				continue;
			}

			// Veteran - Only self choose
			if (player.role.name == "veteran") {

				System.out.println(
						player.name + " " + player.role.name + " is choosing" + "\n-----------------------------\n");

				Scanner scanner = new Scanner(System.in); // Create a Scanner object
				System.out.println("Stay alert?: y/n\n");
				System.out.println("\n-----------------------------\n");

				String veteranDecision = scanner.nextLine(); // Read user input

				if (veteranDecision.trim().equals("y")) {
					player.target = player;
				}
				continue;

			}

			// Doctor, Bodyguard - Can choose everyone
			if (player.role.getCategory() == "protective") {

				System.out.println(
						player.name + " " + player.role.name + " is choosing" + "\n-----------------------------\n");

				for (int i = 0; i < numberOfPlayers; i++) {
					Player pl = PlayersList.get(i);

					System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
				}

				System.out.println("\nChoose a number: ");

				Scanner scanner = new Scanner(System.in); // Create a Scanner object
				int target = scanner.nextInt(); // Reads integer input
				player.target = PlayersList.get(target - 1);
				System.out.println("--------------------------------");
			}

			// Vigilante, Tracker, Sheriff - Default choosing (Everyone but them)
			// TODO: Vigilante moze da izabere sebe a ne bi smeo
			else {

				System.out.println(
						player.name + " " + player.role.name + " is choosing" + "\n-----------------------------\n");

				for (int i = 0; i < numberOfPlayers; i++) {
					Player pl = PlayersList.get(i);

					if (player == pl) {
						continue;
					}
					System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
				}

				System.out.println("Choose a number: ");

				Scanner scanner = new Scanner(System.in); // Create a Scanner object
				int target = scanner.nextInt(); // Reads integer input
				player.target = PlayersList.get(target - 1);
				System.out.println("--------------------------------");
			}

		}

	}

	private static ArrayList<Player> assignRolesToPlayers(ArrayList<Player> PlayersList,
			ArrayList<Model.Role> RolesList) {

		for (int i = 0; i < numberOfPlayers; i++) {
			PlayersList.get(i).role = RolesList.get(i);
		}

		return PlayersList;

	}

	private static boolean randomBoolean() {

		return new Random().nextBoolean();

	}

	// Deklaracija uloga
	private static ArrayList<Model.Role> declareAndChooseRoles() {

		ArrayList<Model.Role> RolesList = new ArrayList<>();

		RolesList.add(new Godfather()); // Mafia attacking

		RolesList.add(new Mafioso()); // Mafia attacking

		RolesList.add(randomBoolean() ? new Consort() : new Framer()); // Mafia backing

		RolesList.add(randomBoolean() ? new Bodyguard() : new Doctor()); // Town Protective

		RolesList.add(randomBoolean() ? new Vigilante() : new Veteran()); // Town Aggresive

		RolesList.add(randomBoolean() ? new Sheriff() : new Tracker()); // Town Investigative

		RolesList.add(randomBoolean() ? new Mayor() : new Transporter()); // Town Support

		RolesList.add(randomBoolean() ? new Jester() : new Survivor()); // Unaligned Evil

//		for (Role role : RolesList) {
//			System.out.println("Role: " + role.name);
//
//		}

		return RolesList;
	}

}
