package com.sinha.utilities;

import org.testng.annotations.DataProvider;

public class DataSuplier {
	
	@DataProvider
	public Object[][] getCustomerDetails()
	{
		Object[][] data = new Object[2][3];
		data[0][0] = "Diptamoy";
		data[0][1] = "Sinha";
		data[0][2] = "721507";
		
		
		data[1][0] = "Mark";		
		data[1][1] = "Hamilton";
		data[1][2] = "500236";
		
		return data;
	}

}
