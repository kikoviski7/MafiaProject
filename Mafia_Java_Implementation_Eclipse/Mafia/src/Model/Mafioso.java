package Model;

import java.util.ArrayList;

public class Mafioso extends Role{
	Player mafioso;
	Player godfather;
	Player mafiaBacking;
	public Mafioso() {
		super("mafioso", 1, 0, true, false, "mafia", "attacking", 10);
	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		for(Player player : playersList) {
			if(player.role.name.equals("mafioso")) {
				mafioso = player;
			}
			if(player.role.name.equals("godfather")) {
				godfather = player;
			}
			if ("framer".equalsIgnoreCase(player.role.name) || "consort".equalsIgnoreCase(player.role.name)) {
		    	mafiaBacking = player;
		    }
		}
		
		if(!mafioso.role.isRoleBlocked) {
			if(target != null) {
				visit(target, playersList);
			}
		}
		else {
			if (mafioso.isAlive == false) {
				mafioso.isAlive = true;
				godfather.isAlive = false;
				mafioso.role.inherits = true;
				
			}
			godfather.target = target;
			mafioso.target = null;
			godfather.role.action(target, playersList);
		}
		
	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		if(mafioso.role.attack > target.role.defence) {
			target.isAlive = false;
			
			
			if(target == mafioso && godfather.isAlive == false) {
				mafiaBacking.role.inherits = true;
			}
		}
		
	}

	
}
	