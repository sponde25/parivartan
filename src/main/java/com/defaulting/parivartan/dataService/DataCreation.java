package com.defaulting.parivartan.dataService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import com.defaulting.parivartan.userprofile.Task;

public class DataCreation {
	private final static String PATH = System.getProperty("user.dir") + "\\files\\tasks.csv";
	private static File tasks;
	public static File populateData() {
		
		tasks = new File(PATH);
		if(tasks.exists()) tasks.delete();
		try {
			tasks.createNewFile();
			PrintWriter taskWriter = new PrintWriter(tasks);
			
			int min = 1;
			int max = 5;
			String task = "";
			for(int i = 0; i<=10000; i++) {
				task += "" + i;
				int atrr1 = min + (int) (Math.random() * (max-min+1));
				int atrr2 = min + (int) (Math.random() * (max-min+1));
				int atrr3 = min + (int) (Math.random() * (max-min+1));
				task += "," + atrr1 + "," + atrr2 + "," + atrr3;
				task += "\n";
			}
			//System.out.println(task);
			taskWriter.write(task);
			taskWriter.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasks;
	}
	
	public static List<Task> getAllTasks(){
		 tasks = new File(PATH);
		if(!tasks.exists()) populateData();
		List<Task> taskList = new LinkedList<>();
		try {
			FileReader fileReader = new FileReader(tasks);
			BufferedReader br = new BufferedReader(fileReader);
			String task;
			while((task = br.readLine()) != null) {
				String[] taskAr = task.split(",");
				taskList.add(new Task("Task_"+taskAr[0],"description",Integer.parseInt(taskAr[1]),
						Integer.parseInt(taskAr[2]), Integer.parseInt(taskAr[3])
						));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return taskList;
	}
}
