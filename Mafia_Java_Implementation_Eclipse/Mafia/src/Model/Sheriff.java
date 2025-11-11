package Model;

import java.util.ArrayList;

public class Sheriff extends Role {
	Player sheriff;
	public Sheriff() {
		super("sheriff", 0, 0, false, false, "town", "investigative", 10);

	}

	@Override
	public boolean action(Player target, ArrayList<Player> playersList) {
		for (Player player : playersList) {
			if (player.role.name.equals("sheriff")) {
				sheriff = player;
				break;
			}
		}
		
		if (sheriff.role.isRoleBlocked) {
			return false;
		}
		if(target != null) {
			visit(target, playersList);
		}
		return false;
		
	}

	@Override
	public boolean visit(Player target, ArrayList<Player> PlayersList) {
		if (target.role.suspiciousToSheriff || target.role.isFramed) {
			System.out.println("Yo, nigga's bugarin!");
		}
		else {
			System.out.println("Nigga's clean, legit.");
		}
		return false;
		
	}
	
	

	
}
