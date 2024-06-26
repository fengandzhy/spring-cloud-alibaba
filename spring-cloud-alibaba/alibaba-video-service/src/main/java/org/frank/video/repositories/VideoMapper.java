package org.frank.video.repositories;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.frank.domains.Video;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMapper {
    
    /**
     * @Param("id") 明确指出方法参数 id 应该替换 SQL 查询中的 #{id}，
     * @Param 里面的id 必须和占位符里面的id 名称相同
     * */
    @Select("select * from video where id=#{id}")
    Video findById(@Param("id") int id); 
}
