package Model;

import java.util.ArrayList;

public class Doctor extends Role {
	Player doctor = null;
	public Doctor() {
		super("doctor", 0, 0, false, false, "town", "protective", 1);

	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		
		for (Player player : playersList) {
		    if ("doctor".equalsIgnoreCase(player.role.name)) {
		        doctor = player;
		        break; // Stop searching after finding the first match
		    }
		}
		if (doctor.role.isRoleBlocked != true) {
			if (!(doctor == target && doctor.role.hasAction > 0)) {
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
