/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kinnar.bigdataproject.recommendation_sys;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortComparator extends WritableComparator {

	protected SecondarySortComparator() {
		super(CompositeKeyMr.class, true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable aa, WritableComparable bb) {

		CompositeKeyMr ckmr1 = (CompositeKeyMr) aa;
		CompositeKeyMr ckmr2 = (CompositeKeyMr) bb;

		int result = ckmr1.getSourceDestination().compareTo(ckmr2.getSourceDestination());

		if (result == 0) {
			String cInfo1 = ckmr1.getCarrierInformation();
			Double rmsVal1 = Double.parseDouble(cInfo1.split("\t")[1]);

			String cInfo2 = ckmr2.getCarrierInformation();
			Double rmsVal2 = Double.parseDouble(cInfo2.split("\t")[1]);
			result = rmsVal1.compareTo(rmsVal2);

		}

		return result;
	}

}
