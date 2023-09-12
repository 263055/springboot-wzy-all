package com.demo.chain.service;

import com.demo.chain.entity.Clue;

/**
 * 重复资源处理
 */
public interface RepeatClueHandler {
    Clue handler(Clue clue);
}
