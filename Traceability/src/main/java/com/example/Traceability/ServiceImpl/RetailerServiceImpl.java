package com.example.Traceability.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Traceability.DTO.RetailerDTO;
import com.example.Traceability.Entity.RetailerEntity;
import com.example.Traceability.Repository.RetailerRepository;
import com.example.Traceability.Service.RetailerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RetailerServiceImpl implements RetailerService {
	
    private final RetailerRepository retailerRepo;
    private final ModelMapper modelMapper;

    @Override
    public RetailerDTO createRetailer(RetailerDTO retailerDTO) {
    	
        RetailerEntity entity = modelMapper.map(retailerDTO, RetailerEntity.class);
        entity.setTimeStamp(LocalDateTime.now().withNano(0)); 
        
        RetailerEntity savedEntity = retailerRepo.save(entity);
        
        return modelMapper.map(savedEntity, RetailerDTO.class);
    }

    @Override
    public RetailerDTO getRetailerById(Long id) {
    	
        RetailerEntity entity = retailerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Incorrect ID of the Retailer"));
        
        return modelMapper.map(entity, RetailerDTO.class);
    }

    @Override
    public List<RetailerDTO> getAllRetailers() {
    	
        return retailerRepo.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, RetailerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RetailerDTO updateRetailer(Long id, RetailerDTO retailerDTO) {
    	
        RetailerEntity entity = retailerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ID not found"));

        entity.setRetailerName(retailerDTO.getRetailerName());
        entity.setRetailerType(retailerDTO.getRetailerType());
        entity.setRetailerAddress(retailerDTO.getRetailerAddress());
        entity.setQuantitySupplied(retailerDTO.getQuantitySupplied());
        entity.setSelectProduct(retailerDTO.getSelectProduct());
        entity.setContactPersonName(retailerDTO.getContactPersonName());
        entity.setContactPersonNumber(retailerDTO.getContactPersonNumber());
        entity.setEventType(retailerDTO.getEventType());

        RetailerEntity updatedEntity = retailerRepo.save(entity);
        
        return modelMapper.map(updatedEntity, RetailerDTO.class);
    }

    @Override
    public String deleteRetailerById(Long id) {
    	
        if (!retailerRepo.existsById(id)) {
            throw new RuntimeException("Retailer not found with ID: " + id);
        }
        retailerRepo.deleteById(id);
        return "Retailer Deleted Successfully...!";
    }
}
