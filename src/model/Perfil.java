package model;

public enum Perfil {
	
	ADMINISTRADOR(1,"Administrador"), 
	FUNCIONARIO(2, "Funcionario");

	private int id;
	private String label;
	
	private Perfil(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return this.id;
	}

	public String getLabel() {
		return this.label;
	}

	public static Perfil valueOf(int id) {
		for (Perfil per : values())
			if (per.getId() == id)
				return per;

		return null;
	}
}
