package br.com.calendar.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.calendar.domain.Calendar;
import br.com.calendar.domain.CalendarDTO;
import br.com.calendar.services.CalendarService;

@RestController
@RequestMapping(value="/calendar")
public class CalendarResource {
	
	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CalendarDTO>> list()
	{
		List<Calendar> calendar = calendarService.list();
		
		List<CalendarDTO> dto = calendar.stream().map(x -> new CalendarDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(value="/find", method = RequestMethod.GET)
	public ResponseEntity<List<CalendarDTO>> findList(@RequestParam(value="startTime")  @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime)
	{
		List<Calendar> calendar = calendarService.findList(startTime);
		
		List<CalendarDTO> dto = calendar.stream().map(x -> new CalendarDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Calendar calendar)
	{
		calendar = calendarService.create(calendar);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(calendar.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
