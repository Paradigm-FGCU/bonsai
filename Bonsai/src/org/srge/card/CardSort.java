package org.srge.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
//on click listener in CardSectionFragment adds current card to incorrect list
//then sorts into priority queue based on how many times it was incorrect
//return newly made deck
public class CardSort {
	private static PriorityQueue<CardInfo> pq;
    private static Comparator<CardInfo> comparator;
    
    public static ArrayList<CardInfo> sortHardestFirst(ArrayList<CardInfo> original){
            comparator = new CardComparator();
            pq = new PriorityQueue<CardInfo>(original.size(),comparator);

            //creates a new list to return
            ArrayList<CardInfo> newList = new ArrayList<CardInfo>();

            //Adds the cards to the priority queue which automatically sorts them
            for(int i=0;i<original.size();i++){
                pq.add(original.get(i));
            }

            //takes the sorted card list and adds them to the new list
            while(!pq.isEmpty()){
                newList.add(pq.poll());
            }
            
            Collections.reverse(newList);
            
            //returns the new list
            return newList;
    }
	
	public static class CardComparator implements Comparator<CardInfo>
	{	  
            @Override
            public int compare(CardInfo left, CardInfo right) {
                int leftValue = getCardValue(left);
                int rightValue = getCardValue(right);

                //compare elements
                if(leftValue>rightValue){
                    return -1;
                }
                else if(leftValue==rightValue){
                    return 0;
                }
                else{
                    return 1;
                }
            }
	}

        private static int getCardValue(CardInfo in){
            int tempForLeft;
            double correct = in.getNumberCorrect();
            double seen = in.getNumberSeen();
            if(seen==0) tempForLeft = 2;
            else if((correct/seen)<0.34) tempForLeft = 1;
            else tempForLeft = 3;
            return tempForLeft;
        }

}
