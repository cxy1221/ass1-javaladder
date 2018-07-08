package cxy.com;

import java.io.*;
import java.util.*;


public class Wordladder {
	
	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>(); 
		String words[] = new String[2];
		read_dict(dict);
		while(true) {
			rw(words);
			while(!(words,dict)){
				rw(words);
			}
			Queue<Stack<String>> que = new LinkedList<Stack<String>>();
			Stack<String> tempstack = new Stack<String>();
			tempstack.push(words[0]);
			que.add(tempstack);
			ladder(que, words, dict);
		}
	}
	private static void ladder(Queue<Stack<String>> que, String[] words, Set<String> dict) {
		Stack<String> tempstack = new Stack<String>();
		Set<String> used_dic = new HashSet<String>();
		used_dic.add(words[0]);
		//used_dic.add(words[1]);
		while(!que.isEmpty()) {
			while(!tempstack.isEmpty())
				tempstack.pop();
			tempstack = que.poll();
			for(int i = 0; i < tempstack.peek().length(); i++) {
				for(char c = 'a'; c <='z'; c++) {
					if(c == tempstack.peek().charAt(i))
						continue;
					String tempword = tempstack.peek();
					StringBuilder strb = new StringBuilder(tempword);
					strb.replace(i, i+1, String.valueOf(c));
					tempword = strb.toString();//replace [i]
					
					if(used_dic.contains(tempword))
						continue;
					if(tempword.equals(words[1])) {
						System.out.printf("A ladder from %s back to %s: %s ",words[0],words[1],words[1]);
						while(!tempstack.isEmpty()) {
							System.out.printf("%s ",tempstack.peek());
							tempstack.pop();
						}
						System.out.printf("\n");
						return;
					}
					if(dict.contains(tempword)) {
						tempstack.push(tempword);
						Stack<String> tmps = (Stack<String>) tempstack.clone();
						que.add(tmps);
						tempstack.pop();
						used_dic.add(tempword);
					}
				}
			}
		}
		System.out.printf("No word ladder found from %s back to %s.",words[1],words[0]);
		return;	
	}
	private static void read_dict(Set<String> dict) {
		try {
			System.out.println("Dictionary file name?");
			Scanner scanner = new Scanner(System.in);
			String filename = scanner.nextLine();
			while(!(filename.equals("dictionary.txt") || 
					filename.equals("EnglishWords.txt") || 
					filename.equals("smalldict1.txt") ||
					filename.equals("smalldict2.txt") || filename.equals("smalldict3.txt")) ) {
				System.out.println("Wrong filename");
				filename = scanner.nextLine();
			}
			File file = new File("src\\cxy.com\\" + filename);
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String nextline = null;
			while((nextline = bf.readLine()) != null) {
				dict.add(nextline);
				//System.out.println(nextline);
			}
			bf.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private static void rw(String[] words) {
		System.out.println("Word #1 (or Enter to quit)");
		Scanner scanner = new Scanner(System.in);
		String tmp1 = scanner.nextLine();
		if(tmp1.length() == 0) {
			System.out.println("Have a nice day.");
			System.exit(0);
		}
		words[0] = tmp1.toLowerCase();
		System.out.println("Word #2 (or Enter to quit)");
		String tmp2 = scanner.nextLine();
		if(tmp2.length() == 0) {
			System.out.println("Have a nice day.");
			System.exit(0);
		}
		words[1] = tmp2.toLowerCase();
	}
	private static boolean is_valid(String[] words, Set<String> dict) {
		if(dict.contains(words[0]) && dict.contains(words[1])) {
			if(words[0].length() != words[1].length()) {
				System.out.println("The two words must be the same length.");
				return false;
			}
			if(words[0].equals(words[1])) {
				System.out.println("The two words must be different.");
				return false;
			}
		}else {
			System.out.println("The two words must be found in the dictionary.");
			return false;
		}
		return true;
	}
	
}
