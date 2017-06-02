package com.mehome.exceptions;

import java.sql.SQLException;

public class DatabaseException extends BaseException {
	private static final long serialVersionUID = 7461040598776735655L;
	public DatabaseException(SQLException e) {
		super(e);
	}
	protected DatabaseException() {
	}
	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return "DB0010";
	}

}
