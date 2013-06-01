package com.angelH4ck;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: auvict
 * To change this template use File | Settings | File Templates.
 */
public class mainPage {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration = new Configuration();

        //the below indicates where to find the template relative to the class
        configuration.setClassForTemplateLoading(mainPage.class, "/");

        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        //let's hav one database for now.
        DB database = client.getDB("main");
        final DBCollection collection =  database.getCollection("people");


        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response Response) {
                StringWriter writer = new StringWriter();

                try {
                    Template mainTemplate = configuration.getTemplate("main.ftl");
                    return mainTemplate.toString();

                } catch (Exception e) {

                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

               return "";

            }

        });

        Spark.get(new Route("/echo/:thing"){
           @Override
            public Object handle(final Request request, final Response Response){
                    return request.params(":thing");
            }
        });

        Spark.get(new Route("/register"){
            @Override
            public Object handle(final Request request, final Response response){
                StringWriter writer = new StringWriter();
                Template mainTemplate;

                try {
                     mainTemplate = configuration.getTemplate("register.ftl");
                 //   Map<String, Object> emptyMap = new HashMap<String, Object>();
                   // mainTemplate.process(document, writer);
                    return mainTemplate.toString();

                } catch (Exception e) {

                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                return "";

            }
        });


        Spark.post(new Route("/register_2"){
            @Override
            public Object handle(final Request request, final Response response){
                StringWriter writer = new StringWriter();
                Template mainTemplate;

                try {
                    final String first_name = request.queryParams("first_name");
                    final String last_name = request.queryParams("last_name");
                    final String email_address = request.queryParams("email_address");
                    final String plan = request.queryParams("plan");
                    final String billing_method = request.queryParams("billing_method");
                    final String billing_address = request.queryParams("billing_address");
                    //we'll actually persist this later.

                    mainTemplate = configuration.getTemplate("register2.ftl");
                    Map<String, Object> register2Map = new HashMap<String, Object>();
                    register2Map.put("_id", "");
                    mainTemplate.process(register2Map, writer);
                    return mainTemplate.toString();

                } catch (Exception e) {

                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                return "";

            }
        });


        Spark.post(new Route("/signed_in"){
            @Override
            public Object handle(final Request request, final Response response){
                StringWriter writer = new StringWriter();
                Template mainTemplate;

                try {
                    final String key_address_name = request.queryParams("key_address_name");
                    final String key_address = request.queryParams("key_address");
                    final String key_address2_name = request.queryParams("key_address2_name");
                    final String key_address2 = request.queryParams("key_address2");
                    final String phone_num = request.queryParams("phone_num");
                    //we'll actually persist this later.


                    //mainTemplate = configuration.getTemplate("register2.ftl");
                    //   Map<String, Object> emptyMap = new HashMap<String, Object>();
                    // mainTemplate.process(document, writer);
                   // return mainTemplate.toString();

                    return "You're done! You can use your custom ID to start ordering!";

                } catch (Exception e) {

                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                return "";

            }
        });

        Spark.get(new Route("/PostTest"){
            @Override
            public Object handle(final Request request, final Response response){
                StringWriter writer = new StringWriter();
                try{
                    Map<String, Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits", Arrays.asList("apple", "orange", "sugar apple"));
                    Template fruitPickerTemplate = configuration.getTemplate("FruitPicker.ftl");

                    fruitPickerTemplate.process(fruitsMap, writer);
                    return writer;
                }
                catch(Exception e)
                {

                }
                return writer;
            }
        });

        Spark.post(new Route("/logged_in"){

           public Object handle(final Request request, final Response response)
           {
               try{
                     final String username = request.queryParams("user");
                    //lets ignore the password
                   if (username == null){
                       return "ERROR with username!";
                   }
                   else{
                       return "Hello " + username;
                   }
               }
               catch(Exception e)
               {
                             return null;
               }

           }
        });


    }

}
