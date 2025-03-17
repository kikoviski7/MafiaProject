package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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
			if(target != null) {
				visit(target, playersList);
			}
		}
		
	}

	@Override
	public void visit(Player target, ArrayList<Player> playersList) {
		if(untrackable.contains(target.role.name)) {
			System.out.println("Your target is untrackable.");
		}
		else {
			if(target.role.isFramed) {
				
				Random r = new Random();
				int randomInt = r.nextInt(playersList.size());
				while(randomInt == 0 || playersList.get(randomInt) == target) {
					randomInt = r.nextInt(playersList.size());
				}
				System.out.println("Your target visited " + playersList.get(randomInt).name + " " + playersList.get(randomInt).role.name);
			}
			else if(target.target == null) {
				System.out.println("Your target did not visit.");
			}
			else {
				System.out.println("Your target visited " + target.target.name + " " + target.target.role.name);
			}
		}
		
	}

	
}
