package hu.holdinarms.example.dao;

import hu.holdinarms.example.core.Message;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.*;

import java.util.List;

public interface MyDao {

	@SqlQuery("SELECT id, message, parent FROM top_hat.tblMessage")
	@org.skife.jdbi.v2.sqlobject.customizers.Mapper(Mapper.class)
	List<Message> getAllMessages();

	@SqlUpdate("INSERT INTO top_hat.tblMessage (message, parent) VALUES (:message, :parentId)")
	void postMessage(@Bind("parentId") Integer parentId, @Bind("message") String message);
}
