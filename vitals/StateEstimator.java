package vitals;

import java.util.Arrays;
import java.util.List;

public class StateEstimator {
	
	BatteryMeasures bms;
	public static String temperature_measure="Celsius";
	static List<String> temperature_unit = Arrays.asList("Celsius", "Fahrenheit");
	static final float MAX_TEMPERATURE = 45;
	static final float MIN_TEMPERATURE = 0;
	static final float MAX_SOC= 80;
	static final float MIN_SOC= 20;
	static final float MAX_CHARGE_RATE= 0.8f;
	static final float MIN_CHARGE_RATE= 0.0f;
	
	public StateEstimator(BatteryMeasures batteryMeasures) {
		this.bms=batteryMeasures;
	}

	public StateEstimator() {
		// TODO Auto-generated constructor stub
	}

	public boolean isBatteryOk() {
		boolean status_of_temp=isTemperatureWithinRange(bms.Temperature);
		boolean status_of_soc=isSocWithinRange(bms.StateOfCharge);
		boolean status_of_charge=isChargeWithinRange(bms.ChargeRate);
		return ((status_of_temp && status_of_soc) && status_of_charge);
	}
	
	public boolean isTemperatureWithinRange(float temperature) {
		
		if((temperature_measure).equals(temperature_unit.get(1))){
			temperature=convert_F_to_C(temperature);
		}
		BatteryStateMeasureFactors btemp=new BatteryStateMeasureFactors("Temperature", temperature, MAX_TEMPERATURE, MIN_TEMPERATURE);
		BatteryMeasures.CheckLowBreach(btemp);
		BatteryMeasures.CheckHighBreach(btemp);
		
		return BatteryMeasures.EvaluateBatteryMeasure(btemp);
		
	}
	private float convert_F_to_C(float temperature) {
		float Cel_temp=((temperature - 32)*5)/9;
		
		System.out.println(Cel_temp);
		return Cel_temp;
	}

	public boolean isSocWithinRange(float stateofcharge) {
		BatteryStateMeasureFactors bsoc=new BatteryStateMeasureFactors("StateofCharge", stateofcharge, MAX_SOC,MIN_SOC);
		BatteryMeasures.CheckLowBreach(bsoc);
		BatteryMeasures.CheckHighBreach(bsoc);
		
		return BatteryMeasures.EvaluateBatteryMeasure(bsoc);
	}
	
	public boolean isChargeWithinRange(float chargeRate) {
		BatteryStateMeasureFactors bcharge=new BatteryStateMeasureFactors("ChargeRate", chargeRate,MAX_CHARGE_RATE,MIN_CHARGE_RATE);
		BatteryMeasures.CheckLowBreach(bcharge);
		BatteryMeasures.CheckHighBreach(bcharge);
		
		return BatteryMeasures.EvaluateBatteryMeasure(bcharge);
	}

	public boolean setTemperatureunit(String temp_unit) {
		if(temperature_unit.contains(temp_unit)){
			temperature_measure = temp_unit;
	        return true;
		}
	    else
	        return false;
		
	}

}