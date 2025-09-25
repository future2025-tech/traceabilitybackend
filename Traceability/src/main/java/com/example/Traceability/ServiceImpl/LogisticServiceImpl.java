package com.example.Traceability.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Traceability.DTO.LogisticsDTO;
import com.example.Traceability.Entity.LogisticsEntity;
import com.example.Traceability.Repository.LogisticsRepository;
import com.example.Traceability.Service.LogisticService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogisticServiceImpl implements LogisticService {

	private final LogisticsRepository logisticRepo;
	private final ModelMapper modelMapper;
	
	@Override
	public LogisticsDTO createLogistic(LogisticsDTO logisticDTO) {

		LogisticsEntity entity = modelMapper.map(logisticDTO, LogisticsEntity.class);
		entity.setTimeStamp(LocalDateTime.now().withNano(0));
		
		LogisticsEntity savedEntity = logisticRepo.save(entity);
		
		return modelMapper.map(savedEntity, LogisticsDTO.class);
	}

	@Override
	public List<LogisticsDTO> getAllLogistics() {

		return logisticRepo.findAll().stream().map(entity -> 
		modelMapper.map(entity, LogisticsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public LogisticsDTO getLogisticById(Long id) {
		
		LogisticsEntity entity = logisticRepo.findById(id).orElseThrow(
				() -> new RuntimeException("Logistic ID not found"));
		
		return modelMapper.map(entity, LogisticsDTO.class);
	}

	@Override
	public LogisticsDTO updateLogistic(Long id, LogisticsDTO logisticDTO) {

		LogisticsEntity entity = logisticRepo.findById(id).orElseThrow(
				() -> new RuntimeException("Logistic ID Not Found"));
		
		entity.setLogisticName(logisticDTO.getLogisticName());
		entity.setLogisticType(logisticDTO.getLogisticType());
		entity.setTrasportMode(logisticDTO.getTrasportMode());
		entity.setEventType(logisticDTO.getEventType());
		entity.setLogisticPartner(logisticDTO.getLogisticPartner());
		entity.setSourceLocation(logisticDTO.getSourceLocation());
		entity.setDestinationlocation(logisticDTO.getDestinationlocation());
		entity.setExpectedDeliveryDate(logisticDTO.getExpectedDeliveryDate());
		
		return modelMapper.map(entity, LogisticsDTO.class);
	}

	@Override
	public String deleteLogistic(Long id) {
		
		logisticRepo.deleteById(id);
		
		return "Logistics Deleted Successfully...!";
	}

}
