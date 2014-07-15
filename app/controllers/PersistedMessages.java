package controllers;

import java.util.List;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.*;
import play.mvc.*;
import redis.clients.jedis.Jedis;

public class PersistedMessages extends Controller {
	public static Result receive() {
		ObjectNode result = Json.newObject();

		Jedis jedis = new Jedis("pub-redis-11620.us-east-1-3.3.ec2.garantiadata.com",11620);
		jedis.connect();
		jedis.auth("rad1ss3");

		// Return last 10 persisted messages received on Dummy Input endpoints
		List<String> range = jedis.lrange("store", 0, 10);

		result.put("persisted-data", Json.toJson(range));
		jedis.close();
		
		return ok(result);
	}
}
