package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Consort extends Role {
	ArrayList<String> immuneToRoleblock = new ArrayList<>(
			Arrays.asList("veteran", "transporter", "jester", "mayor", "survivor"));
	public Consort() {
		super("consort", 0, 0, true, false, "mafia", "backing");

	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		
		if(!immuneToRoleblock.contains(target.role.name)) {
			visit(target, playersList);
		}
		
	}

	//TODO: consort bi samo mogao da ukloni target sa svog targeta umesto da postoji ceo boolean koji
	// odredjuje da li neko moze da upotrebi svoju sposobnost ili ne
	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		target.role.isRoleBlocked = true;
		
	}

	
}
