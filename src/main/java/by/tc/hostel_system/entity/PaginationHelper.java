package by.tc.hostel_system.entity;

import java.io.Serializable;
import java.util.Objects;

public class PaginationHelper implements Serializable{
	private static final long serialVersionUID = 3974115174617028870L;
	private static final int first = 1;
	private int prev;
	private int current;
	private int next;
	private int last;
	private int begin;
	private int end;
	private String firstPage;
	private String prevPage;
	private String nextPage;
	private String lastPage;

	public PaginationHelper() {
	}

	public int getFirst() {
		return first;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(String prevPage) {
		this.prevPage = prevPage;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getLastPage() {
		return lastPage;
	}

	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PaginationHelper that = (PaginationHelper) o;
		return prev == that.prev &&
				current == that.current &&
				next == that.next &&
				last == that.last &&
				begin == that.begin &&
				end == that.end &&
				Objects.equals(firstPage, that.firstPage) &&
				Objects.equals(prevPage, that.prevPage) &&
				Objects.equals(nextPage, that.nextPage) &&
				Objects.equals(lastPage, that.lastPage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(prev, current, next, last, begin, end, firstPage, prevPage, nextPage, lastPage);
	}

	@Override
	public String toString() {
		return "PaginationHelper{" +
				"prev=" + prev +
				", current=" + current +
				", next=" + next +
				", last=" + last +
				", begin=" + begin +
				", end=" + end +
				", firstPage='" + firstPage + '\'' +
				", prevPage='" + prevPage + '\'' +
				", nextPage='" + nextPage + '\'' +
				", lastPage='" + lastPage + '\'' +
				'}';
	}
}
