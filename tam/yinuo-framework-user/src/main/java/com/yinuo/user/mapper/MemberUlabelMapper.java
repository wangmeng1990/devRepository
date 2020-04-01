package com.yinuo.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.inuol.user.MemberUlabel;
import com.inuol.user.Ulabel;

import tk.mybatis.mapper.common.Mapper;

public interface MemberUlabelMapper extends Mapper<MemberUlabel> {

	@Select({ "SELECT b.* FROM t_member_ulabel a JOIN t_ulabel b on a.ulabel_id=b.id",
			"WHERE a.is_valid=1 AND b.is_valid=1 AND a.user_id=#{userId}" })
	List<Ulabel> queryUlabelsByUserId(@Param("userId") Long userId);

	@Update({ "<script>",
			" update t_member_ulabel set update_time=now(), is_valid=2 where is_valid=1 and user_id=#{userId} and ulabel_id in ",
			"<foreach collection='ulabelIds' item='id' open='(' separator=',' close=')'>", "#{id}", "</foreach>",
			"</script>" })
	int deleteMemberUlabels(@Param("userId") Long userId, @Param("ulabelIds") List<String> ulabelIds);

}
