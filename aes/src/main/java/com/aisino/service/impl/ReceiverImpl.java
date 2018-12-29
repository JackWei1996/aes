package com.aisino.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisino.mapper.ReceiverMapper;
import com.aisino.pojo.Receiver;
import com.aisino.pojo.ReceiverExample;
import com.aisino.service.IReceiver;

@Service
public class ReceiverImpl implements IReceiver {
	@Autowired
	private ReceiverMapper receiverMapper;

	@Override
	public List<Receiver> queryAllReceiver() {
		return receiverMapper.queryAllReceiver();
	}

	@Override
	public int addReceiver(Receiver receiver) {
		return receiverMapper.insert(receiver);
	}

	@Override
	public int updateByExampleSelective(Receiver record, ReceiverExample example) {
		return receiverMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<Receiver> selectByExample(ReceiverExample example) {
		return receiverMapper.selectByExample(example);
	}

}
