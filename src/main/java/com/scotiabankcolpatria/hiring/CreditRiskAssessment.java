package com.scotiabankcolpatria.hiring;


import java.util.ArrayList;

public class CreditRiskAssessment {

    private static final String HIGHEST_PEAK_ANOMALY = "La anomalia con el pico mas alto se encuentra en la posicion ";
    private static final String ANOMALY_VALUE = "con el valor de ";


    public double standardDeviation(int[] paymentDelays) {
        double suma = 0, standardDeviation = 0, mean = 0;
        int numberPositions = paymentDelays.length;

        for (int i = 0; i < numberPositions; i++) {
            System.out.println(paymentDelays[i]);
        }

        for (int i = 0; i < numberPositions; i++) {
            suma = suma + paymentDelays[i];
        }

        mean = suma / (numberPositions);

        for (int i = 0; i < numberPositions; i++) {
            standardDeviation = standardDeviation + Math.pow((paymentDelays[i] - mean), 2);
        }

        return Math.sqrt(standardDeviation / numberPositions);
    }

    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
        int anomalyWithHighestPeak = -1;
        int anomalyWithHighestPeakPosition = -1;

        for (int i = 0; i < paymentDelays.length; i++) {
            if(anomalyWithHighestPeak <= paymentDelays[i]){
                anomalyWithHighestPeak = paymentDelays[i];
                anomalyWithHighestPeakPosition = i;
            }
        }
        System.out.println(HIGHEST_PEAK_ANOMALY + anomalyWithHighestPeakPosition
                + ANOMALY_VALUE + anomalyWithHighestPeak);
        return anomalyWithHighestPeakPosition;
    }

    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        int quantityProducts = paymentDelays.length;
        int payPeriods = paymentDelays[0].length;
        double[] latePaymentProbability = new double[payPeriods];
        double latePaymentProbabilityValue;

        for (int i = 0; i < payPeriods; i++) {
            ArrayList<Integer> temporaryListOfValue = new ArrayList<Integer>();
            for (int j = 0; j < quantityProducts; j++) {
                int tempo = paymentDelays[j][i];
                temporaryListOfValue.add(tempo);
            }

            int arrearsCounter = 0;
            for (int k = 0; k < temporaryListOfValue.size(); k++) {
                if(temporaryListOfValue.get(k) > 0) {
                    arrearsCounter++;
                }
            }
            latePaymentProbabilityValue = (double)arrearsCounter/temporaryListOfValue.size();
            latePaymentProbability[i] = latePaymentProbabilityValue;
        }
        return latePaymentProbability;
    }
}
