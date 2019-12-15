/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kinnar.bigdataproject.recommendation_sys;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompositeKeyMr implements WritableComparable<CompositeKeyMr> {

	private String sourceDestination;
	private String carrierInformation;

	public CompositeKeyMr() {
		super();
	}

	public String getSourceDestination() {
		return sourceDestination;
	}

	public void setSourceDestination(String sourceDestination) {
		this.sourceDestination = sourceDestination;
	}

	public String getCarrierInformation() {
		return carrierInformation;
	}

	public void setCarrierInformation(String carrierInformation) {
		this.carrierInformation = carrierInformation;
	}

	public CompositeKeyMr(String sourceDestination, String carrierInformation) {
		this.sourceDestination = sourceDestination;
		this.carrierInformation = carrierInformation;
	}

	@Override
	public void write(DataOutput dataOur) throws IOException {
		dataOur.writeUTF(sourceDestination);
		dataOur.writeUTF(carrierInformation);
	}

	@Override
	public void readFields(DataInput dataIn) throws IOException {
		sourceDestination = dataIn.readUTF();
		carrierInformation = dataIn.readUTF();
	}

	@Override
	public int compareTo(CompositeKeyMr ckmr) {
		int result = this.sourceDestination.compareTo(ckmr.getSourceDestination());
		if (result == 0) {
			String carrier1 = this.carrierInformation;
			Double rmsVal1 = Double.parseDouble(carrier1.split("\t")[1]);

			String carrier2 = ckmr.getCarrierInformation();
			Double rmsVal2 = Double.parseDouble(carrier2.split("\t")[1]);
			return rmsVal1.compareTo(rmsVal2);
		}

		return result;
	}

	@Override
	public String toString() {
		return sourceDestination + " : " + carrierInformation;
	}
}
