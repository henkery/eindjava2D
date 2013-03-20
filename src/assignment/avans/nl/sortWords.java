package assignment.avans.nl;

import java.util.ArrayList;

public class sortWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0)
			System.out.println("you have to give arguments!");
		else
		{
			ArrayList<String> words = new ArrayList<String>();
			IOWrite readFile = new IOWrite();
			String[] lines = readFile.read(args[0]).split("\n");
			System.out.println("the file has a total of " + lines.length + " lines");
			for (String line : lines)
			{
				System.out.println(line);
				for (String word : line.split(" "))
				{
					System.out.println(word);
					for (String compareTo : words)
					{
						if (word.charAt(0) > compareTo.charAt(0))
							words.add(words.indexOf(compareTo), word);
					}
				}
			}
			for (String item : words)
			{
				System.out.println(item);
			}
		}

	}

}
