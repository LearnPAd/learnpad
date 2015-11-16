/**
 *
 */
package eu.learnpad.simulator.utils;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Very basic Markov Model implementation
 *
 * A specific thing of note is the use of the startToken parameter. When
 * starting a serie of observations, the context of the first observations is
 * not enough to satisfy the contextSize. To avoid this problem the user must
 * define a start token and use it to pad the context.
 * <p>
 * E.g.: assume we create a MM with context 3, and we want to use it on the
 * serie: 3,2,6,4,2
 * <p>
 * The 3 firsts observations: ([], 3), ([3],2), ([3,2],6) do not have enough
 * context to be valid. Consequently the user registers them instead as:
 * ([#start, #start, #start], 3), ([#start, #start, 3],2), ([#start, 3, 2],6).
 *
 *
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SimpleMarkovModel<Value> {

	private final int contextSize;
	public final Value startToken;
	private final Random rand;

	private final Map<List<Value>, Map<Value, Integer>> observationMemory = new HashMap<List<Value>, Map<Value, Integer>>();

	public SimpleMarkovModel(int contextSize, Value startToken) {
		this(contextSize, startToken, new Random().nextLong());
	}

	public SimpleMarkovModel(int contextSize, Value startToken, Long seed) {
		this.contextSize = contextSize;
		this.startToken = startToken;
		this.rand = new Random(seed);
	}

	/**
	 * Add a new observation to the MM
	 *
	 * @param context
	 *            the context in which the observation was made. Its length
	 *            should be consistent with the MM
	 * @param observed
	 *            the observed value
	 */
	public void addObservation(List<Value> context, Value observed) {

		checkValidContext(context);

		if (!observationMemory.containsKey(context)) {
			observationMemory.put(context, new HashMap<Value, Integer>());
		}

		if (!observationMemory.get(context).containsKey(observed)) {
			observationMemory.get(context).put(observed, 1);
		} else {
			observationMemory.get(context).put(observed,
					observationMemory.get(context).get(observed) + 1);
		}
	}

	/**
	 * Convenience method to add all the observations of a complete series of
	 * values.
	 *
	 * It is not required to pad the series with start tokens beforehand.
	 *
	 * @param startContext
	 * @param serie
	 */
	public void addSeries(List<Value> startContext, List<Value> serie) {

		checkValidContext(startContext);

		int currentIndex = 0;

		List<Value> context = new ArrayList<Value>(startContext);

		while (currentIndex < serie.size()) {

			// add normal context values, using a sliding windows of size
			// contextSize
			for (int i = Math.max(currentIndex - contextSize, 0); i < currentIndex; i++) {
				context.remove(0);
				context.add(serie.get(i));
			}

			// add the observation
			addObservation(new ArrayList<Value>(context),
					serie.get(currentIndex));

			currentIndex++;
		}
	}

	/**
	 * Convenience method to add all the observations of a complete series of
	 * values.
	 *
	 * It is not required to pad the series with start tokens beforehand.
	 *
	 * @param serie
	 */
	public void addSeries(List<Value> serie) {

		List<Value> context = new ArrayList<Value>();

		// for the first values of the series, we do not have enough
		// context. We pad the context with the startToken to compensate
		for (int i = 0; i < contextSize; i++) {
			context.add(startToken);
		}

		addSeries(context, serie);

	}

	/**
	 * see {@link #addSerie(List) addSerie(List&lt;Value&gt;)}
	 *
	 * @param serie
	 */
	public void addSeries(Value[] serie) {
		this.addSeries(Arrays.asList(serie));
	}

	/**
	 * Draw a value based on the context and probabilities calculated from
	 * previous observations.
	 *
	 * @param context
	 * @return a probabilistic value from the MM, or null if the MM does not
	 *         have any observation for the given context
	 */
	public Value draw(List<Value> context) {

		checkValidContext(context);

		final int obsNb = totalObservations(context);

		if (obsNb == 0) {
			return null;
		} else {

			// Monte-Carlo: we iterate over the possible values until the
			// accumulator correspond to the value we drew

			final double drawnValue = rand.nextDouble();
			double acc = 0.0;

			Iterator<Entry<Value, Integer>> iter = observationMemory
					.get(context).entrySet().iterator();

			Entry<Value, Integer> t = iter.next();

			Value res = t.getKey();
			acc += t.getValue();

			while (acc / obsNb < drawnValue) {
				t = iter.next();
				res = t.getKey();
				acc += t.getValue();
			}

			return res;
		}

	}

	/**
	 * see {@link #draw(List) draw(List&lt;Value&gt;)}
	 *
	 * @param serie
	 */
	public Value draw(Value[] context) {
		return this.draw(Arrays.asList(context));
	}

	/**
	 * Helper method to check invariant regarding the passed context
	 *
	 * @param context
	 *            the context to check
	 */
	private void checkValidContext(List<Value> context) {
		if (context.size() != contextSize) {
			throw new RuntimeException("Expected context of size "
					+ contextSize + " but got context of size "
					+ context.size());
		}
	}

	/**
	 * Helper method to get the total number of observations made with a given
	 * context
	 *
	 * @param context
	 * @return the number of observations made with the given contex
	 */
	private int totalObservations(List<Value> context) {
		int res = 0;

		if (observationMemory.containsKey(context)) {
			for (Integer v : observationMemory.get(context).values()) {
				res += v;
			}
		}

		return res;
	}

	@Override
	public String toString() {
		String res = "";

		for (Entry<List<Value>, Map<Value, Integer>> e : observationMemory
				.entrySet()) {
			res = res + e.getKey();
			for (Value v : e.getValue().keySet()) {
				res = res + " " + v + " -> " + e.getValue().get(v) + ";";
			}
			res = res + "\n";
		}

		return res;
	}

	public static void main(String[] args) {
		// simple example

		SimpleMarkovModel<Character> m = new SimpleMarkovModel<Character>(2,
				'#');

		m.addSeries(ArrayUtils.toObject("aabbbccccaaabbc".toCharArray()));

		System.out.println(m);

		int nbA = 0;
		int nbC = 0;

		for (int i = 0; i < 1000000; i++) {
			Character v = m.draw(ArrayUtils.toObject("cc".toCharArray()));

			if (v == 'a') {
				nbA++;
			} else {
				nbC++;
			}
		}

		System.out.println("draw 'a' " + nbA + " times");
		System.out.println("draw 'c' " + nbC + " times");

	}
}
