package com.portfolio.Flea_Market.UTIL;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pagination {

	private static final Logger logger = LoggerFactory.getLogger(Pagination.class);
	
	//한 페이지당 게시되는 게시물 건 수
	private final int defaultRecordCountPerPage = 3;
	
	//페이지 리스트에 게시되는 페이지 건 수
	private final int defaultPageSize = 5;
	
	
	/*
	 * 게시판 페이징 구성에 필요한 수치들을 연산.
	 * curPageNo = 현재 페이지 번호
	 * recordCountPerPage = 한 페이지당 게시되는 게시물 건 수
	 * pageSize = 페이지 리스트에 게시되는 페이지 건수
	 * totalRecordCount = 전체 게시물 건 수
	 *
	 */
	public HashMap<String, Integer> calcPagerElement(int curPageNo, int totalRecordCount, int recordCountPerPage, int pageSize){
		
		int currentPageNo = curPageNo <= 1 ? 1 : curPageNo;
		recordCountPerPage = recordCountPerPage < 1 ? defaultRecordCountPerPage : recordCountPerPage;
		pageSize = pageSize < 1 ? defaultPageSize : pageSize;
		
		// 페이지 개수
		int totalPageCount = ((totalRecordCount - 1) / recordCountPerPage) + 1;
		
		// 페이지 리스트의 첫 페이지 번호
		int firstPageNoOnPageList = ((currentPageNo - 1) / pageSize) * pageSize + 1;
		
		// 페이지 리스트의 마지막 페이지 번호
		int lastPageNoOnPageList = firstPageNoOnPageList + pageSize - 1;
		
		// 이전 페이지
		int prevPageNoOnPageList = firstPageNoOnPageList - 1 < 1 ? 1 : firstPageNoOnPageList - 1;
		
		// 다음 페이지
		int nextPageNoOnPageList = lastPageNoOnPageList + 1;
		
		//페이지 리스트의 마지막 페이지 번호가 전체 페이지 수보다 큰지 체크
		logger.debug("");
		logger.debug("페이지 리스트의 마지막 페이지 번호가 전체 페이지 수보다 큰지 체크");
		logger.debug(lastPageNoOnPageList+" > "+totalPageCount);
		logger.debug("");
		
		if (lastPageNoOnPageList > totalPageCount) {
			lastPageNoOnPageList = totalPageCount;
			nextPageNoOnPageList = totalPageCount;
		}
		
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		returnMap.put("currentPageNo", currentPageNo);
		returnMap.put("recordCountPerPage", recordCountPerPage);
		returnMap.put("pageSize", pageSize);
		returnMap.put("totalRecordCount", totalRecordCount);
		returnMap.put("totalPageCount", totalPageCount);
		returnMap.put("firstPageNoOnPageList", firstPageNoOnPageList);
		returnMap.put("lastPageNoOnPageList", lastPageNoOnPageList);
		returnMap.put("prevPageNoOnPageList", prevPageNoOnPageList);
		returnMap.put("nextPageNoOnPageList", nextPageNoOnPageList);
		returnMap.put("startPageNoOnPageList", 1); //처음 페이지
		returnMap.put("endPageNoOnPageList", totalPageCount); //끝 페이지
		
		return returnMap;
	}
	
	/*
	 * 게시물 조회 범위 연산.
	 * curPageNo = 현재 페이지 번호
	 * recordCountPerPage = 한 페이지당 게시되는 게시물 건 수
	 * pageSize = 페이지 리스트에 게시되는 페이지 건수
	 * totalRecordCount = 전체 게시물 건 수
	 *
	 */
	public HashMap<String, Integer> calcDataRange(int curPageNo, int recordCountPerPage){
		
		int currentPageNo = curPageNo <= 1 ? 1 : curPageNo;
		
		recordCountPerPage = recordCountPerPage < 1 ? defaultRecordCountPerPage : recordCountPerPage;
		
		// 페이징 SQL의 start
		int firstRecordIndex = ((currentPageNo - 1) * recordCountPerPage) + 1;
		
		// 페이징 SQL의 end
		int lastRecordIndex = currentPageNo * recordCountPerPage;
		
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		returnMap.put("firstRecordIndex", firstRecordIndex);
		returnMap.put("lastRecordIndex", lastRecordIndex);
		
		return returnMap;
	}
}
