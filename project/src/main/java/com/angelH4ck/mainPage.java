package com.angelH4ck;

import com.mashape.unirest.http.HttpResponse;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.json.JSONException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.*;

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


        Spark.post(new Route("/register_2") {
            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                Template mainTemplate;

                try {
                    final String first_name = request.queryParams("first_name");
                    final String user_name = request.queryParams("user_name");
                    final String last_name = request.queryParams("last_name");
                    final String email_address = request.queryParams("email_address");
                    final String plan = request.queryParams("plan");
                    final String pass_word = request.queryParams("pass_word");

                    HttpResponse<JsonNode> Emailrequest = Unirest.get("https://pozzad-email-validator.p.mashape.com/emailvalidator/validateEmail/"+email_address)
                            .header("X-Mashape-Authorization", "saEBgTRPpQskQMx5lyTrxylsjxSYGdDk")
                            .asJson();

                    HttpResponse<JsonNode> passWordrequest = Unirest.get("https://pozzad-passwords.p.mashape.com/passwordtools/hash/"+pass_word)
                            .header("X-Mashape-Authorization", "saEBgTRPpQskQMx5lyTrxylsjxSYGdDk")
                            .asJson();

                        if(!Emailrequest.getBody().getObject().getString("isValid").equals("false"))
                        {

                            //dont care about the below for now
                            final String billing_method = request.queryParams("billing_method");
                            final String billing_address = request.queryParams("billing_address");
                            //we'll actually persist this later.

                            BasicDBObject newUser = new BasicDBObject("first_name", first_name);
                            newUser.put("last_name", last_name);
                            newUser.put("email_address", email_address);
                            newUser.put("plan_type", plan);
                            newUser.put("user_name", user_name);
                            newUser.put("pass_word", passWordrequest.getBody().getObject().getString("hash"));

                            collection.insert(newUser);

                            mainTemplate = configuration.getTemplate("register2.ftl");
                            Map<String, Object> register2Map = new HashMap<String, Object>();

                            Random random = new Random();
                            int ranNum = random.nextInt(123);
                            String id = ranNum+"xYzO";
                            register2Map.put("_id", id);
                            register2Map.put("user_name", user_name);
                            register2Map.put("first_name", first_name);

                            mainTemplate.process(register2Map, writer);

                            return writer;
                        }
                        return "ERROR: email address is wrong";


                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (TemplateException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }catch (Exception e) {

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
                    final String user_name = request.queryParams("user_name");
                    final String key_address_name = request.queryParams("key_address_name");
                    final String key_address = request.queryParams("key_address");
                    final String key_address2_name = request.queryParams("key_address2_name");
                    final String key_address2 = request.queryParams("key_address2");
                    //what am i doing with the phone number?
                    final String phone_num = request.queryParams("phone_num");
                    //we'll actually persist this later.

                     BasicDBObject query = new BasicDBObject("user_name", user_name);
                     DBObject document = collection.findOne(query);

                     BasicDBList addresses = new BasicDBList();


                     DBObject address1 = new BasicDBObject("address_name",key_address_name);
                     address1.put("address", key_address);



                     DBObject address2 = new BasicDBObject("address_name",key_address2_name);
                     address2.put("address", key_address2);

                    HttpResponse<JsonNode> addressRequest1 = Unirest.get("https://montanaflynn-geocode-location-information.p.mashape.com/address?address="+key_address.replaceAll(",",""))
                            .header("X-Mashape-Authorization", "saEBgTRPpQskQMx5lyTrxylsjxSYGdDk")
                            .asJson();

                    HttpResponse<JsonNode> addressRequest2 = Unirest.get("https://montanaflynn-geocode-location-information.p.mashape.com/address?address="+key_address2.replaceAll(",",""))
                            .header("X-Mashape-Authorization", "saEBgTRPpQskQMx5lyTrxylsjxSYGdDk")
                            .asJson();


                    String address1Lat = addressRequest1.getBody().getObject().getString("latitude");
                    String address1Long = addressRequest1.getBody().getObject().getString("longitude");

                    String address2Lat = addressRequest2.getBody().getObject().getString("latitude");
                    String address2Long = addressRequest2.getBody().getObject().getString("longitude");

                    HttpResponse<JsonNode> addMaprequest1 = Unirest.get("https://orfeomorello-static-map.p.mashape.com/mashape/staticimagemap/lat/"+address1Lat+"/lng/"+address1Long+"/provider/Google?height=200&key=%3Cnumeric%20or%20alphanumericstring%3E&maptype=roadmap&width=300&zoom=13")
                            .header("X-Mashape-Authorization", "saEBgTRPpQskQMx5lyTrxylsjxSYGdDk")
                            .asJson();

                    HttpResponse<JsonNode> addMaprequest2 = Unirest.get("https://orfeomorello-static-map.p.mashape.com/mashape/staticimagemap/lat/"+address2Lat+"/lng/"+address1Long+"/provider/Google?height=200&key=%3Cnumeric%20or%20alphanumericstring%3E&maptype=roadmap&width=300&zoom=13")
                            .header("X-Mashape-Authorization", "saEBgTRPpQskQMx5lyTrxylsjxSYGdDk")
                            .asJson();

                    String addMapImageURL1 = addMaprequest1.getBody().getObject().getString("imageUrl");
                    String addMapImageURL2 = addMaprequest2.getBody().getObject().getString("imageUrl");


                    address1.put("mapURL", addMapImageURL1);
                    address2.put("mapURL", addMapImageURL2);

                    addresses.add(address1);

                    addresses.add(address2);




                    document.put("addresses",addresses );

                    collection.save(document);



                    Map<String, Object> accountsMap = new HashMap<String, Object>();

                    BasicDBList deliveries = (BasicDBList)document.get("deliveries");
                    BasicDBList notifications = (BasicDBList)document.get("notification");

                    String firstName = (String)document.get("first_name");


                    accountsMap.put("addresses", addresses);

                    if(deliveries== null)
                        accountsMap.put("deliveries", new ArrayList());
                    else
                        accountsMap.put("deliveries", deliveries);


                    if(notifications== null)
                        accountsMap.put("notifications", new ArrayList());
                    else
                        accountsMap.put("notifications",notifications);


                    accountsMap.put("firstname", firstName);



                    Template accountTemplate = configuration.getTemplate("account.ftl");

                    accountTemplate.process(accountsMap, writer);

                } catch (Exception e) {

                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                return writer;

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


        Spark.get(new Route("/Contact"){
            @Override
            public Object handle(final Request request, final Response response){
                StringWriter writer = new StringWriter();
                try{
                    Template contactTemplate = configuration.getTemplate("contact.ftl");

                    //   Map<String, Object> emptyMap = new HashMap<String, Object>();
                    // mainTemplate.process(document, writer);
                    return contactTemplate.toString();
                }
                catch(Exception e)
                {

                }
                return writer;
            }
        });

        Spark.get(new Route("/FAQ"){
            @Override
            public Object handle(final Request request, final Response response){
                StringWriter writer = new StringWriter();
                try{
                    Template faqTemplate = configuration.getTemplate("faq.ftl");

                    //   Map<String, Object> emptyMap = new HashMap<String, Object>();
                    // mainTemplate.process(document, writer);
                    return faqTemplate.toString();
                }
                catch(Exception e)
                {

                }
                return writer;
            }
        });


        Spark.get(new Route("/settings"){
            @Override
            public Object handle(final Request request, final Response response){
                StringWriter writer = new StringWriter();
                try{
                    Template settingsTemplate = configuration.getTemplate("settings.ftl");

                    //   Map<String, Object> emptyMap = new HashMap<String, Object>();
                    // mainTemplate.process(document, writer);
                    return settingsTemplate.toString();
                }
                catch(Exception e)
                {

                }
                return writer;
            }
        });


        Spark.post(new Route("/logged_in"){
           @Override
           public Object handle(final Request request, final Response response)
           {
               StringWriter writer = new StringWriter();
               try{
                     final String username = request.queryParams("user");
                   //grab the user
                    //lets ignore the password


                    BasicDBObject query = new BasicDBObject("user_name", username);
                    DBObject document = collection.findOne(query);


                   Map<String, Object> accountsMap = new HashMap<String, Object>();

                   BasicDBList deliveries = (BasicDBList)document.get("deliveries");
                    BasicDBList addresses = (BasicDBList)document.get("addresses");
                    BasicDBList notifications = (BasicDBList)document.get("notification");

                   String firstName = (String)document.get("first_name");


                   if(deliveries== null)
                       accountsMap.put("deliveries", new ArrayList());
                   else
                       accountsMap.put("deliveries", deliveries);


                   if(notifications== null)
                       accountsMap.put("notifications", new ArrayList());
                   else
                       accountsMap.put("notifications",notifications);

                   accountsMap.put("addresses", addresses);
                   accountsMap.put("firstname", firstName);


                    Template accountTemplate = configuration.getTemplate("account.ftl");

                    //   Map<String, Object> emptyMap = new HashMap<String, Object>();
                    accountTemplate.process(accountsMap, writer);

               }
               catch(Exception e)
               {
                             return null;
               }
                    return writer;
           }
        });


    }

}
