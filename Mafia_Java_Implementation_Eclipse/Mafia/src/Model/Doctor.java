package Model;

import java.util.ArrayList;

//TODO: doctor nije uspeo da zastiti target. Proveriti doctora opet

public class Doctor extends Role {
	Player doctor = null;
	public Doctor() {
		super("doctor", 0, 0, false, false, "town", "protective", 1);

	}

	@Override
	public boolean action(Player target, ArrayList<Player> playersList) {
		
		for (Player player : playersList) {
		    if ("doctor".equalsIgnoreCase(player.role.name)) {
		        doctor = player;
		        break; // Stop searching after finding the first match
		    }
		}
		if (!doctor.role.isRoleBlocked) {
			if ((doctor == target && doctor.role.actionsLeft == 0)) {
				System.out.println("You took one. Take it or leave.");
				return false;
			}
			if(target != null) {
				visit(target, playersList);
			}
		}
		return false;
		
		 
		
	}

	@Override
	public boolean visit(Player target, ArrayList<Player> PlayersList) {
		target.role.defence = 2;
		return false;
		
	}

	
}
