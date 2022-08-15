package POJO;

public class Player {

	private String name;
	private String country;
	private String role;
	private Double priceInCrores;
	
	
	public Player(String name, String country, String role, Double priceInCrores) {
		this.name = name;
		this.country = country;
		this.role = role;
		this.priceInCrores = priceInCrores;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Double getPriceInCrores() {
		return priceInCrores;
	}
	public void setPriceInCrores(Double priceInCrores) {
		this.priceInCrores = priceInCrores;
	}
	
	
}
