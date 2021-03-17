package vitals;

import java.util.Map;

public class ReportGenerator {
	
	
	public static  void getBatteryReport() {
		StringBuilder sb=new StringBuilder();
		boolean factorViolation=BatteryFactorAccumulator.VitalFactorExceedMaxLimit.isEmpty()&&BatteryFactorAccumulator.VitalFactorExceedMinLimit.isEmpty()&&BatteryFactorAccumulator.VitalFactorReachHighBreach.isEmpty()&&BatteryFactorAccumulator.VitalFactorReachLowBreach.isEmpty();
		sb=(BatteryFactorAccumulator.language=="EN")?GenerateReportEN(factorViolation,sb):GenerateReportDE(factorViolation,sb);
		BatteryFactorAccumulator.VitalFactorExceedMaxLimit.clear();
		BatteryFactorAccumulator.VitalFactorExceedMinLimit.clear();
		BatteryFactorAccumulator.VitalFactorReachHighBreach.clear();
		BatteryFactorAccumulator.VitalFactorReachLowBreach.clear();
		System.out.println(sb.toString());		
	}

	private static StringBuilder GenerateReportDE(boolean factorViolation, StringBuilder sb) {
		// TODO Auto-generated method stub
		sb=(factorViolation)?printSuccessDE(sb):printFailureDE(sb);
		return sb;
	}

	private static StringBuilder GenerateReportEN(boolean factorViolation, StringBuilder sb) {
		// TODO Auto-generated method stub
		sb=(factorViolation)?printSuccessEN(sb):printFailureEN(sb);
		return sb;
	}

	private static StringBuilder printFailureEN(StringBuilder sb) {
		sb.append("Generating Over All  Battery Report \n");
		sb.append("One Or more Vital Factors violating the limit...Please take necessary actions!\n");
		sb.append("Vitals Factors limit-->Factor=[Min,Max]:"+BatteryFactorsStateEstimator.dataMap.toString()+"\n");
		sb.append(getALertMaxParameters(BatteryFactorAccumulator.VitalFactorExceedMaxLimit));
		sb.append(getAlertMinParameters(BatteryFactorAccumulator.VitalFactorExceedMinLimit));
		sb.append("Vitals Factors exceeding High breach limit are:\n");
		sb.append(getParamHighBreachWarning(BatteryFactorAccumulator.VitalFactorReachHighBreach));
		sb.append("Vitals Factors exceeding Low breach limit are:\n");
		sb.append(getParamLowBreachWarning(BatteryFactorAccumulator.VitalFactorReachLowBreach));
		
		return sb;
	}
	
	private static StringBuilder printFailureDE(StringBuilder sb) {
		sb.append("Generieren uber alle Batterieberichte \n");
		sb.append("Ein oder mehrere wichtige Faktoren, die das Limit uberschreiten ... Bitte ergreifen Sie die erforderlichen MaBnahmen!\n");
		sb.append("Vitale Faktoren limit-->Faktoren=[Min,Max]:"+BatteryFactorsStateEstimator.dataMap.toString()+"\n");
		sb.append(getALertMaxParameters(BatteryFactorAccumulator.VitalFactorExceedMaxLimit));
		sb.append(getAlertMinParameters(BatteryFactorAccumulator.VitalFactorExceedMinLimit));
		sb.append("Vitale Faktoren, die die untere Grenze erreichen, sind:\n");
		sb.append(getParamHighBreachWarning(BatteryFactorAccumulator.VitalFactorReachHighBreach));
		sb.append("Vitale Faktoren, die die Obergrenze erreichen, sind:\n");
		sb.append(getParamLowBreachWarning(BatteryFactorAccumulator.VitalFactorReachLowBreach));
		
		return sb;
	}

	private static String getParamLowBreachWarning(Map<String, Float> vitalFactorReachLowBreach) {
		if(!vitalFactorReachLowBreach.isEmpty()){
			return vitalFactorReachLowBreach.toString()+"\n";
		}
		return "None \n";
		}

	private static String getParamHighBreachWarning(
			Map<String, Float> vitalFactorReachHighBreach) {
		if(!vitalFactorReachHighBreach.isEmpty()){
			return vitalFactorReachHighBreach.toString()+"\n";
		}
		return "None \n";
	}

	private static String getAlertMinParameters(
			Map<String, Float> vitalFactorExceedMinLimit) {
		if(!vitalFactorExceedMinLimit.isEmpty()){
			return vitalFactorExceedMinLimit.toString()+"\n";
		}
		return "Factors Listed crossing Min Limit->None \n";
		
	}

	private static String getALertMaxParameters(
			Map<String, Float> vitalFactorExceedMaxLimit) {
		if(!vitalFactorExceedMaxLimit.isEmpty()){
			return vitalFactorExceedMaxLimit.toString()+"\n";
		}
		return "Factors Listed crossing Max Limit->None \n";
	}

	private static StringBuilder printSuccessEN(StringBuilder sb) {
		sb.append("Generating Over All  Battery Report \n");
		sb.append("All the vital parameters are within Range.Overall Battery Health is Good!\n");
		return sb;
	}

	private static StringBuilder printSuccessDE(StringBuilder sb) {
		sb.append("Generieren uber alle Batterieberichte \n");
		sb.append("Alle wichtigen Parameter liegen innerhalb des Bereichs. Insgesamt ist der Batteriezustand gut!\n");
		return sb;
	}
	
	
}
