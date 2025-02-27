package Model;

import java.util.ArrayList;

public class Vigilante extends Role {
	Player vigilante;
	public Vigilante() {
		super("vigilante" ,1, 0, false, false, "town", "aggresive", 2);
	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		for(Player player : playersList) {
			if(player.role.name.equals("vigilante")) {
				vigilante = player;
				break;
			}
		}
		
		if (!vigilante.role.isRoleBlocked && vigilante.role.hasAction > 0) {
			visit(target, playersList);
			
		}
		
	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		if(target.role.defence < vigilante.role.attack) {
			target.isAlive = false;
		}
		
	}

	
}
