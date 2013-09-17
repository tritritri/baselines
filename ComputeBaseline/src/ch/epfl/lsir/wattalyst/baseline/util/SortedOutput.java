package ch.epfl.lsir.wattalyst.baseline.util;

import java.util.ArrayList;

/**
 * Sorted collection by keys. 
 * @author Tri Kurniawan Wijaya <tri-kurniawan.wijaya@epfl.ch>
 * @date Wednesday 07 August 2013 02:10:44 PM IST 
 */
public class SortedOutput {

	ArrayList<Long> keys;
	ArrayList<Object> contents;
	
	public SortedOutput() {
		keys = new ArrayList<Long>();
		contents = new ArrayList<Object>();
	}
	
	public void add (long key, Object content) {
		this.keys.add(key);
		this.contents.add(content);
	}
	
	public void sortAsc() {
		for (int i=0; i<contents.size()-1; i++) {
			for (int j=i+1; j<contents.size(); j++) {
				if ( keys.get(i) > keys.get(j)  ) {
					// swap
					long key = keys.get(i);
					keys.set(i, keys.get(j));
					keys.set(j, key);
					
					Object tmp = contents.get(i);
					contents.set(i, contents.get(j));
					contents.set(j, tmp);
				}
			}
		}
	}
	
	public Object getContent(int index) {
		return contents.get(index);
	}

	public long getKey(int index) {
		return keys.get(index);
	}
	public int size() {
		return contents.size();
	}
}

