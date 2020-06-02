// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.util.List; 
import java.util.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

import java.util.ArrayList;
import java.util.stream.Collectors; 
import com.google.sps.data.Comment;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/comments")
public class DataServlet extends HttpServlet {

    ArrayList<Comment> commentList = new ArrayList<>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Retrieving Comments from DataStore
        Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);

        // Create ArrayList of all comments retrieved form DataStore
        List<Comment> commentList = new ArrayList<>();
        for (Entity entity : results.asIterable()) {
            String name = (String) entity.getProperty("name");
            String message = (String) entity.getProperty("message");
            String timestamp = (String) entity.getProperty("timestamp");
            long id = entity.getKey().getId();

            Comment newComment = new Comment(name, message, timestamp, id);
            commentList.add(newComment);
        }

        // Convert commentList to JSON and send response
        Gson gson = new Gson();
        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(commentList));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // To be used for timestamp
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        String strDate = dateFormat.format(currentDate);

        // Create JSON object with GSON
        String requestData = request.getReader().lines().collect(Collectors.joining());
        Comment comment = new Gson().fromJson(requestData, Comment.class);

        // Creating DataStore Entity
        Entity taskEntity = new Entity("Comment");
        taskEntity.setProperty("name", comment.getName());
        taskEntity.setProperty("message", comment.getMessage());
        taskEntity.setProperty("timestamp", strDate);

        // Placing Entity in datastore for persistant storage
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(taskEntity);
    }
}
