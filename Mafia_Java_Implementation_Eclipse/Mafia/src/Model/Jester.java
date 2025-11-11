package Model;

import java.util.ArrayList;

public class Jester extends Role {
	public Jester() {
		super("jester", 0, 0, false, false, "unaligned", "evil", 1);

	}

	@Override
	public boolean action(Player jester, ArrayList<Player> playersList) {
		if(jester != null && jester.role.isRoleBlocked == false && jester.role.actionsLeft > 0) {
			visit(jester, playersList);
		}
		return false;
		
	}

	@Override
	public boolean visit(Player jester, ArrayList<Player> PlayersList) {
		
		jester.role.defence = 2;
		jester.role.actionsLeft--;
		return false;
		
	}

	
}
