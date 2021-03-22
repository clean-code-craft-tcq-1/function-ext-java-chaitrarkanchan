package vitals;

public class ReportGenerator {
	
	
	public static  void getBatteryReport() {
		StringBuilder sb=new StringBuilder();
		
		sb=(BatteryFactorAccumulator.language=="EN")?generateReportEN(sb):generateReportDE(sb);
		BatteryFactorAccumulator.VitalFactorExceedMaxLimit.clear();
		BatteryFactorAccumulator.VitalFactorExceedMinLimit.clear();
		BatteryFactorAccumulator.VitalFactorReachHighBreach.clear();
		BatteryFactorAccumulator.VitalFactorReachLowBreach.clear();
		System.out.println(sb.toString());		
	}

	private static StringBuilder generateReportDE(StringBuilder sb) {
		// TODO Auto-generated method stub
		sb=(BatteryFactorAccumulator.factorviolated)?printSuccessDE(sb):printFailureDE(sb);
		return sb;
	}

	private static StringBuilder generateReportEN(StringBuilder sb) {
		// TODO Auto-generated method stub
		sb=(BatteryFactorAccumulator.factorviolated)?printSuccessEN(sb):printFailureEN(sb);
		return sb;
	}

	private static StringBuilder printFailureEN(StringBuilder sb) {
		sb.append("Generating Over All  Battery Report \n");
		sb.append("One Or more Vital Factors violating the limit...Please take necessary actions!\n");
		sb.append("Vitals Factors limit-->Factor=[Min,Max]:"+BatteryFactorsStateEstimator.dataMap.toString()+"\n");
		sb.append("\n Violates maximum limit-->"+BatteryFactorAccumulator.VitalFactorExceedMaxLimit.toString());
		sb.append("\n Violates minimum limit-->"+BatteryFactorAccumulator.VitalFactorExceedMinLimit.toString());
		sb.append("\n Vitals Factors exceeding High breach limit are--->"+BatteryFactorAccumulator.VitalFactorReachHighBreach.toString());
		sb.append("\n Vitals Factors exceeding Low breach limit are--->"+BatteryFactorAccumulator.VitalFactorReachLowBreach.toString());
		
		return sb;
	}
	
	private static StringBuilder printFailureDE(StringBuilder sb) {
		sb.append("Generieren uber alle Batterieberichte \n");
		sb.append("Ein oder mehrere wichtige Faktoren, die das Limit uberschreiten ... Bitte ergreifen Sie die erforderlichen MaBnahmen!\n");
		sb.append("Vitale Faktoren limit-->Faktoren=[Min,Max]:"+BatteryFactorsStateEstimator.dataMap.toString()+"\n");
		sb.append("\n verletzt maximal-->"+BatteryFactorAccumulator.VitalFactorExceedMaxLimit.toString());
		sb.append("\n verletzt minimal-->"+BatteryFactorAccumulator.VitalFactorExceedMinLimit.toString());
		sb.append("\n Vitale Faktoren, die die untere Grenze erreichen, sind --->"+BatteryFactorAccumulator.VitalFactorReachHighBreach.toString());
		sb.append("\n Vitale Faktoren, die die Obergrenze erreichen, sind--->"+BatteryFactorAccumulator.VitalFactorReachLowBreach.toString());
		return sb;
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
