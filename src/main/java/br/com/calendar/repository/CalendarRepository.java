package br.com.calendar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.calendar.domain.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer>  {

	@Query(value = "select * from calendar c where date_trunc('day',c.start_time) = :date order by c.start_time desc", nativeQuery = true)
	List<Calendar> findByStartTime(@Param("date") Date startTime);

}
