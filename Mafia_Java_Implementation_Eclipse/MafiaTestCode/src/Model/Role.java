package Model;

import java.util.ArrayList;

public abstract class Role {
	public String name;
	public int attack;
	public int defence;
	public boolean suspiciousToSheriff;
	public boolean isFramed = false;
	public String alignment;
	public boolean isRoleBlocked = false;
	// category
	public String category;
	public boolean hasAction;
	

	
	public Role(String name, int attack, int defence, boolean suspiciousToSheriff, boolean isFramed, String alignment, String subAlignment) {
		this.name = name;
		this.attack = attack;
		this.defence = defence;
		this.suspiciousToSheriff = suspiciousToSheriff;
		this.isFramed = isFramed;
		this.alignment = alignment;
		this.category = subAlignment;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public boolean isSuspiciousToSheriff() {
		return suspiciousToSheriff;
	}

	public void setSuspiciousToSheriff(boolean suspiciousToSheriff) {
		this.suspiciousToSheriff = suspiciousToSheriff;
	}

	public boolean isFramed() {
		return isFramed;
	}

	public void setFramed(boolean isFramed) {
		this.isFramed = isFramed;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String subAlignment) {
		this.category = subAlignment;
	}


	public abstract void action(Player target, ArrayList<Player> playersList);


	public abstract void visit(Player player, ArrayList<Player> PlayersList);
		
		
	


	

	

}
