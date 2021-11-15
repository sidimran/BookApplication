package com.zkteco.book.delete.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessCount {

	private int successCount;
	private Object success;
}
