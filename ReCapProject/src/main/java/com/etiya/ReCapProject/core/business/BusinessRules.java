package com.etiya.ReCapProject.core.business;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public class BusinessRules {
	
	public static Result run (Result... logics)
	{
		for (Result  logic : logics)  {
			
			if(!logic.isSuccess() )
			{
				return logic;
			}
			
		}
		
		return null;
	}
	
	public static DataResult run (DataResult... logics)
	{
		for (DataResult  logic : logics)  {
			
			if(!logic.isSuccess() )
			{
				return logic;
			}
			
		}
		
		return null;
	}
	
}
