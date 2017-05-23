package com.logmanager.matcher;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class LogMatcherImpl implements LogMatcher {
	private Set<String> toMatchSet;
	
	public LogMatcherImpl(final String matcherString) {
		this.toMatchSet = new HashSet<>();
		
		StringTokenizer st = new StringTokenizer(matcherString, ",");
		while(st.hasMoreTokens()){
			this.toMatchSet.add(st.nextToken().trim());
		}
	}
	
	@Override
	public boolean match(String logLine) {
		StringTokenizer st = new StringTokenizer(logLine, ",");
		
		Set<String> inputSet = new HashSet<String>();
		while(st.hasMoreTokens()){
			inputSet.add(st.nextToken().trim());
		}
		
		return inputSet.containsAll(toMatchSet);
	}

}
