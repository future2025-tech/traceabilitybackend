package com.example.Traceability.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Traceability.DTO.LogisticsDTO;
import com.example.Traceability.ServiceImpl.LogisticServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/logistic")
@AllArgsConstructor
public class LogisticsController {

	private final LogisticServiceImpl logisticService;
	
	@PostMapping
	public LogisticsDTO createLogistic(@RequestBody LogisticsDTO logisticDTO) {
		
		return logisticService.createLogistic(logisticDTO);
	}
	
	@GetMapping("/{id}")
	public LogisticsDTO getLogisticById(@PathVariable Long id) {
		
		return logisticService.getLogisticById(id);
	}
	
	@GetMapping
	public List<LogisticsDTO> getLogisticsList(){
		
		return logisticService.getAllLogistics();
	}
	
	@PutMapping("/{id}")
	public LogisticsDTO updateLogistic(@PathVariable Long id,
			@RequestBody LogisticsDTO logisticsDTO) {
		
		return logisticService.updateLogistic(id, logisticsDTO);
	}
	
	@DeleteMapping("/{id}")
	public String deleteLogistic(@PathVariable Long id) {
		
		logisticService.deleteLogistic(id);
		
		return "Logistics Deleted Successfully";
	}
}
