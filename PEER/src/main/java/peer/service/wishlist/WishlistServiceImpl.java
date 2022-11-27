package peer.service.wishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.wishlist.WishlistMapper;
import peer.model.house.HouseBean;
import peer.model.wishlist.WishlistBean;

@Service
public class WishlistServiceImpl implements WishlistService{
	
	@Autowired 
	private WishlistMapper wm;
	

	@Override
	public void addWl(WishlistBean WishlistBean) throws Exception {
		wm.addWl(WishlistBean);
		
	}

	@Override
	public HouseBean getHnum(int house_num) throws Exception {
		return wm.getHnum(house_num);
	}

	@Override
	public List<WishlistBean> getWl(int user_num) throws Exception {
		return wm.getWl(user_num);
	}
	
	

}
