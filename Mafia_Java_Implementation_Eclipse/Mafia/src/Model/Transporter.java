package Model;

import java.util.ArrayList;

public class Transporter extends Role {
	Player transporterSecondTarget;
	public Transporter() {
		super("transporter", 0, 0, false, false, "town", "support", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(Player secondTarget, ArrayList<Player> playersList) {
		
		Player transporter = null;
		
		for(Player player : playersList) {
			if (player.role.name.equals("transporter")){
				transporter = player;
			}
		}
		
		if (transporter != null) {
			if (secondTarget != null && transporter.target != null) {
				transporterSecondTarget = secondTarget;
				if (secondTarget.role.name.equals("veteran")) {
					transporter.isAlive = false;
				}
				visit(transporter, playersList);
			}
			return;
		}
		
	}

	@Override
	public void visit(Player transporter, ArrayList<Player> playersList) {
		
		ArrayList<Player> visitsFirstTarget = new ArrayList<>();
		ArrayList<Player> visitsSecondTarget = new ArrayList<>();
		
		for(Player player : playersList) {
			if (player.target == transporter.target && player != transporter) {
				if(player.role.name.equals("veteran") && player.target.role.name.equals("veteran")) {
					continue;
				}
				if(player.role.name.equals("survivor") && player.target.role.name.equals("survivor")) {
					continue;
				}
				if(player.role.name.equals("jester") && player.target.role.name.equals("jester")) {
					continue;
				}
				visitsFirstTarget.add(player);
			}
			
		}
		
		for(Player player : playersList) {
			if (player.target == transporterSecondTarget && player != transporter) {
				if(player.role.name.equals("veteran") && player.target.role.name.equals("veteran")) {
					continue;
				}
				if(player.role.name.equals("survivor") && player.target.role.name.equals("survivor")) {
					continue;
				}
				if(player.role.name.equals("jester") && player.target.role.name.equals("jester")) {
					continue;
				}
				visitsSecondTarget.add(player);
			}
		}
		
		for(Player player : visitsFirstTarget) {
			player.target = transporterSecondTarget;
		}
		
		for(Player player : visitsSecondTarget) {
			player.target = transporter.target;
		}
		
		
		
	}

	
}
