package peer.model.board;

import lombok.Data;

@Data
public class PageBean {
	private int page;				/* 현재 페이지 */
	private int recordList;			/* 1페이지 당 출력할 게시글 개수 */
	private int pageSize;			/* 하단 출력할 페이지 개수 */
	private int recordCount;		/* 총 게시글 개수 */
	private int pageCount;			/* 총 페이지 개수 */
	private int firstPage;			/* 페이지 첫 넘버 */
	private int lastPage;			/* 페이지막 마지막 넘버 */

	private int firstIndex;
	private int lastIndex;
	
	private boolean hasPreviousPage;	/* 이전 페이지 여부 */
	private boolean hasNextPage;		/* 다음 페이지 여부 */
	
	
	public PageBean(int page) {
		this.page = page;
		this.recordList = 20;
		this.pageSize = 5;
	}
	
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		
		if(recordCount > 0) {
			calculation();
		}
	}
	
	private void calculation() {
		
		/* 전체 페이지 수 */
		pageCount = ((recordCount - 1) / recordList) + 1;
		if(this.getPage() > pageCount) {
			this.setPage(pageCount);
		}
		
		/* 페이지 첫 번호 ..? */
		firstPage = ((this.getPage() - 1) / this.getPageSize()) * this.getPageSize() + 1;
		/* 페이지 마지막 번호 */
		lastPage = firstPage + this.getPageSize() - 1;
		if(lastPage > this.getPageCount()) {
			lastPage = this.getPageCount();
		}
		
		firstIndex = ((this.getPage() - 1) * this.getRecordList()) + 1;
		lastIndex = this.getPage() * this.getRecordList();
		
		/* 이전 페이지 존재 유무 */
		hasPreviousPage = firstPage == 1 ? false : true;
		if(hasPreviousPage == false) {
			if(page != firstPage) {
				hasPreviousPage = true;
			}
		}else {
			hasPreviousPage = false;
		}
		
		/* 다음 페이지 존재 유무 */
		hasNextPage = (lastPage * this.getRecordList()) >= recordCount ? false : true;
		if(hasNextPage == false) {
			if(page != lastPage) {
				hasNextPage = true;
			}
		}else {
			hasNextPage = false;
		}
	}
}
