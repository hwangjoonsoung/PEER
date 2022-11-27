package peer.dao.wishlist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import peer.model.wishlist.WishlistBean;
import peer.model.house.HouseBean;

@Mapper
public interface WishlistMapper {
	
	public void addWl(WishlistBean WishlistBean) throws Exception;
	
	public List<WishlistBean> getWl(int user_num) throws Exception;
	
	public HouseBean getHnum(int house_num) throws Exception;

}
