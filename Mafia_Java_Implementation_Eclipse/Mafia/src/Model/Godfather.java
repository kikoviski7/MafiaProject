package Model;

import java.util.ArrayList;

public class Godfather extends Role {
	Player godfather;

	public Godfather() {
		super("godfather", 1, 1, false, false, "mafia", "attacking", 10); // Call the parent constructor with required parameter
	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		for(Player player : playersList) {
			if(player.role.name.equals("godfather")) {
				godfather = player;
			}
		}
		
		if(!godfather.role.isRoleBlocked) {
			visit(target, playersList);
		}
		
	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		if(godfather.role.attack > godfather.role.defence) {
			target.isAlive = false;
		}
		
	}


	
}
