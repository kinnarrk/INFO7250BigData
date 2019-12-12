package com.kinnar.bigdataproject.top10_busy_airports;

import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

public class TopNAirportsReducer extends Reducer<NullWritable, Text, NullWritable, Text>{
	private TreeMap<Integer, Text> counter = new TreeMap<>();
	
	@Override
    protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for(Text value: values){
            String[] val= value.toString().split("\t");

            if(val.length==2){
                int count = Integer.parseInt(val[1]);
                counter.put(count,new Text(value));
            }

            if(counter.size()>10)
            	counter.remove(counter.firstKey());
        }

        for(Text t: counter.descendingMap().values())
            context.write(NullWritable.get(),t);
    }
}
