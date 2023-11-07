package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String email;
	
	private String role;
	
	private String mobile;
	
	//why cascade all?--> if we delete the user then all addresses related to that user will get deleted
	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL)
	private List<Address>  address = new ArrayList<>();
	
	
//	@Embedded: This JPA annotation is used to specify that a field or property within an entity should be 
//	treated as an embedded object. It means that the fields of the embedded object should be persisted 
//	as columns in the same table as the owning entity (the entity 
//	where this annotation is used). In your code, it suggests that the elements within the collection 
//	should be treated as an embedded object
	
//	ElementCollection: This JPA annotation is used to indicate that the field should be treated as a collection 
//	of simple values (e.g., basic types like String, Integer, etc.) or embedded objects. In this context, it indicates 
//	that the elements of the collection should be treated as individual elements stored in a separate table.
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment_Information", joinColumns = @JoinColumn(name = "user_id"))
	private List<PaymentInformation> paymentInformation = new ArrayList<>();
	
	//one user can rate many products
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rating> ratings = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>(); 
	
	private LocalDateTime createdAt;

	public User() {
		super();
	}
	
	
}
