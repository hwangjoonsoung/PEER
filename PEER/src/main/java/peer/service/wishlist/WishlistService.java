package peer.service.wishlist;

import java.util.List;

import peer.model.wishlist.WishlistBean;
import peer.model.house.HouseBean;

public interface WishlistService {
	public void addWl(WishlistBean WishlistBean) throws Exception;
	
	public List<WishlistBean> getWl(int user_num) throws Exception;
	
	public HouseBean getHnum(int house_num) throws Exception;

}
