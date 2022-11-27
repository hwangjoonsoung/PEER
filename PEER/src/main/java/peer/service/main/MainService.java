package peer.service.main;

import java.util.List;

import peer.dao.main.MainMapper;
import peer.model.main.MainBean;

public interface MainService {
	// 숙소 목록
	public int getListCount() throws Exception;

	public List getHouseList(int page) throws Exception;

	public List<MainBean> search(String loc) throws Exception;
	
}
