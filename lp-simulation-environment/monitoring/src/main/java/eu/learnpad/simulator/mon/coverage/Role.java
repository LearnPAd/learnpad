package eu.learnpad.simulator.mon.coverage;

public class Role {

	private int id;
	private String roleName;

	public Role(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
