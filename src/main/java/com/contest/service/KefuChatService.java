package com.contest.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.mapper.KefuChatModelMapper;
import com.contest.model.KefuChatModel;

@Service
public class KefuChatService {
	
	@Autowired
	private KefuChatModelMapper kefuChatModelMapper;

	public Integer addChatContent(String cont, String session_id) {
		
		KefuChatModel kefuChatModel = new KefuChatModel();
		kefuChatModel.setChatCont(cont);
		kefuChatModel.setSessionId(session_id);
		kefuChatModel.setSendTime(new Date());
		return kefuChatModelMapper.insertSelective(kefuChatModel);
	}

}
