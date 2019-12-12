package com.kinnar.bigdataproject.yearly_delay;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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

public class YearlyDelayApp
{
//	public int run(String[] args) throws Exception {
	public static void main(String[] args) throws Exception {

//		JobControl jobControl = new JobControl("jobChain to find top 10 Source Destination airports");
		Configuration conf1 = new Configuration();
//		Configuration conf1 = getConf();

		Job job1 = Job.getInstance(conf1);
		job1.setJarByClass(YearlyDelayApp.class);
		job1.setJobName("Yearly delay ratio with delay > 15 minutes and cancelled flights ratio");

		FileInputFormat.setInputPaths(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));
		
		job1.setMapperClass(YearlyDelayMapper.class);
		job1.setCombinerClass(YearlyDelayReducer.class);
        job1.setReducerClass(YearlyDelayReducer.class);

		job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);

        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(DelayRatioTuple.class);

        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(DelayRatioTuple.class);
        
//		ControlledJob controlledJob1 = new ControlledJob(conf1);
//		controlledJob1.setJob(job1);

		if (!job1.waitForCompletion(true)) {
	      System.exit(1);
	    }		
		 
	}

//	public static void main(String[] args) throws Exception {
//		int exitCode = ToolRunner.run(new TopNApp(), args);
//		System.exit(exitCode);
//	}
	
}
