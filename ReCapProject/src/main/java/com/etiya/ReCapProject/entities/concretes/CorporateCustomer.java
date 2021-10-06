package com.etiya.ReCapProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.etiya.ReCapProject.entities.abstracts.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "corporate_customer")
public class CorporateCustomer extends Customer {
	
	@NotNull
	@Column(name = "company_name")
	private String companyName;
	
	@NotNull
	@Column(name = "tax_number")
	private String taxNumber;
}
