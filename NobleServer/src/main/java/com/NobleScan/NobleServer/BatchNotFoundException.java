package com.NobleScan.NobleServer;

public class BatchNotFoundException extends RuntimeException{
	BatchNotFoundException(Integer id) {
		super("Could not find batch " + id);
	}
}
