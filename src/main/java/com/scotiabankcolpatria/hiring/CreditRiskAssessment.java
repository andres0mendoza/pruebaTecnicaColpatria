package com.scotiabankcolpatria.hiring;


import java.util.ArrayList;

public class CreditRiskAssessment {

    /**
     * Variable estaticas definidas para contener mensajes que se imprime por consola
     * en metodo paymentDelayMaxPeakIndex para mostrar la posición y el valor de la
     * anomalía con la posición del pico más alto
     */
    private static final String HIGHEST_PEAK_ANOMALY = "La anomalia con el pico mas alto se encuentra en la posicion ";
    private static final String ANOMALY_VALUE = "con el valor de ";

    /**
     *
     * @param paymentDelays
     * @return desviacion estandar
     * Se define el numero de posiciones que contiene la lista recibida como parametro, se realiza la suma de los
     * valores conteidos en la lista, posteriormente con el resultado de la suma anterior se calcula la media,
     * finalmente se calcula la desviacion estandar, iterando atravez del bucle y utilizando metodos matematicos como
     * Math.pow (retorna la base elevada al exponente) y Math.sqrt (calcula la raíz cuadrada de un número)
     */
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

    /**
     *
     * @param paymentDelays
     * @return Posicion de la anomalia con el pico mas alto
     * En este metodo se obtiene la pocision de la anomalia con el pico mas alto, se utiliza un bucle para recorrer
     * la lista y guardar el valor de la anomalia y la posicion, posteriormente se imprime en consola la posición
     * de la anomalia y el valor de la misma
     */
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

    /**
     *
     * @param paymentDelays
     * @return Probabilidad de pago tardío
     * En este metodo se obtiene la probabilidad de pagos tardios, antes de comenzar se definen variables necesarias
     * para realizar el calculo tales como, cantidad de productos y periodos de pago, posteriormente se recorre los
     * periodos de pago y la cantidad de productos para guardar los valores correspondientes en una lista temporal
     * para asi manipularlos con mayor facilidad, luego se operan los valores de la lista temporal y por ultimo
     * se agraga el resultado a la lista de probabilidad de pago tardio
     */
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
