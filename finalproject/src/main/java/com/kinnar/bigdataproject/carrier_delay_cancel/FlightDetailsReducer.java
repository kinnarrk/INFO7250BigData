package com.kinnar.bigdataproject.carrier_delay_cancel;

import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;

public class FlightDetailsReducer extends Reducer<Text, Text, Text, Text> {
//	private Text tmp = new Text();
	private ArrayList<Text> listA = new ArrayList<>();
	private ArrayList<Text> listB = new ArrayList<>();
	private String joinType = "inner";

//	@Override
//	protected void setup(Context context) throws IOException, InterruptedException {
//		// joinType = context.getConfiguration().get("join.type");
//		joinType = "inner";
//	}

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		listA.clear();
		listB.clear();
//		Iterator<Text> itr = values.iterator();
//		while (itr.hasNext()) {
//			tmp = itr.next();
//			if (tmp.charAt(0) == 'A') {
//				listA.add(new Text(tmp.toString().substring(1)));
//			} else if (tmp.charAt(0) == 'B') {
//				listB.add(new Text(tmp.toString().substring(1)));
//			}
//		}
		for (Text text : values) {
			if (text.charAt(0) == 'A') {
				listA.add(new Text(text.toString().substring(1)));
			} else if (text.charAt(0) == 'B') {
				listB.add(new Text(text.toString().substring(1)));
			}
		}

		executeInnerJoin(context);
	}

	private void executeInnerJoin(Context context) throws IOException, InterruptedException {
		//System.out.println("A size:" + listA.size() + " B size:" + listB.size());
		if (joinType.equals("inner")) {
			if (!listA.isEmpty() && !listB.isEmpty()) {				
				for (Text textA : listA) {
					for (Text textB : listB) {
						context.write(textA, textB);
					}
				}
			}
		}
	}
}
