package Model;

import java.util.ArrayList;

public class Veteran extends Role {
	public Veteran() {
		super("veteran", 3, 0, false, false, "town", "aggresive", 2);

	}

	@Override
	public void action(Player veteran, ArrayList<Player> PlayersList) {
		if (veteran != null) {
			visit(veteran, PlayersList);
		}
		
	}
	
	@Override
	public void visit(Player veteran, ArrayList<Player> PlayersList) {
		
		veteran.role.defence = 3;
		veteran.role.hasAction--;
		
		for(Player pl : PlayersList) {
			if (pl.role.name == "veteran") {
				continue;
			}
			if(pl.target == veteran) {
				pl.isAlive = false;
			}
		}
		
		
	}
	
	
}
