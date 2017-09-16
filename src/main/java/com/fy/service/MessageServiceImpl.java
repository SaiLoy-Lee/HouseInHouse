package com.fy.service;

import com.fy.mapper.MessageMapper;
import com.fy.pojo.SMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryp on 2017/9/15.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void saveMessage(SMessage sMessage) {
        messageMapper.saveMessage(sMessage);
    }
}
