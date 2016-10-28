package org.vsg.rmodel.search.service.impl;

import org.vsg.common.async.AsyncResult;

public class VoidAsyncResult implements AsyncResult<Void> {

	@Override
	public Void result() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Throwable cause;
	
	
	public void setExceptionCause(Throwable cause) {
		this.cause = cause;
	}
	

	@Override
	public Throwable cause() {
		// TODO Auto-generated method stub
		return cause;
	}
	
	private boolean succeed = false;
	
	
	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	

	@Override
	public boolean succeeded() {
		// TODO Auto-generated method stub
		return succeed;
	}

	@Override
	public boolean failed() {
		// TODO Auto-generated method stub
		return !succeed;
	}

}
