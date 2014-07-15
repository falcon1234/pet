package controllers;

import static java.util.concurrent.TimeUnit.SECONDS;

import play.*;
import play.mvc.*;


import play.libs.Akka;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import scala.concurrent.duration.Duration;
import views.html.*;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;


import views.html.*;
import models.*;

public class Application extends Controller {
	  
    // get the ws.js script
    public static Result wsJs() {
        return ok(views.js.ws.render());
    }
    
    // Websocket interface
    public static WebSocket<String> wsInterface(){
        return new WebSocket<String>(){
            
            // called when WbSocket handshake is done
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out){
                WebService.start(in, out);
            }
        };   
    }   
    public static Result index() {
        return ok(index.render(""));
    }
}
