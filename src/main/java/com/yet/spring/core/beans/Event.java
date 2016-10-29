package com.yet.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
	
	private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
	
	private int id;
	private String msg;
	private Date date;

	private DateFormat dateFormat;
	
	public Event(Date date, DateFormat df) {
		this.id = AUTO_ID.getAndIncrement();
		
		this.date = date;
		this.dateFormat = df;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", msg=" + msg + ", date=" + dateFormat.format(date) + "]";
	}

}
