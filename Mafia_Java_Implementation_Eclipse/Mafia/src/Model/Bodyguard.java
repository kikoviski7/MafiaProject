package Model;

import java.util.ArrayList;

public class Bodyguard extends Role {
	public Player bodyguard;

	public Bodyguard() {
		super("bodyguard", 2, 0, false, false, "town", "protective", 1);

	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		for (Player player : playersList) {
		    if ("bodyguard".equalsIgnoreCase(player.role.name)) {
		        bodyguard = player;
		        break; // Stop searching after finding the first match
		    }
		}
		if (bodyguard.role.isRoleBlocked != true) {
			if (!(bodyguard == target && bodyguard.role.hasAction > 0)) {
				return;
			}
			visit(target, playersList);
		}
	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		
		target.role.defence = 2;
		
	}

	

}
