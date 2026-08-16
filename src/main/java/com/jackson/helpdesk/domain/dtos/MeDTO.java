package com.jackson.helpdesk.domain.dtos;

import java.io.Serializable;
import java.util.List;

public class MeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private List<String> perfis;

	public MeDTO() {
		super();
	}

	public MeDTO(String email, List<String> perfis) {
		super();
		this.email = email;
		this.perfis = perfis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}

}
