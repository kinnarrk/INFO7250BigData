/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kinnar.bigdataproject.recommendation_sys;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class KeyPartition extends Partitioner<CompositeKeyMr, NullWritable> {

	@Override
	public int getPartition(CompositeKeyMr key, NullWritable value, int numPartitions) {

		return key.getSourceDestination().hashCode() % numPartitions;

	}

}
