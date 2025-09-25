package com.example.Traceability.Service;

import java.util.List;

import com.example.Traceability.DTO.LogisticsDTO;

public interface LogisticService {

	public LogisticsDTO createLogistic(LogisticsDTO logisticDTO);
	
	public List<LogisticsDTO> getAllLogistics();
	
	public LogisticsDTO getLogisticById(Long id);
	
	public LogisticsDTO updateLogistic(Long id, LogisticsDTO logisticDTO);
	
	public String deleteLogistic(Long id);
}
