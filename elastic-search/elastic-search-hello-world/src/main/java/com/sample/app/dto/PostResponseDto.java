package com.sample.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostResponseDto extends PostRequestDto {
	private String id;

}
