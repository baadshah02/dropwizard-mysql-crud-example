package hu.holdinarms.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

//import hu.holdinarms.example.core.POJO;
import hu.holdinarms.example.core.Message;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class Mapper implements ResultSetMapper<Message>{
	@Override
	public Message map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
		return new Message(resultSet.getInt("id"), resultSet.getString("message"), resultSet.getInt("parent"));
	}

//	public POJO map(int i, ResultSet resultSet, StatementContext statementContext)throws SQLException {
//		return new POJO( Long.parseLong( resultSet.getString("id") ));
//	}

}
