package hu.holdinarms.example.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.holdinarms.example.core.Message;
import hu.holdinarms.example.dao.MyDao;

@Path("top_hat")
public class Resource {

  private MyDao myDao;

  public Resource(MyDao myDao) {
    this.myDao = myDao;
  }

  @GET
  @Path("/get_messages")
  @Produces({MediaType.APPLICATION_JSON})
  public boolean getAllThread() {
//    return new Message(1, "Hi", null);
    List<Message> messages = myDao.getAllMessages();
    if (messages.size() > 0) {
      System.out.println(messages.get(0).getMessage());
    }
    return true;
  }

  @PUT
  @Path("/post_message/{parentId}/{message}")
  public boolean postMessage(@PathParam("parentId") Integer parentId, @PathParam("message") String message) {
    System.out.println(parentId);
    System.out.println(message);
    myDao.postMessage(parentId, message);
    return true;
  }
}
