package id.ratware.pojo.typicode;

import lombok.Data;

@Data
public class TypicodeResponse {
	private String website;
	private Address address;
	private String phone;
	private String name;
	private Company company;
	private Integer id;
	private String email;
	private String username;
}
