package com.kinnar.bigdataproject.avg_dist_carrier;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AvgDisTimeTuple implements Writable {

	private int totalFlights = 0;
	private int totalDistance = 0;
	private int totalAirtime = 0;
	private double avgDistance = 0.0;
	private double avgAirTime = 0.0;

	

	public int getTotalFlights() {
		return totalFlights;
	}

	public void setTotalFlights(int totalFlights) {
		this.totalFlights = totalFlights;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public int getTotalAirtime() {
		return totalAirtime;
	}

	public void setTotalAirtime(int totalAirtime) {
		this.totalAirtime = totalAirtime;
	}

	public double getAvgDistance() {
		return avgDistance;
	}

	public void setAvgDistance(double avgDistance) {
		this.avgDistance = avgDistance;
	}

	public double getAvgAirTime() {
		return avgAirTime;
	}

	public void setAvgAirTime(double avgAirTime) {
		this.avgAirTime = avgAirTime;
	}

	@Override
	public String toString() {
		return "AvgDisTimeTuple{" + "TotalFlights=" + totalFlights + ", TotalDistance=" + totalDistance
				+ ", TotalAirTime=" + totalAirtime + ", AverageDistance=" + String.format("%.2f", avgDistance)
				+ ", AverageAirTime=" + String.format("%.2f", avgAirTime) + '}';
	}

	@Override
	public void write(DataOutput dataOut) throws IOException {
		dataOut.writeInt(totalFlights);
		dataOut.writeInt(totalDistance);
		dataOut.writeInt(totalAirtime);
		dataOut.writeDouble(avgDistance);
		dataOut.writeDouble(avgAirTime);

	}

	@Override
	public void readFields(DataInput dataIn) throws IOException {

		totalFlights = dataIn.readInt();
		totalDistance = dataIn.readInt();
		totalAirtime = dataIn.readInt();
		avgDistance = dataIn.readDouble();
		avgAirTime = dataIn.readDouble();

	}
}
