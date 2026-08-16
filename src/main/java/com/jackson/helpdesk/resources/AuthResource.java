package com.jackson.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jackson.helpdesk.domain.dtos.MeDTO;

@RestController
public class AuthResource {

	@GetMapping(value = "/me")
	public ResponseEntity<MeDTO> me(Authentication authentication) {
		List<String> perfis = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.map(authority -> authority.replace("ROLE_", ""))
				.collect(Collectors.toList());

		return ResponseEntity.ok(new MeDTO(authentication.getName(), perfis));
	}

}
