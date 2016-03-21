/**
 * 
 */
package com.application.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;

public class CookieListWrapper {

	private HashMap<NewCookie, Integer> cookieMap;

	public CookieListWrapper() {
		cookieMap = new HashMap<NewCookie, Integer>();
	}

	public void addCookie(NewCookie newCookieObj, int custNo) {
		cookieMap.put(newCookieObj, new Integer(custNo));
	}

	public boolean deleteCookie(Cookie cookieObj) {
		Iterator<Entry<NewCookie, Integer>> iteratorObj = cookieMap.entrySet().iterator();
		NewCookie newCookieObj;
		while(iteratorObj.hasNext()) {
			Entry<?, ?> currentEntry = (Entry<?, ?>) iteratorObj.next();
			newCookieObj = (NewCookie) currentEntry.getKey();
			if(newCookieObj.getValue().equalsIgnoreCase(cookieObj.getValue())) {
				cookieMap.remove(newCookieObj);
				return true;
			}			
		}
		return false;
	}
	
	public NewCookie findCookie(Cookie cookieObj) {
		Iterator<Entry<NewCookie, Integer>> iteratorObj = cookieMap.entrySet().iterator();
		NewCookie newCookieObj;
		while(iteratorObj.hasNext()) {
			Entry<NewCookie, Integer> currentEntry = (Entry<NewCookie, Integer>) iteratorObj.next();
			newCookieObj = currentEntry.getKey();
			if(newCookieObj.getValue().equalsIgnoreCase(cookieObj.getValue())) {
				return newCookieObj;
			}
		}
		return null;
	}

	public String displayCookies() {
		return cookieMap.toString();
	}
}
