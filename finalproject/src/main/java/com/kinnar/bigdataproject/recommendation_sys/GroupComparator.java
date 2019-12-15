/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kinnar.bigdataproject.recommendation_sys;

import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {

	protected GroupComparator() {
		super(CompositeKeyMr.class, true);
	}

	@Override
	public int compare(Object aa, Object bb) {

		CompositeKeyMr ckmr1 = (CompositeKeyMr) aa;
		CompositeKeyMr ckmr2 = (CompositeKeyMr) bb;

		return ckmr1.getSourceDestination().compareTo(ckmr2.getSourceDestination());
	}
}
