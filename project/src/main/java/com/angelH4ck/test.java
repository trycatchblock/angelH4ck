package com.angelH4ck;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: auvict
 * Date: 3/12/13
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class test {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("test");
        DBCollection collection =  database.getCollection("names");

        DBObject document = collection.findOne();
        System.out.println(document);

    }
}
