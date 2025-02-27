package Model;

import java.util.ArrayList;

//TODO: zavrsiti bodyguarda; niko ne umire kada bodyguard nekog zastiti

public class Bodyguard extends Role {
	public Player bodyguard;
	Player attacker;

	public Bodyguard() {
		super("bodyguard", 2, 0, false, false, "town", "protective", 1);

	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		for (Player player : playersList) {
		    if ("bodyguard".equalsIgnoreCase(player.role.name)) {
		        bodyguard = player;
		        
		    }
		}
		for (Player player : playersList) {
			if(player.target == bodyguard.target && player != bodyguard) {
		    	attacker = player;
		    }
		}
		
		if (bodyguard.role.isRoleBlocked != true) {
			if ((bodyguard == target && bodyguard.role.hasAction == 0)) {
				return;
			}
			visit(target, playersList);
		}
	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		
		target.role.defence = 2;
		if (target != bodyguard) {
			if(target.role.defence > bodyguard.role.attack) {
				bodyguard.isAlive = false;
				attacker.isAlive = false;
			}
		}
		
		
		
	}

	

}
