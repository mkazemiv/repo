/**
 * This class !@#$
 * @author Mohammad A. Kazemivarnamkhasti
 */
import java.util.*;

public class Iterators {
	public static void main(String[] args) {
		// instance of Random constructed
		Random rand = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int k = 0; k < 20; k++)
		{
			numbers.add(rand.nextInt(100));
			if(numbers.get(k) < 10)
				numbers.set(k, numbers.get(k)+10);
		}
		
		// the ArrayList is printed
		System.out.print("Random set of 20 integers: ");
		for(int k = 0; k < numbers.size(); k++)
			System.out.print(numbers.get(k) + " ");
		System.out.println();
		// instance of ListIterator initialized
		ListIterator<Integer> cursor = numbers.listIterator();
		boolean anyMatches = true;
		int d;
		do {
			d = numbers.size();
			while(cursor.hasNext())
			{
				int temp1 = cursor.next();
				int temp2;
				if(cursor.hasNext())
					temp2 = cursor.next();
				else
					break;				
				if((temp1%10 == temp2%10) || (temp1/10 == temp2/10))
				{
					cursor.remove();
					cursor.previous();
					cursor.remove();
				}
				else if(cursor.hasPrevious())
					cursor.previous();
			}
			cursor = numbers.listIterator();
			System.out.print("\nIntegers remaining after run: ");
			for(int k = 0; k < numbers.size(); k++)
				System.out.print(numbers.get(k) + " ");
			System.out.println();
			if(d == numbers.size())
				anyMatches = false;
		} while(anyMatches);
		
		if(anyMatches == false)
			System.out.println("\nYou lose! Better luck next time. :(");
		else
			System.out.println("\nYou win! All the numbers matched! :)");
		
	}	
}

