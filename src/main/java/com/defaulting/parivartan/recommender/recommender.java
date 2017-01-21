package com.defaulting.parivartan.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class recommender {

	public static List<String> init() {
		List<String> recommended = new LinkedList<>();
		try {
			final String PATH = System.getProperty("user.dir") + "\\files\\tasks.csv";
			final String PATH2 = System.getProperty("user.dir") + "\\files\\recommended.csv";
			//private static File tasks;
			
			//populate(tasks);
			BufferedReader br=new BufferedReader(new FileReader(PATH));
			//recommended = new File(PATH2);
			//if(!recommended.exists()) recommended.createNewFile();
			//BufferedWriter bw=new BufferedWriter(new FileWriter(recommended));
			
			DataModel dm = new FileDataModel(new File(PATH));
			
			ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
			
			Random r=new Random();
			int rn=r.nextInt()%900;
			if(rn<0)
				rn=-rn;
			
			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
			
			List<RecommendedItem> recommendations=recommender.mostSimilarItems(rn,5);
			for(RecommendedItem recommendation:recommendations){
				System.out.println(recommendation.getItemID());
				recommended.add(recommendation.getItemID()+"");
			}
			
		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("There was a Taste Exception");
			e.printStackTrace();
		}
		return recommended;
	}

	
		
	
}