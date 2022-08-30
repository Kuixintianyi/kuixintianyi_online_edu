package com.zrj.eduucenter.service;

import com.zrj.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrj.eduucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-07-31
 */
public interface UcenterMemberService extends IService<UcenterMember> {
      String login(UcenterMember member);

      void register(RegisterVo registerVo);

    UcenterMember getByOpenid(String openid);

    Integer countRegisterDay(String day);
}
