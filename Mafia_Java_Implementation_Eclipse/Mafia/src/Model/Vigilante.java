package Model;

import java.util.ArrayList;

public class Vigilante extends Role {
	Player vigilante;
	Player godfather;
	Player mafioso;
	Player mafiaBacking;
	public Vigilante() {
		super("vigilante" ,1, 0, false, false, "town", "aggresive", 2);
	}

	@Override
	public boolean action(Player target, ArrayList<Player> playersList) {
		for (Player player : playersList) {
		    if ("godfather".equalsIgnoreCase(player.role.name)) {
		    	godfather = player;
		        break;
		    }
		}
		
		for (Player player : playersList) {
		    if ("mafioso".equalsIgnoreCase(player.role.name)) {
		    	mafioso = player;
		        break;
		    }
		}
		
		for (Player player : playersList) {
		    if ("framer".equalsIgnoreCase(player.role.name) || "consort".equalsIgnoreCase(player.role.name)) {
		    	mafiaBacking = player;
		        break;
		        
		    }
		}
		
		for(Player player : playersList) {
			if(player.role.name.equals("vigilante")) {
				vigilante = player;
				break;
			}
		}
		
		if (!vigilante.role.isRoleBlocked && vigilante.role.actionsLeft > 0) {
			if(target != null) {
			   return visit(target, playersList);
			}
			
		}
		return false;
		
	}

	@Override
	public boolean visit(Player target, ArrayList<Player> PlayersList) {
		if(target.role.defence < vigilante.role.attack) {
			target.isAlive = false;
			
			
		}
		return true;
		
	}

	
}
