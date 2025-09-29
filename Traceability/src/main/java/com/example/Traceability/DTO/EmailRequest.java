package com.example.Traceability.DTO;

import com.example.Traceability.Constant.RoleManagement;

import lombok.Data;

@Data
public class EmailRequest {

	private String email;
	
	private RoleManagement role;
}
