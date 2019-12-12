package com.kinnar.bigdataproject.unique_carrier_names;

import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class CarrierInfoMapper extends Mapper<LongWritable, Text, Text, Text> {
	Text word = new Text();
    IntWritable one = new IntWritable(1);
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] data= line.split(",");
        if(data[0].equals("Code"))return;
        String nkey = data[0].replace("\"", "");
        System.out.println("Akey:"+nkey+":");
        word.set(nkey);
        String out= "A"+data[1].replace("\"", "");	//left table        
        context.write(word, new Text(out));
    }
}
