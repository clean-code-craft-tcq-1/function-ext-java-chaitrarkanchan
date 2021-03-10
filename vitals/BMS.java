package vitals;

public class BMS {
	static boolean batteryIsOk(float temperature, float soc, float chargeRate) {       
        StateEstimator state_estimator = new StateEstimator(new BatteryMeasures(temperature, soc, chargeRate));
		if(state_estimator.isBatteryOk()){
			DisplayMessages.PrintMessage("All Param Ok!");
			return true;
		}
			return false;
    }
	
    public static void main(String[] args) {
    	
    	StateEstimator state_estimator=new StateEstimator();
    	assert(DisplayMessages.set_system_language("EN")==true);
    	assert(batteryIsOk(25, 70, 0.7f) == true);
        assert(batteryIsOk(-1, -20, 0.7f)==false);
    	assert(batteryIsOk(44, 79, 0.7f)==true);
    	assert(state_estimator.setTemperatureunit("Fahrenheit")==true);
    	assert(batteryIsOk(140, 60, 0.6f) == true);
    	
    }
}
