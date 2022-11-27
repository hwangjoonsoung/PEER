package peer.service.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.main.MainMapper;
import peer.model.main.MainBean;

@Service
public class MainServiceImpl implements MainService{
	@Autowired
	private MainMapper mainMapper; 
	
	public int getListCount() throws Exception {	     
	return mainMapper.getListCount();
	}
	
	public List getHouseList(int page) throws Exception {
		return mainMapper.getHouseList(page);
	}
	    
	public List<MainBean> search(String loc) throws Exception{
		return mainMapper.search(loc);
				
	}
}
