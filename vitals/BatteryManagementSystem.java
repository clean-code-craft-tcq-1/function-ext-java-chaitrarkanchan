package vitals;

public class BatteryManagementSystem {
	static boolean batteryIsOk(float temperature, float soc, float chargeRate) {       
        BatteryFactorsStateEstimator state_estimator = new BatteryFactorsStateEstimator(temperature, soc, chargeRate);
		return state_estimator.isBatteryOk();
    }
	
    public static void main(String[] args) {
    	
    	BatteryFactorsStateEstimator state_estimator=new BatteryFactorsStateEstimator();
    	assert(DisplayMessages.set_system_language("EN")==true);
    	assert(batteryIsOk(25, 70, 0.7f) == true);
        assert(batteryIsOk(-1, -20, 0.7f)==false);
    	assert(batteryIsOk(44, 79, 0.7f)==true);
    	assert(state_estimator.setTemperatureunit("Fahrenheit")==true);
    	assert(batteryIsOk(140, 60, 0.6f) == false);
    	
    }
}
