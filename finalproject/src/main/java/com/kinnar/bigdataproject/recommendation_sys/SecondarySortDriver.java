/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kinnar.bigdataproject.recommendation_sys;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;
import java.net.URI;

public class SecondarySortDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		Configuration conf = new Configuration();
		FileSystem hdfs = FileSystem.get(URI.create("hdfs://localhost:9000"), conf);

		Path outDir = new Path(args[1]);
		// delete existing directory
		if (hdfs.exists(outDir)) {
			hdfs.delete(outDir, true);
		}

		Job job1 = Job.getInstance();

		job1.setJarByClass(SecondarySortDriver.class);

		job1.setGroupingComparatorClass(GroupComparator.class);
		job1.setSortComparatorClass(SecondarySortComparator.class);
		job1.setPartitionerClass(KeyPartition.class);

		FileInputFormat.addInputPath(job1, new Path(args[0]));
//        Path outDir = new Path(args[1]);
		FileOutputFormat.setOutputPath(job1, outDir);

		job1.setMapperClass(SecondarySortMapper.class);
		job1.setReducerClass(SecondarySortReducer.class);

		job1.setNumReduceTasks(1);

		job1.setOutputKeyClass(CompositeKeyMr.class);
		job1.setOutputValueClass(NullWritable.class);

		job1.waitForCompletion(true);
	}
}
