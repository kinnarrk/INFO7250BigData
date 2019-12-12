package com.kinnar.bigdataproject.top10_busy_airports;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
//import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
//import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//import org.apache.hadoop.util.Tool;
//import org.apache.hadoop.util.ToolRunner;

public class TopNApp
{
//	public int run(String[] args) throws Exception {
	public static void main(String[] args) throws Exception {

//		JobControl jobControl = new JobControl("jobChain to find top 10 Source Destination airports");
		Configuration conf1 = new Configuration();
//		Configuration conf1 = getConf();

		Job job1 = Job.getInstance(conf1);
		job1.setJarByClass(TopNApp.class);
		job1.setJobName("Get src-dest airport combination count");

		FileInputFormat.setInputPaths(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1] + "/temp/topnintermediate"));

		job1.setMapperClass(SrcDestMapper.class);
		job1.setReducerClass(SrcDestReducer.class);
//		job1.setNumReduceTasks(1);
//		job1.setCombinerClass(SrcDestReducer.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);
		
		job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);
        
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(IntWritable.class);

//		ControlledJob controlledJob1 = new ControlledJob(conf1);
//		controlledJob1.setJob(job1);

		if (!job1.waitForCompletion(true)) {
	      System.exit(1);
	    }
		
//		jobControl.addJob(controlledJob1);
		Configuration conf2 = new Configuration();

		Job job2 = Job.getInstance(conf2);
		job2.setJarByClass(TopNApp.class);
		job2.setJobName("Top 10 Source Destination airport combination from previous MR job");

		FileInputFormat.setInputPaths(job2, new Path(args[1] + "/temp/topnintermediate"));
		FileOutputFormat.setOutputPath(job2, new Path(args[1] + "/Top10SourceDestinations"));

		job2.setMapperClass(TopNAirportsMapper.class);
		job2.setReducerClass(TopNAirportsReducer.class);
//		job2.setCombinerClass(TopNAirportsReducer.class);

		job2.setMapOutputKeyClass(NullWritable.class);
        job2.setMapOutputValueClass(Text.class);
//		job2.setOutputFormatClass(TextInputFormat.class);
		
		job2.setInputFormatClass(TextInputFormat.class);
        job2.setOutputFormatClass(TextOutputFormat.class);

        job2.setMapOutputKeyClass(NullWritable.class);
        job2.setMapOutputValueClass(Text.class);


//		job2.setSortComparatorClass(IntComparator.class);
//		ControlledJob controlledJob2 = new ControlledJob(conf2);
//		controlledJob2.setJob(job2);

		if (!job2.waitForCompletion(true)) {
	      System.exit(1);
	    }
		
		// make job2 dependent on job1
//		controlledJob2.addDependingJob(controlledJob1);
		// add the job to the job control
//		jobControl.addJob(controlledJob2);
//		Thread jobControlThread = new Thread(jobControl);
//		jobControlThread.start();

//		while (!jobControl.allFinished()) {
//			try {
//				System.out.println("Jobs in waiting state: " + jobControl.getWaitingJobList().size());  
//			    System.out.println("Jobs in ready state: " + jobControl.getReadyJobsList().size());
//			    System.out.println("Jobs in running state: " + jobControl.getRunningJobList().size());
//			    System.out.println("Jobs in success state: " + jobControl.getSuccessfulJobList().size());
//			    System.out.println("Jobs in failed state: " + jobControl.getFailedJobList().size());
//				Thread.sleep(5000);
//			} catch (Exception e) {
//
//			}
//
//		}
//		System.exit(0);
//		return (job1.waitForCompletion(true) ? 0 : 1);
		  
		 
	}

//	public static void main(String[] args) throws Exception {
//		int exitCode = ToolRunner.run(new TopNApp(), args);
//		System.exit(exitCode);
//	}
	
}
