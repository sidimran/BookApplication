package com.zkteco.book.delete.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorCount {

	private int errorCount;
	private Object failure;

}
