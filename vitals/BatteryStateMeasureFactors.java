package vitals;

public class BatteryStateMeasureFactors {
	
	public float MeasureValue, MaximumLimit, MinimumLimit, LowBreach, HighBreach;
    public String MeasureName, MessageLanguage;
     public BatteryStateMeasureFactors(String Name, float Value, float MaximumValue, float MinimumValue)
     {
         this.MeasureName = Name;
         this.MeasureValue = Value;
         this.MaximumLimit = MaximumValue;
         this.MinimumLimit = MinimumValue;
         this.LowBreach = (MinimumValue * 0.05f);
         this.HighBreach = (MaximumValue * 0.05f);
     }
	
 }

