package Model;

import java.util.ArrayList;

public class Mafioso extends Role{
	Player mafioso;
	Player godfather;
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
		}
		
		if(!mafioso.role.isRoleBlocked) {
			visit(target, playersList);
		}
		else {
			if (mafioso.isAlive == false) {
				mafioso.isAlive = true;
				godfather.isAlive = false;
				
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
		}
		
	}

	
}
	