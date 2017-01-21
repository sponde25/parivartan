package com.defaulting.parivartan.dataService;

import java.util.LinkedList;
import java.util.List;

import com.defaulting.parivartan.recommender.recommender;
import com.defaulting.parivartan.userprofile.Task;

public class DataCreation {
	
	public static List<Task> readRecommedations() {
		List<String> recommended = recommender.init();
		List<Task> recommendations = new LinkedList<>();
		//tasks = new File(PATH);
		
		for(String item: recommended){
				
				int min = 1, max = 5;
				int a1 = min + (int) (Math.random() * (max - min + 1));
				int a2 = min + (int) (Math.random() * (max - min + 1));
				int a3 = min + (int) (Math.random() * (max - min + 1));
				recommendations.add(new Task("Task_"+item, "Description",
						a1,a2,a3
						));
			}
		
		
		return recommendations;
	}
	
	
}
