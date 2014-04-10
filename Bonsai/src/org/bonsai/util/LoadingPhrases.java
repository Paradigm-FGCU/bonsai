package org.bonsai.util;

import java.util.Random;

public class LoadingPhrases {
	static String[] phrases = {"Setting up hamster wheels",
								"Heating up the oven",
								"Loading audio files",
								"Installing divers",
								"Discovering fire",
								"Watching paint dry",
								"Chopping down a bonsai tree",
								"Finding licks to the center of a tootsie pop"
								};
	
	

	
	public static String generatePhrase(){
		Random random = new Random();
		int rint = random.nextInt(phrases.length);
		return phrases[rint];
	}
	
	public static String generatePhrase(CharSequence in){
		Random random = new Random();
		String inTemp = in.toString();
		int rint = random.nextInt(phrases.length);;
		while(phrases[rint].equals(inTemp)){
			rint = random.nextInt(phrases.length);
		}
		return phrases[rint];
	}
}
