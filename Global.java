import play.*;
import play.libs.Akka;
import java.util.concurrent.TimeUnit;

import models.WebService;
import scala.concurrent.duration.Duration;

import redis.clients.jedis.*;

public class Global extends GlobalSettings {

	private Jedis jedis;

	public void onStop(Application app) {
		if (jedis != null)
			jedis.close();
	}
	public void onStart(Application app) {

		jedis = new Jedis("pub-redis-11620.us-east-1-3.3.ec2.garantiadata.com",11620);
		jedis.connect();
		jedis.auth("rad1ss3");

		//subscribe to the message channel
		Akka.system().scheduler().scheduleOnce(
				Duration.create(10, TimeUnit.MILLISECONDS),
				new Runnable() {
					public void run() {
						jedis.subscribe(new Listener(), "Falcon2");
					}
				},
				Akka.system().dispatcher()
				);
	}
	public static class Listener extends JedisPubSub {
		private Jedis jedis;

		@Override
		public void onMessage(String channel, String messageBody) {
			//Process messages from the pub/sub channel

			Jedis jedis = new Jedis("pub-redis-11620.us-east-1-3.3.ec2.garantiadata.com",11620);
			jedis.connect();
			jedis.auth("rad1ss3");

			jedis.lpush("store", messageBody);

			jedis.close();
		}
		@Override
		public void onPMessage(String arg0, String arg1, String arg2) {
		}
		@Override
		public void onPSubscribe(String arg0, int arg1) {
		}
		@Override
		public void onPUnsubscribe(String arg0, int arg1) {
		}
		@Override
		public void onSubscribe(String arg0, int arg1) {
		}
		@Override
		public void onUnsubscribe(String arg0, int arg1) {
		}
	}
}

