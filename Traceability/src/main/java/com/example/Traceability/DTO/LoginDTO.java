package com.example.Traceability.DTO;

import com.example.Traceability.Constant.RoleManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

	private String id;

	private String email;

	private String password;
	
	private RoleManagement userRole;
}
