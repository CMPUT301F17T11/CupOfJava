/* ElasticSearchController
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;
import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

//TODO prj5

/**
 * Implements Elastic Search.
 *
 * @version 1.0
 */
public class ElasticsearchController {
    private static JestDroidClient client; //manages communication with a server
    //http://cmput301.softwareprocess.es:8080/cmput301f17t11_cupofjava

    // TODO we need a function which adds habits to elastic search

    /**
     * Adds habits to elastic search.
     *
     * @version 1.0
     */
    public static class AddUsersTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) { //...means one or more
            verifySettings();

            for (User user : users) {
                Index index = new Index.Builder(user).index("cmput301f17t11_cupofjava").type("user").build();

                try {
                    // where is the client?
                    DocumentResult execute = client.execute(index);
                    if(execute.isSucceeded()){
                        //user.setId(execute.getId());
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the users");
                }

            }
            return null;
        }
    }

    // TODO we need a function which gets tweets from elastic search

    /**
     * Gets habits from elastic search.
     *
     * @version 1.0
     */
    public static class GetUsersTask extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<User> users = new ArrayList<User>();
            //String query = "" + search_parameters[0] +"";
            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"username\" :\"" +search_parameters[0] +"\" }\n" +
                    "    }\n" +
                    "}";

            // TODO Build the query
            Search search = new Search.Builder(query).addIndex("cmput301f17t11").addType("user").build();


            try {
                // TODO get the results of the query

                SearchResult result = client.execute(search);
                if(result.isSucceeded()){

                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUsers);
                }
                else{
                    Log.i("Error", "the search query failed to find any users that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return users;
        }
    }



    /**
     * Sets up the server.
     */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
