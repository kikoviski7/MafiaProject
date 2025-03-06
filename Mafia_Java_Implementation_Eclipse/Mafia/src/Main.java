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

	public static int night = 0;

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
			System.out.println("Player isAlive: " + PlayersList.get(i).isAlive);
			System.out.println("Player role: " + PlayersList.get(i).role.name);

			if (PlayersList.get(i).role.name.equals("transporter") && PlayersList.get(i).isAlive) {
				System.out.println("Players targets: " + PlayersList.get(i).target.name + " "
						+ PlayersList.get(i).target.role.name + " " + transporterSecondTargets.get(night - 1).name + " "
						+ transporterSecondTargets.get(night - 1).role.name);
			}

			else if (PlayersList.get(i).target != null) {
				System.out.println("Players target: " + PlayersList.get(i).target.name + " "
						+ PlayersList.get(i).target.role.name);
			} else {
				System.out.println("Players target: no target");
			}
			System.out.println("Player role-blocked: " + PlayersList.get(i).role.isRoleBlocked);
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

		while (!checkWinCondition(PlayersListWithRoles)) {

			night++;

			choosingTragets(PlayersListWithRoles);

			performActionsAndVisits(PlayersListWithRoles, RolesList);

			setDefaultRound(PlayersListWithRoles);

			day(PlayersListWithRoles);
		}

	}

	private static void day(ArrayList<Player> playersList) {
		System.out.println("\nPick a player to lynch"
				+ "\n-----------------------------\n");

		for (int i = 0; i < numberOfPlayers; i++) {
			Player pl = playersList.get(i);

			if (pl.isAlive) {
				System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
			}
		}

		System.out.println("\nChoose a number: ");
		
		Scanner scanner = new Scanner(System.in); // Create a Scanner object
		int target = scanner.nextInt() - 1; // Reads integer input
		if (target >= 0 && target <= 8) {
			playersList.get(target).isAlive = false;
			System.out.println("--------------------------------");
		}

		while(target <= 0 && target >= 8) {
			System.out.println("\nChoose a number: ");
			target = scanner.nextInt() - 1; // Reads integer input
			if (target >= 0 && target <= 8) {
				playersList.get(target).isAlive = false;
				System.out.println("--------------------------------");
			}
		}
		
		

	}

	private static void setDefaultRound(ArrayList<Player> playersList) {
		for (Player player : playersList) {
			player.role.isFramed = false;
			player.role.isRoleBlocked = false;
			player.target = null;

			if (player.role.name.equals("godfather")) {
				player.role.defence = 1;
			} else {
				player.role.defence = 0;
			}
			
			//Mafia inheritance
			if (player.role.inherits) {
				if (player.role.name == "mafioso") {
					player.role = new Godfather();
					System.out.println("Godfather died. You are new Godfather");
				}
				else if(player.role.name == "framer" || player.role.name == "consort") {
					player.role = new Mafioso();
					System.out.println("Mafioso died. You are new Mafioso");
				}
			}

		}

	}

	private static boolean checkWinCondition(ArrayList<Player> playersList) {

		ArrayList<Player> town = new ArrayList<>();
		ArrayList<Player> mafia = new ArrayList<>();
		Player survivor = null;

		for (Player player : playersList) {
			if (player.isAlive && player.role.alignment.equals("mafia")) {
				mafia.add(player);
			}
			if (player.isAlive && player.role.alignment.equals("town")) {
				town.add(player);
			}
			if (player.isAlive && player.role.name.equals("survivor")) {
				survivor = player;
			}
		}

		if (mafia.size() == 0) {
			if (!(survivor == null)) {
				System.out.println("Survivor wins!");


			} else {
				System.out.println("Town wins!");

			}
			return true;
		}

		else if (town.size() == 0) {
			if (!(survivor == null)) {
				System.out.println("Survivor wins!");

			} else {
				System.out.println("Mafia wins!");

			}
			return true;
		}

		else {
			return false;
		}
	}
	// Ako role detektuje da je ubio Godfathera, trazi mafiosu u listi rolova i tom igracu dodeljuje godfathera.

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
		Player jester = null;
		Player survivor = null;

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
			case "survivor":
				survivor = player;
				break;
			case "jester":
				jester = player;
				break;
			case "mayor":
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + player.role.name);
			}
		}

		
		// TODO: red akcija ce biti definisan listom: prvi role u listi ima priotitet?
		if (veteran != null) {
			veteran.role.action(veteran.target, PlayersList);
		}

		if (transporter != null && transporter.isAlive) {
			transporter.role.action(transporterSecondTargets.get(night - 1), PlayersList);
		}

		if (consort != null) {
			consort.role.action(consort.target, PlayersList);
		}

		if (survivor != null) {
			survivor.role.action(survivor.target, PlayersList);
		}

		if (jester != null) {
			jester.role.action(jester.target, PlayersList);
		}

		if (doctor != null) {
			doctor.role.action(doctor.target, PlayersList);
		}

		// TODO: svaki napadac treba da proveri da li bodyguard cuva njegov target pre
		// napada
		if (bodyguard != null) {
			bodyguard.role.action(bodyguard.target, PlayersList);
		}

		// TODO: framer kao role je useless kada nema sheriffa. razmisliti kako uticati
		// na trackera
		// da li da se trackeru prikaze random lik, ili da tracker prosto ne moze da
		// trackuje osobu koja je frame-ovana?
		// kada framer framuje, susToSheriff mu se invertuje
		// ako je bio sus vise nije, ako nije bio sus, sad jeste
		// ako transporter zameni neku mafiju, framer moze da frameuje svog
		if (framer != null) {
			framer.role.action(framer.target, PlayersList);
		}

		// TODO: Sheriff action treba da se premesti gore u deo gde se bira. Samim tim bi i framer trebalo da se premesti gore
		if (sheriff != null) {
			sheriff.role.action(sheriff.target, PlayersList);
		}
		
		if (mafioso != null) {
			mafioso.role.action(mafioso.target, PlayersList);
		}
		
		if (godfather != null) {
			godfather.role.action(godfather.target, PlayersList);
		}

		if (vigilante != null) {
			vigilante.role.action(vigilante.target, PlayersList);
		}

		// TODO: Tracker action treba da se premesti gore u deo gde se bira
		// Tracker ne moze da trackuje transportera?
		// ili da se napravi kao da je transporter uvek frameovan od strane trackera
		if (tracker != null) {
			tracker.role.action(tracker.target, PlayersList);
		}

		printPlayers(PlayersList);

	}
	// Execution order
	// veteran
	// transporter
	// consort
	// jester/survivor
	// doctor/bodyguard
	// framer
	// sheriff
	// mafioso
	// vigilante/godfather
	// tracker

	// TODO: kada biraju doctor, bodyguard, veteran, jester i survivor, napisati
	// koliko imaju action left.
	private static void choosingTragets(ArrayList<Player> PlayersList) {
		for (Player player : PlayersList) {

			if (player.isAlive) {

				// Mayor - None

				if (player.role.name == "mayor") {
//					System.out.println("\n-----------------------------\n");
//					System.out.println(player.name + " " + player.role.name + " does not choose");
//					System.out.println("\n-----------------------------\n");
					continue;
				}

				// TODO: survivor i jester su slicni kao veteran
				if (player.role.alignment == "unaligned") {
					System.out.println(player.name + " " + player.role.name + " is choosing"
							+ "\n-----------------------------\n");

					Scanner scanner = new Scanner(System.in); // Create a Scanner object
					System.out.println("Protect yourself?: y/n\n");
					System.out.println("\n-----------------------------\n");

					String decision = scanner.nextLine(); // Read user input

					if (decision.trim().equals("y")) {
						player.target = player;
					}
					continue;
				}

				// TODO: mafija moze da izabere mafiju iako se ne prikazuje
				// Mafioso, Godfather, Framer, Consort - Mafia choosing
				if (player.role.alignment == "mafia") {
					// Ako je Godfather ziv, mafioso ne bira
					// TODO: ovde sam stavio: "ako je ziv prvi u listi" bice bug ako bude mesao
					// listu rolova
					if (player.role.name == "mafioso" && PlayersList.get(0).isAlive == true) {
						player.target = PlayersList.get(0).target;
						PlayersList.get(0).target = null;
						continue;
					}

					System.out.println(player.name + " " + player.role.name + " is choosing"
							+ "\n-----------------------------\n");

					for (int i = 0; i < numberOfPlayers; i++) {
						Player pl = PlayersList.get(i);
						if (pl.role.alignment == "mafia" || !(pl.isAlive)) {
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
					System.out.println(player.name + " " + player.role.name + " is choosing"
							+ "\n-----------------------------\n");

					// Get throught all players
					for (int i = 0; i < numberOfPlayers; i++) {
						Player pl = PlayersList.get(i);

						if (pl.isAlive) {
							System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
						}
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
				// TODO: ako veteran nema alertova ne moze da alertuje
				if (player.role.name == "veteran") {

					System.out.println(player.name + " " + player.role.name + " is choosing"
							+ "\n-----------------------------\n");

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

					System.out.println(player.name + " " + player.role.name + " is choosing"
							+ "\n-----------------------------\n");

					for (int i = 0; i < numberOfPlayers; i++) {
						Player pl = PlayersList.get(i);

						if (pl.isAlive) {
							System.out.println(i + 1 + " " + pl.name + " " + pl.role.name);
						}
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

					System.out.println(player.name + " " + player.role.name + " is choosing"
							+ "\n-----------------------------\n");

					for (int i = 0; i < numberOfPlayers; i++) {
						Player pl = PlayersList.get(i);

						if (player == pl || !(pl.isAlive)) {
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

//		RolesList.add(randomBoolean() ? new Consort() : new Framer()); // Mafia backing

		RolesList.add(new Consort());

//		RolesList.add(randomBoolean() ? new Bodyguard() : new Doctor()); // Town Protective

		RolesList.add(new Bodyguard());

//		RolesList.add(randomBoolean() ? new Vigilante() : new Veteran()); // Town Aggresive

		RolesList.add(new Vigilante());

//		RolesList.add(randomBoolean() ? new Mayor() : new Transporter()); // Town Support

		RolesList.add(new Transporter());

//		RolesList.add(randomBoolean() ? new Jester() : new Survivor()); // Unaligned Evil
		
		RolesList.add(new Survivor());

		// Town investigative bi trebalo poslednji da biraju zato sto oni dobijaju
		// povratnu informaciju odmah
//		RolesList.add(randomBoolean() ? new Sheriff() : new Tracker()); // Town Investigative

		RolesList.add(new Tracker());


		return RolesList;
	}

}
