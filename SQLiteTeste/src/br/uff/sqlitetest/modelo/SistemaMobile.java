package br.uff.sqlitetest.modelo;

public class SistemaMobile {
	
	private String id;
	
	private String name;
	
	private String company;
	
	public SistemaMobile() {
	}
	
	public SistemaMobile(String id, String name, String title) {
		
		this.id = id;
		this.name = name;
		this.company = title;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setGameTitle(String gameTitle) {
		this.company = gameTitle;
	}
	
	public String toString(){
		return this.name;
	}
}
