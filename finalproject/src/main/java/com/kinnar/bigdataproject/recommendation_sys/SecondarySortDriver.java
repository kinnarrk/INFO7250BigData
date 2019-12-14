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
    
        public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException
    {
        
    	Configuration conf = new Configuration();
    	FileSystem hdfs = FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
		
		Path outDir = new Path(args[1]);
		// delete existing directory
		if (hdfs.exists(outDir)) {
		    hdfs.delete(outDir, true);
		}
    		
        Job job = Job.getInstance();
        
        job.setJarByClass(SecondarySortDriver.class);
        
        job.setGroupingComparatorClass(GroupComparator.class);
        job.setSortComparatorClass(SecondarySortComparator.class);
        job.setPartitionerClass(KeyPartition.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
//        Path outDir = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outDir);
        
        job.setMapperClass(SecondarySortMapper.class);
        job.setReducerClass(SecondarySortReducer.class);
        
        job.setNumReduceTasks(1);
        
        job.setOutputKeyClass(CompositeKey.class);
        job.setOutputValueClass(NullWritable.class);
        
//        FileSystem fs = FileSystem.get(job.getConfiguration());
//        if(fs.exists(outDir)) {
//        	fs.delete(outDir, true);
//        }
        
        job.waitForCompletion(true);
    }
}
