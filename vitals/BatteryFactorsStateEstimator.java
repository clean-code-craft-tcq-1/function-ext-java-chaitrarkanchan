package vitals;

import java.util.Arrays;
import java.util.List;

public class BatteryFactorsStateEstimator {
	
	BatteryFactorValidator bms;
	public static String temperature_measure="Celsius";
	static List<String> temperature_measure_type = Arrays.asList("Celsius", "Fahrenheit");
	public float Temperature, StateOfCharge, ChargeRate;
	
	static final float MAX_TEMPERATURE = 45;
	static final float MIN_TEMPERATURE = 0;
	static final float MAX_SOC= 80;
	static final float MIN_SOC= 20;
	static final float MAX_CHARGE_RATE= 0.8f;
	static final float MIN_CHARGE_RATE= 0.0f;
	
	 
    public BatteryFactorsStateEstimator(float temperature, float soc, float chargeRate)
    {
        this.Temperature = temperature;
        this.StateOfCharge = soc;
        this.ChargeRate = chargeRate;
    }
    
	public BatteryFactorsStateEstimator() {
		// TODO Auto-generated constructor stub
	}

	public boolean isBatteryOk() {
		boolean status_of_temp=isTemperatureWithinRange(this.Temperature);//to evaluate each parameter status and display warning & alert message
		boolean status_of_soc=isSocWithinRange(this.StateOfCharge);
		boolean status_of_charge=isChargeWithinRange(this.ChargeRate);
		return ((status_of_temp && status_of_soc) && status_of_charge);
	}
	
	public boolean isTemperatureWithinRange(float temperature) {
		
		if((temperature_measure).equals(temperature_measure_type.get(1))){
			temperature=convert_F_to_C(temperature);
		}
		DisplayMessages.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("Temperature", temperature, MIN_TEMPERATURE, MAX_TEMPERATURE),"Low","Temperature");
		DisplayMessages.printBreachWarningMsg(BatteryFactorValidator.CheckHBreach("Temperature", temperature, MIN_TEMPERATURE, MAX_TEMPERATURE),"High","Temperature");
		return (BatteryFactorValidator.EvaluateBatteryMaxMeasure("Temperature", temperature, MAX_TEMPERATURE) && BatteryFactorValidator.EvaluateBatteryMinMeasure("Temperature", temperature, MIN_TEMPERATURE));
		
	}
	private float convert_F_to_C(float temperature) {
		float Cel_temp=((temperature - 32)*5)/9;		
		System.out.println(Cel_temp);
		return Cel_temp;
	}

	public boolean isSocWithinRange(float stateofcharge) {
		DisplayMessages.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("StateofCharge", stateofcharge, MIN_SOC,MAX_SOC),"Low","StateofCharge");
		DisplayMessages.printBreachWarningMsg(BatteryFactorValidator.CheckHBreach("StateofCharge", stateofcharge, MIN_SOC,MAX_SOC),"High","StateofCharge");
		return (BatteryFactorValidator.EvaluateBatteryMaxMeasure("StateofCharge", stateofcharge, MAX_SOC)&&BatteryFactorValidator.EvaluateBatteryMinMeasure("StateofCharge", stateofcharge,MIN_SOC));
	}
	
	public boolean isChargeWithinRange(float chargeRate) {
		DisplayMessages.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("ChargeRate", chargeRate,MIN_CHARGE_RATE,MAX_CHARGE_RATE),"Low","ChargeRate");
		DisplayMessages.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("ChargeRate", chargeRate,MIN_CHARGE_RATE,MAX_CHARGE_RATE),"High","ChargeRate");
		return (BatteryFactorValidator.EvaluateBatteryMaxMeasure("ChargeRate", chargeRate,MAX_CHARGE_RATE));
	}

	public boolean setTemperatureunit(String temp_unit) {
		if(temperature_measure_type.contains(temp_unit)){
			temperature_measure = temp_unit;
	        return true;
		}
	        return false;
		
	}

}
