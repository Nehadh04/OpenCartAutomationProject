package opencartpackage;

import org.testng.annotations.DataProvider;

public class OpenCartDataProvider {
	
	@DataProvider(name = "registrationdata") 
	public Object GetRegistrationData() 
	{
		Object data[][] =  { 
							{"jack","Soe", "username8199992@gmail.com","403000001", "aaa222555"},
							{"jack11","Soe", "username819920@gmail.com","403000001", "aaa222555"}
							
							
		};
		return data;
	}   
	
	@DataProvider(name = "logindata") 
	public Object GetLoginData() 
	{
		
		
		
		Object data[][] =  { 
							{"ValidEmail", "username814@gmail.com", "aaa222555"},
							{"InvalidEmail","username82489@gmail.com", "aaa222555"}
		};
		
			return data;
	}   
}
