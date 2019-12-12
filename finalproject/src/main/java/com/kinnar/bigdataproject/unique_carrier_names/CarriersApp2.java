package com.kinnar.bigdataproject.unique_carrier_names;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class CarriersApp2
{
//	public int run(String[] args) throws Exception {
	public static void main(String[] args) throws Exception {
		
//		jobControl.addJob(controlledJob1);
		Configuration conf2 = new Configuration();

		Job job2 = Job.getInstance(conf2);
		job2.setJarByClass(CarriersApp2.class);
		job2.setJobName("Reducer Side Inner Join");
		
		MultipleInputs.addInputPath(job2, new Path(args[1]),TextInputFormat.class, CarrierInfoMapper.class);
        MultipleInputs.addInputPath(job2, new Path(args[0] + "/temp/carriersintermediate" ),TextInputFormat.class, FlightDetailsMapper.class);

        job2.setReducerClass(FlightDetailsReducer.class);
        job2.setNumReduceTasks(1);
        
//        Path outputPath = new Path(args[2]);
        
//		FileInputFormat.setInputPaths(job2, new Path(args[1] + "/temp/carriersintermediate"));
//		FileOutputFormat.setOutputPath(job2, new Path(args[1] + "/UniqueCarriersWithNames"));
				
		job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
		
        TextOutputFormat.setOutputPath(job2, new Path(args[2]));
//		FileOutputFormat.setOutputPath(job2, outputPath);
//        outputPath.getFileSystem(conf2).delete(outputPath);
        System.exit(job2.waitForCompletion(true) ? 0 : 1);

//		job2.setMapperClass(FlightDetailsMapper.class);
//		job2.setReducerClass(FlightDetailsReducer.class);
//		job2.setCombinerClass(TopNAirportsReducer.class);

//		job2.setOutputKeyClass(Text.class);
//        job2.setOutputValueClass(Text.class);
//		job2.setOutputFormatClass(TextInputFormat.class);
		
//		job2.setInputFormatClass(TextInputFormat.class);
//        job2.setOutputFormatClass(TextOutputFormat.class);
//
//        job2.setMapOutputKeyClass(NullWritable.class);
//        job2.setMapOutputValueClass(Text.class);


//		job2.setSortComparatorClass(IntComparator.class);
//		ControlledJob controlledJob2 = new ControlledJob(conf2);
//		controlledJob2.setJob(job2);

//		if (!job2.waitForCompletion(true)) {
//	      System.exit(1);
//	    }
		
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
