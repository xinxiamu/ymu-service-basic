package com.ymu.servicecommon.config.ds;

import com.ymu.framework.dao.ds.AbstractDataSourceAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *  解析注入的数据源。
 */
@Aspect
@Component
public class DynamicDataSourceAspect extends AbstractDataSourceAspect {

	@Override
	public String defaultDb() {
		return DSType.YMU_DIC_MASTER;
	}
}
