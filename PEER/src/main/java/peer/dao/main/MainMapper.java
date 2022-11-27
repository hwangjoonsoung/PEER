package peer.dao.main;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import peer.model.main.MainBean;

@Mapper
public interface MainMapper {
	public int getListCount() throws Exception;

	public List<MainBean> getHouseList(int page) throws Exception;
	
	public List<MainBean> search(@Param("loc")String loc) throws Exception;
}
