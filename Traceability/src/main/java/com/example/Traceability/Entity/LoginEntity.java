package com.example.Traceability.Entity;

import org.hibernate.annotations.GenericGenerator;

import com.example.Traceability.Constant.RoleManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TC_USERS")
public class LoginEntity {

	@Id
	@GeneratedValue(generator = "user-id-generator")
	@GenericGenerator(
	    name = "user-id-generator",
	    strategy = "com.example.Traceability.Util.UserIdGenerator"
	)
	private String id;

	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RoleManagement userRole; 
    
}
