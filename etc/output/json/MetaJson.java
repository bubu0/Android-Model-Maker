package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class MetaJson { 
	@JsonProperty("previous")
	private String previous; 
	@JsonProperty("next")
	private String next; 
	@JsonProperty("is_anonymous")
	private boolean isAnonymous; 
	@JsonProperty("limit")
	private long limitDb; 
	@JsonProperty("page")
	private long page; 
	@JsonProperty("pages")
	private long pages; 
	@JsonProperty("offset")
	private long offset; 
	@JsonProperty("latest")
	private long latest; 
	@JsonProperty("total_count")
	private long totalCount; 

	public MetaJson(String previous, String next, boolean isAnonymous, long limitDb, long page, long pages, long offset, long latest, long totalCount) { 
		super();
		this.previous = previous;
		this.next = next;
		this.isAnonymous = isAnonymous;
		this.limitDb = limitDb;
		this.page = page;
		this.pages = pages;
		this.offset = offset;
		this.latest = latest;
		this.totalCount = totalCount;
	} 

	public MetaJson() {
		super();
	} 
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
