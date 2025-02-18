package com.gn.common.vo;

public class Paging {
	// 전체 게시글 갯수
	private int totalData;
	// 전페 페이지 개수
	private int totalPage;
	// 한 페이지당 게시글의 개수 필요
	private int numPerPage =10;
	// LIMIT 쿼리 앞쪽 숫자 : 건너뛸 숫자, 뒷쪽숫자 : 보여줄 숫자
	private int limitPageNo;
	// 현재 페이지 번호(1페이지 부터 시작하겠다)
	private int nowPage =1;
	
	// 페이징 바
	private int pageBarSize = 5;
	private int pageBarStart;
	private int pageBarEnd;
	//페이징바의 이전,다음 여부
	private boolean prev = true;
	private boolean next = true;
	
	
	public int getTotalData() {
		return totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		calcPaging();
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getLimitPageNo() {
		return limitPageNo;
	}
	public void setLimitPageNo(int limitPageNo) {
		this.limitPageNo = limitPageNo;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageBarSize() {
		return pageBarSize;
	}
	public void setPageBarSize(int pageBarSize) {
		this.pageBarSize = pageBarSize;
	}
	public int getPageBarStart() {
		return pageBarStart;
	}
	public void setPageBarStart(int pageBarStart) {
		this.pageBarStart = pageBarStart;
	}
	public int getPageBarEnd() {
		return pageBarEnd;
	}
	public void setPageBarEnd(int pageBarEnd) {
		this.pageBarEnd = pageBarEnd;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	private void calcPaging() {
		// 현재 존재하는 페이지를 기준으로 건너뛸 데이터 개수를 셋팅해주기
		// 현재 3페이지면 앞에 2페이지가 있다고 생각하고 -> 6개(한페이지탕 3개)
		limitPageNo = (nowPage-1)*numPerPage;
		// 전체 게시글 기준으로 페이지 개수를 계산하기
		totalPage = (int)Math.ceil((double)totalData/numPerPage);
		
		// 페이징바 계산하기
		// 시작번호구하기
		pageBarStart = ((nowPage-1)/pageBarSize)*pageBarSize +1;
		// 끝 번호
		pageBarEnd = pageBarStart + pageBarSize -1;
		if(pageBarEnd > totalPage) pageBarEnd = totalPage;
		// 페이징바가 1번일 경우를 제외하고 이전,이후버튼은 항상 고정
		if(pageBarStart == 1) prev = false;
		if(pageBarEnd >= totalPage) next = false;
		
		
	}
	
	
}
