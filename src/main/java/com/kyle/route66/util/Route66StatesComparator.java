package com.kyle.route66.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.kyle.route66.db.model.State;

public class Route66StatesComparator implements Comparator<State> {
	private List<String> states;
	
	public Route66StatesComparator() {
		states = new ArrayList<String>(8);
		states.add("Illinois");
		states.add("Missouri");
		states.add("Kansas");
		states.add("Oklahoma");
		states.add("Texas");
		states.add("New Mexico");
		states.add("Arizona");
		states.add("California");
	}

	@Override
	public int compare(State o1, State o2) {
		return Integer.compare(states.indexOf(o1.getName()), states.indexOf(o2.getName()));
	}

}
