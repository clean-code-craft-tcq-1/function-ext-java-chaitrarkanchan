package vitals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatteryFactorsStateEstimator {
	
	BatteryFactorValidator bms;
	public static String temperature_measure="Celsius";
	static List<String> temperature_measure_type = Arrays.asList("Celsius", "Fahrenheit");
	public float Temperature, StateOfCharge, ChargeRate;
	static List<Float> Temperature_Limit = Arrays.asList(0f,45f);
	static List<Float> Soc_Limit = Arrays.asList(20f,80f);
	static List<Float> ChargeRate_Limit = Arrays.asList(0f,0.8f);
	static Map<String, List<Float>> dataMap = new HashMap<String, List<Float>>();
	ReportLogger rl=new ReportLogger();
	 
    public BatteryFactorsStateEstimator(float temperature, float soc, float chargeRate)
    {
        this.Temperature = temperature;
        this.StateOfCharge = soc;
        this.ChargeRate = chargeRate;
    }
    
	public BatteryFactorsStateEstimator() {
		// TODO Auto-generated constructor stub
		dataMap.put("Temperature",Temperature_Limit);
		dataMap.put("StateofCharge",Soc_Limit);
		dataMap.put("ChargeRate",ChargeRate_Limit);
	}

	public boolean isBatteryOk() {
		boolean status_of_temp=isTemperatureWithinRange(this.Temperature);//to evaluate each parameter status and display warning & alert message
		boolean status_of_soc=isSocWithinRange(this.StateOfCharge);
		boolean status_of_charge=isChargeWithinRange(this.ChargeRate);
		rl.getReport();
		return ((status_of_temp && status_of_soc) && status_of_charge);
	}
	
	public boolean isTemperatureWithinRange(float temperature) {
		
		if((temperature_measure).equals(temperature_measure_type.get(1))){
			temperature=convert_F_to_C(temperature);
		}
		BatteryFactorAccumulator.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("Temperature", temperature, BatteryFactorsStateEstimator.Temperature_Limit.get(0), BatteryFactorsStateEstimator.Temperature_Limit.get(1)),"Low","Temperature",temperature);
		BatteryFactorAccumulator.printBreachWarningMsg(BatteryFactorValidator.CheckHBreach("Temperature", temperature, BatteryFactorsStateEstimator.Temperature_Limit.get(0), BatteryFactorsStateEstimator.Temperature_Limit.get(1)),"High","Temperature",temperature);
		boolean isTempMax=BatteryFactorAccumulator.printAlertHighMsg(BatteryFactorValidator.EvaluateBatteryMaxMeasure("Temperature", temperature, BatteryFactorsStateEstimator.Temperature_Limit.get(1)),"High","Temperature",temperature);
		boolean isTempMin=BatteryFactorAccumulator.printAlertLowMsg(BatteryFactorValidator.EvaluateBatteryMinMeasure("Temperature", temperature, BatteryFactorsStateEstimator.Temperature_Limit.get(0)),"Low","Temperature",temperature);
		return (isTempMax && isTempMin);
		
	}
	private float convert_F_to_C(float temperature) {
		float Cel_temp=((temperature - 32)*5)/9;		
		System.out.println(Cel_temp);
		return Cel_temp;
	}

	public boolean isSocWithinRange(float stateofcharge) {
		BatteryFactorAccumulator.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("StateofCharge", stateofcharge, BatteryFactorsStateEstimator.Soc_Limit.get(0),BatteryFactorsStateEstimator.Soc_Limit.get(1)),"Low","StateofCharge",stateofcharge);
		BatteryFactorAccumulator.printBreachWarningMsg(BatteryFactorValidator.CheckHBreach("StateofCharge", stateofcharge, BatteryFactorsStateEstimator.Soc_Limit.get(0),BatteryFactorsStateEstimator.Soc_Limit.get(1)),"High","StateofCharge",stateofcharge);
		boolean isSocMax=BatteryFactorAccumulator.printAlertHighMsg(BatteryFactorValidator.EvaluateBatteryMaxMeasure("StateofCharge", stateofcharge, BatteryFactorsStateEstimator.Soc_Limit.get(1)),"High","StateofCharge",stateofcharge);
		boolean isSocMin=BatteryFactorAccumulator.printAlertLowMsg(BatteryFactorValidator.EvaluateBatteryMinMeasure("StateofCharge", stateofcharge, BatteryFactorsStateEstimator.Soc_Limit.get(0)),"Low","StateofCharge",stateofcharge);
		return (isSocMax && isSocMin);
		
	}
	
	public boolean isChargeWithinRange(float chargeRate) {
		BatteryFactorAccumulator.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("ChargeRate", chargeRate,BatteryFactorsStateEstimator.ChargeRate_Limit.get(0),BatteryFactorsStateEstimator.ChargeRate_Limit.get(1)),"Low","ChargeRate",chargeRate);
		BatteryFactorAccumulator.printBreachWarningMsg(BatteryFactorValidator.CheckLBreach("ChargeRate", chargeRate,BatteryFactorsStateEstimator.ChargeRate_Limit.get(0),BatteryFactorsStateEstimator.ChargeRate_Limit.get(1)),"High","ChargeRate",chargeRate);
		boolean isChargeRateMax=BatteryFactorAccumulator.printAlertHighMsg(BatteryFactorValidator.EvaluateBatteryMaxMeasure("ChargeRate", chargeRate, BatteryFactorsStateEstimator.ChargeRate_Limit.get(1)),"High","ChargeRate",chargeRate);
		return (isChargeRateMax);
	}

	public boolean setTemperatureunit(String temp_unit) {
		if(temperature_measure_type.contains(temp_unit)){
			temperature_measure = temp_unit;
	        return true;
		}
	        return false;
		
	}

}
