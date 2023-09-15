package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.Result;
import com.example.entity.Blog;

public interface IBlogService2 extends IService<Blog> {

    Result queryBlogById(Long id);


    /**
     * 喜欢的笔记
     *
     * @param id
     * @return
     */
    Result likeBlog(Long id);

    /**
     * 喜欢的列表
     *
     * @param id
     * @return
     */
    Result queryBlogLikes(Long id);


}
