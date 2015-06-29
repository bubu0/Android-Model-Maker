package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Meta {
 
	private String previous; 
	private String next; 
	private boolean isAnonymous; 
	private long limitDb; 
	private long page; 
	private long pages; 
	private long offset; 
	private long latest; 
	private long totalCount; 

	public Meta() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public String getPrevious() {
		return this.previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}
 
	public String getNext() {
		return this.next;
	}

	public void setNext(String next) {
		this.next = next;
	}
 
	public boolean getIsAnonymous() {
		return this.isAnonymous;
	}

	public void setIsAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
 
	public long getLimitDb() {
		return this.limitDb;
	}

	public void setLimitDb(long limitDb) {
		this.limitDb = limitDb;
	}
 
	public long getPage() {
		return this.page;
	}

	public void setPage(long page) {
		this.page = page;
	}
 
	public long getPages() {
		return this.pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}
 
	public long getOffset() {
		return this.offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}
 
	public long getLatest() {
		return this.latest;
	}

	public void setLatest(long latest) {
		this.latest = latest;
	}
 
	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Meta : "
		+ " previous = " + previous
		+ " next = " + next
		+ " isAnonymous = " + isAnonymous
		+ " limitDb = " + limitDb
		+ " page = " + page
		+ " pages = " + pages
		+ " offset = " + offset
		+ " latest = " + latest
		+ " totalCount = " + totalCount;
		return s;
	} 
} 
