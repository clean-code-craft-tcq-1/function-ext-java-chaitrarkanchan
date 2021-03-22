package vitals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatteryFactorAccumulator {  //store and print data to the console
	
	public static String language;
	static List<String> lang_types = Arrays.asList("EN", "DE");
	static Map<String,Float> VitalFactorExceedMinLimit=new HashMap<>();
	static Map<String,Float> VitalFactorReachHighBreach=new HashMap<>();
	static Map<String,Float> VitalFactorReachLowBreach=new HashMap<>();
	static Map<String,Float> VitalFactorExceedMaxLimit=new HashMap<>();
	static boolean factorviolated=true; //set to false if one or more battery factors violates the limit

    public static void showMsg(String msg)
    {
      System.out.println(msg);
    }
    
    public static void checkAndPrintMaximumAlert(String vitalparam)
    {
    	String alert = (language == "EN")? vitalparam + " is more than its Maximum Limit":"Die " + vitalparam + "ist hoher als die maximale Grenze";
    	showMsg(alert);
    	
    }

    public static void checkAndPrintMinimumAlert(String vitalparam)
    {
    	String alert = (language == "EN") ? vitalparam + " is less than its Minimum Limit":"Die " + vitalparam + " liegt unter der Hochstgrenze ";
    	showMsg(alert);
    	
    	
    }
    
	public static boolean checkAndPrintLowBreach(String vitalparam,float val)
    {
		String warning = (language == "EN") ?  vitalparam + " is reaching towards low breach limit":"Die "+ vitalparam +" erreicht ein niedriges VerstoÃƒÅ¸limit";
		showMsg(warning);
		factorviolated=false;
		VitalFactorReachLowBreach.put(vitalparam,val);
		return true;
    }
    public static boolean checkAndPrintHighBreach(String vitalparam,float val)
    {
    	String warning = (language == "EN") ?  vitalparam + " is reaching towards high breach limit":"Die " + vitalparam + " erreicht ein niedriges VerstoÃƒÅ¸limit" ;
    	showMsg(warning);
    	factorviolated=false;
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


	public static void checkAndprintBreachMsg(Boolean breachStatus,String status,String parameter,float val) {
		
		if(breachStatus){
			breachStatus=(status=="LOW")? checkAndPrintLowBreach(parameter,val):checkAndPrintHighBreach(parameter,val);
		}
		
	}

	public static boolean testAndStoreHighfactors(boolean parammeasure,String status, String parameter,float val) {
		if(parammeasure==true && status=="High" ){
			factorviolated=false;
			VitalFactorExceedMaxLimit.put(parameter,val);
			checkAndPrintMaximumAlert(parameter);
			return false;
		}
		
		return true;
	}
	public static boolean testAndStoreLowfactors(boolean parammeasure,String status, String parameter,float val) {
		if(parammeasure==true && status=="Low" ){
			VitalFactorExceedMinLimit.put(parameter,val);
			factorviolated=false;
			checkAndPrintMinimumAlert(parameter);
			return false;
		}
		return true;
	}
}


