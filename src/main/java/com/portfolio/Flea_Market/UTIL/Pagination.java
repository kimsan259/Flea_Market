package com.portfolio.Flea_Market.UTIL;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pagination {

	private static final Logger logger = LoggerFactory.getLogger(Pagination.class);
	
	//�� �������� �ԽõǴ� �Խù� �� ��
	private final int defaultRecordCountPerPage = 3;
	
	//������ ����Ʈ�� �ԽõǴ� ������ �� ��
	private final int defaultPageSize = 5;
	
	
	/*
	 * �Խ��� ����¡ ������ �ʿ��� ��ġ���� ����.
	 * curPageNo = ���� ������ ��ȣ
	 * recordCountPerPage = �� �������� �ԽõǴ� �Խù� �� ��
	 * pageSize = ������ ����Ʈ�� �ԽõǴ� ������ �Ǽ�
	 * totalRecordCount = ��ü �Խù� �� ��
	 *
	 */
	public HashMap<String, Integer> calcPagerElement(int curPageNo, int totalRecordCount, int recordCountPerPage, int pageSize){
		
		int currentPageNo = curPageNo <= 1 ? 1 : curPageNo;
		recordCountPerPage = recordCountPerPage < 1 ? defaultRecordCountPerPage : recordCountPerPage;
		pageSize = pageSize < 1 ? defaultPageSize : pageSize;
		
		// ������ ����
		int totalPageCount = ((totalRecordCount - 1) / recordCountPerPage) + 1;
		
		// ������ ����Ʈ�� ù ������ ��ȣ
		int firstPageNoOnPageList = ((currentPageNo - 1) / pageSize) * pageSize + 1;
		
		// ������ ����Ʈ�� ������ ������ ��ȣ
		int lastPageNoOnPageList = firstPageNoOnPageList + pageSize - 1;
		
		// ���� ������
		int prevPageNoOnPageList = firstPageNoOnPageList - 1 < 1 ? 1 : firstPageNoOnPageList - 1;
		
		// ���� ������
		int nextPageNoOnPageList = lastPageNoOnPageList + 1;
		
		//������ ����Ʈ�� ������ ������ ��ȣ�� ��ü ������ ������ ū�� üũ
		logger.debug("");
		logger.debug("������ ����Ʈ�� ������ ������ ��ȣ�� ��ü ������ ������ ū�� üũ");
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
		returnMap.put("startPageNoOnPageList", 1); //ó�� ������
		returnMap.put("endPageNoOnPageList", totalPageCount); //�� ������
		
		return returnMap;
	}
	
	/*
	 * �Խù� ��ȸ ���� ����.
	 * curPageNo = ���� ������ ��ȣ
	 * recordCountPerPage = �� �������� �ԽõǴ� �Խù� �� ��
	 * pageSize = ������ ����Ʈ�� �ԽõǴ� ������ �Ǽ�
	 * totalRecordCount = ��ü �Խù� �� ��
	 *
	 */
	public HashMap<String, Integer> calcDataRange(int curPageNo, int recordCountPerPage){
		
		int currentPageNo = curPageNo <= 1 ? 1 : curPageNo;
		
		recordCountPerPage = recordCountPerPage < 1 ? defaultRecordCountPerPage : recordCountPerPage;
		
		// ����¡ SQL�� start
		int firstRecordIndex = ((currentPageNo - 1) * recordCountPerPage) + 1;
		
		// ����¡ SQL�� end
		int lastRecordIndex = currentPageNo * recordCountPerPage;
		
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		returnMap.put("firstRecordIndex", firstRecordIndex);
		returnMap.put("lastRecordIndex", lastRecordIndex);
		
		return returnMap;
	}
}
