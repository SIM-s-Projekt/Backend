package com.NobleScan.NobleServer.API;

public class SettingNotFoundException extends RuntimeException {
	public SettingNotFoundException(String setting) {
		super("Could not find setting " + setting);
	}
}
