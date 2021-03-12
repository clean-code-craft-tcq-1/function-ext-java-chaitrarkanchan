package vitals;

public class BatteryFactorValidator {
  
	public static boolean EvaluateBatteryMaxMeasure(String ParamName,float ParamValue,float MaxParamLimit) {
		if(ParamValue>MaxParamLimit){
			DisplayMessages.displayMaximumAlert(ParamName,MaxParamLimit);
			return false;
		}
        return true;
	}
	
	public static boolean EvaluateBatteryMinMeasure(String ParamName,float ParamValue,float MinParamLimit) {
	if (ParamValue < MinParamLimit){
    	DisplayMessages.displayMinimumAlert(ParamName,MinParamLimit);
    	return false;
    }
	return true;
	}
	
	
    public static boolean CheckLBreach(String ParamName,float ParamValue,float MinParamLimit,float MaxParamLimit)
    {
    	return((ParamValue >(MinParamLimit+ (MinParamLimit* 0.05f)))&& (ParamValue<(MinParamLimit +MaxParamLimit*0.05f)));
    }    
    
    public static boolean CheckHBreach(String ParamName,float ParamValue,float MinParamLimit,float MaxParamLimit)
    {
         return((ParamValue > (MaxParamLimit- (MaxParamLimit*0.05f)))&& (ParamValue<MaxParamLimit));
         
        
    }

}
