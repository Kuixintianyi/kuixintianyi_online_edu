package com.zrj.edumsm.service;

import java.util.Map;

/**
 * @ClassName: MsmService
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/30
 * @Version 1.0
 */
public interface MsmService {
      public boolean send(String phoneNumbers,String code);
}
