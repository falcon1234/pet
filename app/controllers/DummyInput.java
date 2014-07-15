package controllers;

import models.WebService;
import play.*;
import play.mvc.*;

import com.fasterxml.jackson.databind.JsonNode;
import redis.clients.jedis.*;

public class DummyInput extends Controller {
	public static Result receive() {
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");
		} else {
			String name = json.findPath("name").textValue();
			if(name == null) {
				return badRequest("Missing parameter [name]");
			} else {
				Jedis jedis = new Jedis("pub-redis-11620.us-east-1-3.3.ec2.garantiadata.com",11620);
				jedis.connect();
				jedis.auth("rad1ss3");
				
				// Publish to channel
				String channel = "Falcon2";
				String message = name;
				jedis.publish(channel, message);
				jedis.close();
				
				// Push messageBody to all WebSocket listeners
	            WebService.notifyAll(name);

				return ok("ok") ;
			}
		}
	}
}
