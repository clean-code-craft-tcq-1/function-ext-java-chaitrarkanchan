package vitals;

import java.util.Arrays;
import java.util.List;

public class DisplayMessages {
	
	public static String language;
	static List<String> lang_types = Arrays.asList("EN", "DE");
	

    public static void showMsg(String msg)
    {
      System.out.println(msg);
    }
    
    public static void displayMaximumAlert(String vitalparam, float MaximumLimit)
    {
    	String alert = (language == "EN")? vitalparam + " is more than its Maximum Limit-" + MaximumLimit:"Die " + vitalparam + "ist hoher als die maximale Grenze" + MaximumLimit;
    	showMsg(alert);
    }

    public static void displayMinimumAlert(String vitalparam, float MinimumLimit)
    {
    	String alert = (language == "EN") ? vitalparam + " is less than its Minimum Limit-" + MinimumLimit:"Die " + vitalparam + " liegt unter der Hochstgrenze " + MinimumLimit;
    	showMsg(alert);
    	
    }
    
	public static boolean displayLowBreachWarning(String vitalparam)
    {
		String warning = (language == "EN") ?  vitalparam + " is reaching towards low breach limit":"Die "+ vitalparam +" erreicht ein niedriges Verstoßlimit";
		showMsg(warning);
		return true;
    }
    public static boolean displayHighBreachWarning(String vitalparam)
    {
    	String warning = (language == "EN") ?  vitalparam + " is reaching towards high breach limit":"Die " + vitalparam + " erreicht ein niedriges Verstoßlimit" ;
    	showMsg(warning);
    	return true;
    }
   
	public static boolean set_system_language(String lan) {
		if(lang_types.contains(lan)){
	    	language=lan;
	        return true;
	    }
		return false;
	}


	public static void printBreachWarningMsg(Boolean breachStatus,String val,String parameter) {
		
		if(breachStatus){
			breachStatus=(val=="LOW")? displayLowBreachWarning(parameter):displayHighBreachWarning(parameter);
		}
		
	}
}
