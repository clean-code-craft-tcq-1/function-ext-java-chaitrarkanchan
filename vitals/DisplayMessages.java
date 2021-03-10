package vitals;

import java.util.Arrays;
import java.util.List;

public class DisplayMessages {
	
	public static String language;
	static List<String> lang_types = Arrays.asList("EN", "DE");
	
	public static void PrintLowBreachMessage(String Measure)
    {
		String Message = (language == "DE") ? "Die "+ Measure +" lauft in Richtung ihrer unteren Bruchgrenze" : Measure + " is near low breach limit";
       PrintMessage(Message);
    }
    public static void PrintHighBreachMessage(String Measure)
    {
    	String Message = (language == "DE") ? "Die " + Measure + " lauft in Richtung ihrer oberen Bruchgrenze" : Measure + " is near high breach limit";
        PrintMessage(Message);
    }

    public static void PrintMaximumLimitMessage(String Measure, float MaximumLimit)
    {
    	String Message = (language == "DE") ? "Die " + Measure + " hat seine Hochstgrenze von " + MaximumLimit + " uberschritten" : Measure + " is more than its Maximum Limit of " + MaximumLimit;
        PrintMessage(Message);
    }

    public static void PrintMinimumLimitMessage(String Measure, float MinimumLimit)
    {
    	String Message = (language == "DE") ? "Die " + Measure + " ist unter seine Mindestgrenze von " + MinimumLimit + " gefallen" : Measure + " is less than its Minimum Limit of " + MinimumLimit;
        PrintMessage(Message);
    }

    public static void PrintMessage(String Message)
    {
      System.out.println(Message);
    }
    
	public static boolean set_system_language(String lan) {
		if(lang_types.contains(lan)){
	    	language=lan;
	        return true;
	    }
		return false;
	}
}
