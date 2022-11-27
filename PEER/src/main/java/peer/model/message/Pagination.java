package peer.model.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
	private int page;
	private int pageLimit;
	private int pageTotal;
	private int startPage;
	private int endPage;
	private int pagingstart;
	private int pagingend;
	
	public Pagination() {
		pageLimit = 10;
	}
	public Pagination(int msgTotal) {
		page = 1;
		pageLimit = 10;
		pageTotal = (msgTotal%pageLimit == 0)? msgTotal/pageLimit:(msgTotal/pageLimit) +1;
		startPage = msgTotal - ((page-1) * pageLimit);
		endPage = (startPage > 10)? startPage - pageLimit + 1 : 1;
		pagingstart = ((page-1)/5)* 5 + 1;
		pagingend = (pagingstart + 4 > pageTotal) ? pageTotal : pagingstart + 4;
	}
	public Pagination(int page, int msgTotal) {
		this.page = page;
		pageLimit = 10;
		pageTotal = (msgTotal%pageLimit == 0)? msgTotal/pageLimit:(msgTotal/pageLimit) +1;
		startPage = msgTotal - ((page-1) * pageLimit);
		endPage = (startPage > 10)? startPage - pageLimit + 1 : 1;
		pagingstart = ((page-1)/5)* 5 + 1;
		pagingend = (pagingstart + 4 > pageTotal) ? pageTotal : pagingstart + 4;
	}

}
