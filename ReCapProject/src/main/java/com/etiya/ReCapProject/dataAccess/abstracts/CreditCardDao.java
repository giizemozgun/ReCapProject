package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.CreditCard;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
		
//
//	boolean existsByCardName(String cardName);
//	CreditCard getByCardName(String cardName);
	
	List<CreditCard> getByCustomer_id(int customerId);
	
}
