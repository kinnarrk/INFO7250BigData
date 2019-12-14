package com.kinnar.bigdataproject.unique_carrier_names;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class CarriersApp2 {
	public static void main(String[] args) throws Exception {

		Configuration conf2 = new Configuration();

		Job job2 = Job.getInstance(conf2);
		job2.setJarByClass(CarriersApp2.class);
		job2.setJobName("Reducer Side Inner Join");

		MultipleInputs.addInputPath(job2, new Path(args[1]), TextInputFormat.class, CarrierInfoMapper.class);
		MultipleInputs.addInputPath(job2, new Path(args[0] + "/temp/carriersintermediate"), TextInputFormat.class,
				FlightDetailsMapper.class);

		job2.setReducerClass(FlightDetailsReducer.class);
		job2.setNumReduceTasks(1);

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

		TextOutputFormat.setOutputPath(job2, new Path(args[2]));
		System.exit(job2.waitForCompletion(true) ? 0 : 1);
	}
}
