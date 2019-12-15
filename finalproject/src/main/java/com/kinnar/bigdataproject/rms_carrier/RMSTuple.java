package com.kinnar.bigdataproject.rms_carrier;

import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class RMSTuple implements Writable {

	private int arrivalDelay = 0;
	private int departureDelay = 0;
	private int totalFlights = 0;
	private double rmsVal = 0.0;

	public int getArrivalDelay() {
		return arrivalDelay;
	}

	public void setArrivalDelay(int arrivalDelay) {
		this.arrivalDelay = arrivalDelay;
	}

	public int getDepartureDelay() {
		return departureDelay;
	}

	public void setDepartureDelay(int departureDelay) {
		this.departureDelay = departureDelay;
	}

	public int getTotalFlights() {
		return totalFlights;
	}

	public void setTotalFlights(int totalFlights) {
		this.totalFlights = totalFlights;
	}

	public double getRmsVal() {
		return rmsVal;
	}

	public void setRmsVal(double rmsVal) {
		this.rmsVal = rmsVal;
	}

	// Returning just rms value for recommendation system. If just to show the
	// result use the below return
//    @Override
//    public String toString() {
//        return "{" +
//                "arrivalDelay=" + arrivalDelay +
//                ", departureDelay=" + departureDelay +
//                ", totalFlights=" + totalFlights +
//                ", rmsVal=" +  String.format("%.4f", rmsVal)+
//                '}';
//    }

	@Override
	public String toString() {
		return String.format("%.4f", rmsVal);
	}

	@Override
	public void write(DataOutput dataOut) throws IOException {
		dataOut.writeInt(arrivalDelay);
		dataOut.writeInt(departureDelay);
		dataOut.writeInt(totalFlights);
		dataOut.writeDouble(rmsVal);

	}

	@Override
	public void readFields(DataInput dataIn) throws IOException {

		arrivalDelay = dataIn.readInt();
		departureDelay = dataIn.readInt();
		totalFlights = dataIn.readInt();
		rmsVal = dataIn.readDouble();

	}
}
