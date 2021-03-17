package vitals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatteryFactorAccumulator {
	
	public static String language;
	static List<String> lang_types = Arrays.asList("EN", "DE");
	static Map<String,Float> VitalFactorExceedMinLimit=new HashMap<>();
	static Map<String,Float> VitalFactorReachHighBreach=new HashMap<>();
	static Map<String,Float> VitalFactorReachLowBreach=new HashMap<>();
	static Map<String,Float> VitalFactorExceedMaxLimit=new HashMap<>();
	

    public static void showMsg(String msg)
    {
      System.out.println(msg);
    }
    
    public static void displayMaximumAlert(String vitalparam)
    {
    	String alert = (language == "EN")? vitalparam + " is more than its Maximum Limit":"Die " + vitalparam + "ist hoher als die maximale Grenze";
    	showMsg(alert);
    	
    }

    public static void displayMinimumAlert(String vitalparam)
    {
    	String alert = (language == "EN") ? vitalparam + " is less than its Minimum Limit":"Die " + vitalparam + " liegt unter der Hochstgrenze ";
    	showMsg(alert);
    	
    	
    }
    
	public static boolean displayLowBreachWarning(String vitalparam,float val)
    {
		String warning = (language == "EN") ?  vitalparam + " is reaching towards low breach limit":"Die "+ vitalparam +" erreicht ein niedriges VerstoÃŸlimit";
		showMsg(warning);
		VitalFactorReachLowBreach.put(vitalparam,val);
		return true;
    }
    public static boolean displayHighBreachWarning(String vitalparam,float val)
    {
    	String warning = (language == "EN") ?  vitalparam + " is reaching towards high breach limit":"Die " + vitalparam + " erreicht ein niedriges VerstoÃŸlimit" ;
    	showMsg(warning);
    	VitalFactorReachHighBreach.put(vitalparam,val);
    	return true;
    }
   
	public static boolean set_system_language(String lan) {
		if(lang_types.contains(lan)){
	    	language=lan;
	        return true;
	    }
		return false;
	}


	public static void printBreachWarningMsg(Boolean breachStatus,String status,String parameter,float val) {
		
		if(breachStatus){
			breachStatus=(status=="LOW")? displayLowBreachWarning(parameter,val):displayHighBreachWarning(parameter,val);
		}
		
	}

	public static boolean printAlertMsg(boolean parammeasure,String status, String parameter,float val) {
		if(parammeasure==true && status=="High" ){
		VitalFactorExceedMaxLimit.put(parameter,val);
			displayMaximumAlert(parameter);
			return false;
		}
		else if(parammeasure==true && status=="Low" ){
		VitalFactorExceedMinLimit.put(parameter,val);
			displayMinimumAlert(parameter);
			return false;
		}
		return true;
	}
}

