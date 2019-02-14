package com.eternallyc.blogproject.mapper;

import com.eternallyc.blogproject.bean.Picture;
import org.apache.ibatis.annotations.Select;

//图片Mapper
public interface PictureMapper {

    @Select("select * from picture where picture_id=#{id}")
    public Picture getPicture(Integer id);
}
