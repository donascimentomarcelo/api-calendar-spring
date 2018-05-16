package br.com.calendar.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calendar.domain.Calendar;
import br.com.calendar.exceptions.BadRequestException;
import br.com.calendar.repository.CalendarRepository;

@Service
public class CalendarService {
	
	@Autowired
	private CalendarRepository calendarRepository;
	
	public Calendar create(Calendar calendar)
	{
		calendar.setId(null);
		addThirtyMinutes(calendar);
		checkSlotBetweenDate(calendar);
		calendar.setName("Crane");
		return calendarRepository.save(calendar);
	}
	
	public void addThirtyMinutes(Calendar calendar)
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(calendar.getStartTime());
		cal.add(java.util.Calendar.MINUTE, 30);
		calendar.setEndTime(cal.getTime());
	}
	
	public void checkSlotBetweenDate(Calendar calendar)
	{
		Iterable<Calendar> cal = calendarRepository.findAll();
		
		for(Calendar key: cal)
		{
			Date start = key.getStartTime();
			Date end = key.getEndTime();
			Date selectedDateStart = calendar.getStartTime();
			Date selectedDateEnd = calendar.getEndTime();

			if(selectedDateStart.before(new Date()))
			{
				throw new BadRequestException("A data não pode ser menor que a atual!");
			}

			if(!selectedDateStart.before(convertTime(start)) && selectedDateStart.before(convertTime(end)) || !selectedDateEnd.before(convertTime(start)) && selectedDateEnd.before(convertTime(end)))
			{
				throw new BadRequestException("O período selecionado já está ocupado!");
			}
		}
	}
	
	public Date convertTime(Date date) 
	{    
		java.util.Calendar cal = java.util.Calendar.getInstance();  
	    cal.setTime(date);
	    return cal.getTime(); 
	}
	
	public List<Calendar> list()
	{
		List<Calendar> calendar = calendarRepository.findAll();
		return calendar;
	}
	
	public List<Calendar> findList(Date startTime)
	{
		List<Calendar> calendar = calendarRepository.findByStartTime(startTime);
		return calendar;
	}
}	


