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

import io.searchbox.core.Delete;
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

    public static class AddUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users) {
                Index index = new Index.Builder(user).index("cmput301f17t11_cupofjava").type("user").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        user.setId(result.getId());
                    } else {
                        Log.i("Error", "Elasticsearch was not able to add the user");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build");
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
    public static class GetUserTask extends AsyncTask<String, Void, User> {
        private User thisUser;

        @Override
        protected User doInBackground(String... search_parameters) {
            verifySettings();

            // TODO Build the query

            //String query = "{\"query\":{\"match\":{\"username\":"+search_parameters[0]+"}}}";
            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "       \"constant_score\" : {\n" +
                    "           \"filter\" : {\n" +
                    "               \"term\" : {\"username\": \"" + search_parameters[0] + "\"}\n" +
                    "             }\n" +
                    "         }\n" +
                    "    }\n" +
                    "}";

            Search search = new Search.Builder(query).addIndex("cmput301f17t11_cupofjava").addType("user").build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    thisUser = result.getFirstHit(User.class).source;
                } else {
                    Log.i("Error", "The search query failed to find any Users that matched");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return thisUser;
        }
    }

    public static class UpdateUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users) {

                Index index = new Index.Builder(user).index("cmput301f17t11_cupofjava").type("user").id(user.getId()).build();

                try {
                    DocumentResult result = client.execute(index);
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build");
                }

            }
            return null;
        }
    }

    public static class AddHabitTask extends AsyncTask<Habit, Void, Void> {
        @Override
        protected Void doInBackground(Habit... habits) {
            verifySettings();

            for (Habit habit : habits) {
                Index index = new Index.Builder(habit).index("cmput301f17t11_cupofjava").type("habit").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        habit.setId(result.getId());

                    } else {
                        Log.i("Error", "Elasticsearch was not able to add the habit");
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build");
                }

            }
            return null;
        }
    }

    public static class GetHabitsTask extends AsyncTask<String, Void, ArrayList<Habit>> {
        @Override
        protected ArrayList<Habit> doInBackground(String... search_parameters) {
            verifySettings();
            ArrayList<Habit> habits = new ArrayList<Habit>();

            // TODO Build the query

            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "       \"constant_score\" : {\n" +
                    "           \"filter\" : {\n" +
                    "               \"term\" : {\"username\": \"" + search_parameters[0] + "\"}\n" +
                    "             }\n" +
                    "         }\n" +
                    "    }\n" +
                    "}";
            Search search = new Search.Builder(query)
                    .addIndex("cmput301f17t11_cupofjava")
                    .addType("habit")
                    .build();


            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Habit> foundhabits = result.getSourceAsObjectList(Habit.class);
                    habits.addAll(foundhabits);
                } else {
                    Log.i("Error", "The search query failed to find any habits that matched");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return habits;
        }
    }

    public static class UpdateHabitTask extends AsyncTask<Habit, Void, Void> {
        @Override
        protected Void doInBackground(Habit... habits) {
            verifySettings();
            for (Habit habit : habits) {
                Index index = new Index.Builder(habit).index("cmput301f17t11_cupofjava").type("habit").id(habit.getId()).build();

                try {
                    DocumentResult result = client.execute(index);
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build");
                }

            }
            return null;
        }
    }

    public static class DeleteHabitsTask extends AsyncTask<Habit, Void, Void> {
        @Override
        protected Void doInBackground(Habit... habits) {
            verifySettings();

            // TODO Build the query


            for (Habit habit : habits) {
                Delete delete = new Delete.Builder(habit.getId()).index("cmput301f17t11_cupofjava").type("habit").build();

                try {
                    DocumentResult result = client.execute(delete);
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build");
                }

            }
            return null;
        }
    }

    public static class AddEventTask extends AsyncTask<HabitEvent, Void, Void> {
        @Override
        protected Void doInBackground(HabitEvent... events) {
            verifySettings();
            for (HabitEvent habitEvent : events) {
                Index index = new Index.Builder(habitEvent).index("cmput301f17t11_cupofjava").type("event").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        habitEvent.setId(result.getId());
                    } else {
                        Log.i("Error", "Elasticsearch was not able to add the user");
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build");
                }

            }
            return null;
        }
    }

    public static class GetEventsTask extends AsyncTask<String, Void, ArrayList<HabitEvent>> {
        @Override
        protected ArrayList<HabitEvent> doInBackground(String... search_parameters) {
            verifySettings();
            ArrayList<HabitEvent> events = new ArrayList<HabitEvent>();


            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "       \"constant_score\" : {\n" +
                    "           \"filter\" : {\n" +
                    "               \"term\" : {\"username\": \"" + search_parameters[0] + "\"}\n" +
                    "             }\n" +
                    "         }\n" +
                    "    }\n" +
                    "}";

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f17t11_cupofjava")
                    .addType("event")
                    .build();


            try {
                // get results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<HabitEvent> foundEvents = result.getSourceAsObjectList(HabitEvent.class);
                    events.addAll(foundEvents);
                } else {
                    Log.i("Error", "The search query failed to find any tweets that matched");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return events;
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
