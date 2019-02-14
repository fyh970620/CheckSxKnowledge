package com.ffcs.itm.web.sso.respository;

import com.ffcs.itm.web.respository.MyBatisRepository;
import com.ffcs.itm.web.sso.entity.ItmSsoCfg;
import com.ffcs.itm.web.sso.entity.ItmSsoKey;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface TicketMapper {
	ItmSsoCfg getSsoCfg();
	
	void updateSsoKey(ItmSsoKey ssoKey);
	
	ItmSsoKey getSsoKey(@Param("isCurrentDate") boolean isCurrentDate);
}
