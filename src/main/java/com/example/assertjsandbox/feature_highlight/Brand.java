package com.example.assertjsandbox.feature_highlight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	private String name;

	private String designer;

	private Gender gender;
}
