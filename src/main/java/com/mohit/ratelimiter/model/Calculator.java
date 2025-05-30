package com.mohit.ratelimiter.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Calculator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String operation;
	private int answer;

}
