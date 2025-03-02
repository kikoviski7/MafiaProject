package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Tracker extends Role {
	Player tracker;
	ArrayList<String> untrackable = new ArrayList<>(
			Arrays.asList("veteran", "transporter", "mayor", "jester", "survivor"));
	public Tracker() {
		super("tracker", 0, 0, false, false, "town", "investigative", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {

		for (Player player : playersList) {
			if (player.role.name.equals("tracker")) {
				tracker = player;
				break;
			}
		}
		
		if (!tracker.role.isRoleBlocked) {
			visit(target, playersList);
		}
		
	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		if(untrackable.contains(target)) {
			System.out.println("Your target is fast as fuck boii");
		}
		else if(target.role.isFramed) {
			System.out.println("Zajebote.");
		}
		else if(target.target == null) {
			System.out.println("Your target lazy af.");
		}
		else {
			System.out.println("Your target visited " + target.target.name);
		}
		
	}

	
}
