package org.srge.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
//on click listener in CardSectionFragment adds current card to incorrect list
//then sorts into priority queue based on how many times it was incorrect
//return newly made deck
public class CardSort {
	private static ArrayList<CardInfo> incorrect_list;
	static PriorityQueue<Object> pq = new PriorityQueue<Object>();
	
    Comparator<Object> comparator = new CardComparator();
    ArrayList<CardInfo> cardList = RunningInfo.getSelectedDeck().getCardList();
    PriorityQueue<Object> queue = new PriorityQueue<Object>(cardList.size(), comparator);

	
	public static ArrayList<CardInfo> add_incorrect_list(int card_number) {
		RunningInfo.getWorkingCardList().get(card_number);
		incorrect_list.add(RunningInfo.getWorkingCardList().get(card_number));	
		pq.offer(RunningInfo.getWorkingCardList().get(card_number));
		return incorrect_list;
	}
	
	public class CardComparator implements Comparator<Object>
	{	  

		@Override
		public int compare(Object left, Object right) {
			//compare elements
			return 0;
		}
	}


}
