package com.NobleScan.NobleServer.REST;

public class BatchNotFoundException extends RuntimeException{
	BatchNotFoundException(Integer id) {
		super("Could not find batch " + id);
	}
}
