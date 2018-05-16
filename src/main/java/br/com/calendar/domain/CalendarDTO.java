package br.com.calendar.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CalendarDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@JsonFormat(pattern="HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date startTime;
	@JsonFormat(pattern="HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date endTime;
	private String name;
	
	public CalendarDTO(Calendar x) 
	{
		this.id = x.getId();
		this.startTime = x.getStartTime();
		this.endTime = x.getEndTime();
		this.name = x.getName();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}