package com.example.users.dto;

import java.util.List;

public record SecurityUserDTO(
		Long id,
		String name,
		String password,
		boolean active,
		List <String> role,
		List <String> email
		) {

}
