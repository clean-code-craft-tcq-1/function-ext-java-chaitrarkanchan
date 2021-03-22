package vitals;
public class BatteryFactorValidator {
  
	public static boolean evaluateBatteryMaxMeasure(String ParamName,float ParamValue,float MaxParamLimit) {
		
        return ParamValue>MaxParamLimit;
	}
	
	public static boolean evaluateBatteryMinMeasure(String ParamName,float ParamValue,float MinParamLimit) {
		return ParamValue < MinParamLimit;
	}
	
	
    public static boolean checkLowBreach(String ParamName,float ParamValue,float MinParamLimit,float MaxParamLimit)
    {
    	return((ParamValue >(MinParamLimit+ (MinParamLimit* 0.05f)))&& (ParamValue<(MinParamLimit +MaxParamLimit*0.05f))); //check against computed tolerance
    }    
    
    public static boolean checkHighBreach(String ParamName,float ParamValue,float MinParamLimit,float MaxParamLimit)
    {
         return((ParamValue > (MaxParamLimit- (MaxParamLimit*0.05f)))&& (ParamValue<MaxParamLimit));//check against computed tolerance
         
        
    }
}
