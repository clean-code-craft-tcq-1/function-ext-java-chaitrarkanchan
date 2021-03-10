package vitals;

public class BatteryMeasures {
	public float Temperature, StateOfCharge, ChargeRate;
	 
    public BatteryMeasures(float temperature, float soc, float chargeRate)
    {
        this.Temperature = temperature;
        this.StateOfCharge = soc;
        this.ChargeRate = chargeRate;
    }
    
    
    public static void CheckLowBreach(BatteryStateMeasureFactors battery)
    {
        if ((battery.MeasureValue > (battery.MinimumLimit + battery.LowBreach)) && (battery.MeasureValue<(battery.MinimumLimit + battery.HighBreach))){
        DisplayMessages.PrintLowBreachMessage(battery.MeasureName);
        }
    }    
    
    public static void CheckHighBreach(BatteryStateMeasureFactors battery)
    {
         if (((battery.MeasureValue > battery.MaximumLimit - battery.HighBreach)) && (battery.MeasureValue<battery.MaximumLimit)){
         DisplayMessages.PrintHighBreachMessage(battery.MeasureName);
         }
    }


	public static boolean EvaluateBatteryMeasure(BatteryStateMeasureFactors battery) {
		if(battery.MeasureValue > battery.MaximumLimit){
			DisplayMessages.PrintMaximumLimitMessage(battery.MeasureName, battery.MaximumLimit);
            return false;
		}
        if (battery.MeasureValue < battery.MinimumLimit){
        	DisplayMessages.PrintMinimumLimitMessage(battery.MeasureName, battery.MinimumLimit);
        	return false;
        }
        return true;
	}
}
