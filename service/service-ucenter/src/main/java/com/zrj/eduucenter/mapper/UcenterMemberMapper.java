package com.zrj.eduucenter.mapper;

import com.zrj.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author zrj
 * @since 2022-07-31
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    Integer countRegisterDay(String day);
}
