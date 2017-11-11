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

/**
 * Created by nazim on 10/11/17.
 */

public class ElasticsearchController {
        private static JestDroidClient client; //manages communication with a server

        // TODO we need a function which adds habits to elastic search
        public static class AddHabitsTask extends AsyncTask<Habit, Void, Void> {

            @Override
            protected Void doInBackground(Habit... habits) { //...means one or more
                verifySettings();

                for (Habit habit : habits) {
                    Index index = new Index.Builder(habits).index("testing").type("habit").build();

                    try {
                        // where is the client?
                        DocumentResult execute = client.execute(index);
                        if(execute.isSucceeded()){
                            habit.setId(execute.getId());
                        }
                    }
                    catch (Exception e) {
                        Log.i("Error", "The application failed to build and send the tweets");
                    }

                }
                return null;
            }
        }

        // TODO we need a function which gets tweets from elastic search
        public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<NormalTweet>> {
            @Override
            protected ArrayList<NormalTweet> doInBackground(String... search_parameters) {
                verifySettings();

                ArrayList<NormalTweet> tweets = new ArrayList<NormalTweet>();
                //String query = "" + search_parameters[0] +"";
                String query = "{\n" +
                        "    \"query\" : {\n" +
                        "        \"term\" : { \"message\" :\"" +search_parameters[0] +"\" }\n" +
                        "    }\n" +
                        "}";

                // TODO Build the query
                Search search = new Search.Builder(query).addIndex("testing").addType("tweet").build();


                try {
                    // TODO get the results of the query

                    SearchResult result = client.execute(search);
                    if(result.isSucceeded()){

                        List<NormalTweet> foundTweets = result.getSourceAsObjectList(NormalTweet.class);
                        tweets.addAll(foundTweets);
                    }
                    else{
                        Log.i("Error", "the search query failed to find any tweets that matched");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }

                return tweets;
            }
        }



        //sets up the server stuff
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
