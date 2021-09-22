package com.etiya.ReCapProject.entities.concretes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="individual_customer")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class IndividualCustomer extends Customer {
	
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull
	@Column(name = "identity_number")
	private String identityNumber;


}
