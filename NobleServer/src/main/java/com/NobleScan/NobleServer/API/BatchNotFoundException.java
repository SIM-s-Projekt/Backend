package com.NobleScan.NobleServer.API;

public class BatchNotFoundException extends RuntimeException{
	BatchNotFoundException(Integer id) {
		super("Could not find batch " + id);
	}
}
