/************************************************************************
 *         Copyright (C) 2004-2009 The University of Sheffield          *
 *       Developed by Mark Greenwood <m.greenwood@dcs.shef.ac.uk>       *
 *                                                                      *
 * This program is free software; you can redistribute it and/or modify *
 * it under the terms of the GNU Lesser General Public License as       *
 * published by the Free Software Foundation; either version 2.1 of the *
 * License, or (at your option) any later version.                      *
 *                                                                      *
 * This program is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of       *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the        *
 * GNU General Public License for more details.                         *
 *                                                                      *
 * You should have received a copy of the GNU Lesser General Public     *
 * License along with this program; if not, write to the Free Software  *
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.            *
 ************************************************************************/

package mark.chunking;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapulates chunking rules, providing methods to
 * see if it matches against text and to see if it fits within
 * a sentence.
 **/
public class Rule
{
	/**
	 * A Pattern which will split the rule name into type and offsets.
	 **/
	private static final Pattern pt = Pattern.compile("(T|W|P)(_?[0-3])+");

	/**
	 * A Pattern which will split up a sequence of offsets.
	 **/
	private static final Pattern po = Pattern.compile("_?[0-3]");

	/**
	 * The new chunk tag that is assigned if this rule matches the
	 * input text.
	 **/
	private String outTag = null;

	/**
	 * The full line of the rules file which created this rule,
	 * used mainly for the toString() method.
	 **/
	private String rule = null;

	/**
	 * A List to hold the types (T, W or P)
	 * of the parts of the rule.
	 **/
	private List<String> types = new ArrayList<String>();

	/**
	 * A List to hold the offsets for the
	 * parts of the rule.
	 **/
	private List<List<Integer>> offsets = new ArrayList<List<Integer>>();

	/**
	 * A List to hold the values for the
	 * parts of the rule.
	 **/
	private List<String> values = new ArrayList<String>();

	/**
	 * The smallest offset used within this rule.
	 **/
	private int begin = Integer.MAX_VALUE;

	/**
	 * The largest offset used within this rule.
	 **/
	private int end = Integer.MIN_VALUE;

	public Rule(String rule)
	{
		//store a copy of the defining text
		this.rule = rule;

		//split the rule into pieces at the spaces
		String[] parts = rule.split(" ");

		//store the last part of the rule as the out tag
		outTag = parts[parts.length-1];

		//Use the Pattern to split the template type
		//into the different token/offsets
		Matcher mt = pt.matcher(parts[0]);

		//We know that the first value is at position 1
		//in the split array
		int index = 1;

		while (mt.find())
		{
			//while there are still parts to process,
			//get the next one
			String to = mt.group();

			//store the type of this part
			types.add(to.substring(0,1));

			//create a new list to hold the offsets
			//for this part
			List<Integer> ofs = new ArrayList<Integer>();

			//split the offsets into separate parts
			Matcher mo = po.matcher(to.substring(1));

			//store the value associated with this part of the rule
			values.add(parts[index++]);

			while (mo.find())
			{
				//while there are more offsets,

				//get the next one and make an Integer from it
				//(we have to replace '_' by '-' first for it to work)
				Integer offset = new Integer(mo.group().replaceAll("_","-"));

				//if the current offset is before the known beginning then
				//make this the beginning
				if (offset.intValue() < begin) begin = offset.intValue();

				//if the current offset is after the known ending then
				//make this offset the end
				if (offset.intValue() > end) end = offset.intValue();

				//store the offset in the list
				ofs.add(offset);
			}

			//store the list of offsets for this part
			offsets.add(ofs);
		}
	}

	/**
	 * Simply returns true if this rule matches against the sentence at
	 * a given position. This method makes no alterations to the
	 * tags assigned to any specific offset.
	 * @param currentToken the index within the sentece of the token
	 *                     upon which the rule is centered.
	 * @param words an ordered List of the words within the sentence.
	 * @param tags an ordered List of the chunk tags within the sentence.
	 * @param pos an ordered List of the POS tags within the sentence.
	 * @return true if the rule matches the input sentence, false otherwise.
	 **/
	public boolean match(int currentToken, List<String> words, List<String> tags, List<String> pos)
	{
		//if the rule doesn't fit within the sentence then it can never
		//match so simply return false
		if (!withinSentence(words.size(), currentToken)) return false;

		//assume the rule will match
		boolean matched = true;

		//loop through all the parts of this rule
		for (int i = 0 ; i < types.size() ; ++i)
		{
			//get the current type
			String type = types.get(i);

			//get the list of offsets for the part
			List<Integer> ofs = offsets.get(i);

			//get the value for this part
			String value = values.get(i);

			//A placeholder for the right list
			List<String> working = null;

			if (type.equals("T"))
			{
				//if the type is "T" then the list we
				//are going to work on contains chunk tags
				working = tags;
			}
			else if (type.equals("W"))
			{
				//if the type is "W" then the list we
				//are going to work on contains words
				working = words;
			}
			else if (type.equals("P"))
			{
				//if the type is "P" then the list we
				//are going to work on contains POS tags
				working = pos;
			}

			//get the first (maybe the only) offset for this part
			int offset = ofs.get(0).intValue();

			//does the value of this offset match the value given in the rule
			boolean matchOffset = working.get(currentToken+offset).equals(value);

			for (int j = 1 ; j < ofs.size() ; ++j)
			{
				//if there is more than one offset then...

				//get the next offset
				offset = ofs.get(j).intValue();

				//or the truth of matching the value in the rule against
				//the value of the offset
				matchOffset = matchOffset || working.get(currentToken+offset).equals(value);
			}

			//combine the success/failure of matching this part with that
			//of matching the rest of the rule
			matched = matched && matchOffset;

			//if we have failed to match there is no point trying
			//to match the rest of the rule so jump out of this loop
			if (!matched) i = types.size();
		}

		//return the result of matching we have found
		return matched;
	}

	/**
	 * Simply returns the new chunk tag to use if this rule matched.
	 * @return the new chunk tag.
	 **/
	public String getNewTag()
	{
		//simply return the out tag
		return outTag;
	}

	/**
	 * A method which allows you to check that this rule fits within
	 * the sentence when centered on a specific token.
	 * @param numTokens the total number of tokens in the sentence.
	 * @param currentToken the index of the token upon which the
	 *        rule is going to be centered.
	 * @return true if the rule fits within the sentence, false otherwise.
	 **/
	public boolean withinSentence(int numTokens, int currentToken)
	{
		int start = currentToken + begin;
		int finish = currentToken + end;

		boolean within = (start >= 0 && start < numTokens);

		within = within && (finish >= 0 && finish < numTokens);

		return within;
	}

	@Override public String toString()
	{
		//simply return the line of the rules file
		return rule;
	}
}