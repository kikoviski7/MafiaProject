package Model;

import java.util.ArrayList;

public class Veteran extends Role {
	Player godfather;
	Player mafioso;
	Player mafiaBacking;
	public Veteran() {
		super("veteran", 3, 0, false, false, "town", "aggresive", 2);

	}

	@Override
	public boolean action(Player veteran, ArrayList<Player> playersList) {
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
		
		
		if (veteran != null) {
			return visit(veteran, playersList);
		}
		return false;
		
		
	}
	
	@Override
	public boolean visit(Player veteran, ArrayList<Player> playersList) {
		
		veteran.role.defence = 3;
		veteran.role.actionsLeft--;
		
		for(Player pl : playersList) {
			if (pl.role.name == "veteran") {
				continue;
			}
			if(pl.target == veteran) {
				pl.isAlive = false;
				if(pl == godfather) {
					mafioso.role.inherits = true;
				}
				else if(pl == mafioso && godfather.isAlive == false) {
					mafiaBacking.role.inherits = true;
				}
			}
		}
		return true;
		
		
	}
	
	
}
