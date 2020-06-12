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
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


import static java.lang.Math.toIntExact;
import static java.lang.Math.min;

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

    private final int defaultNumComments = 5;
    private final int defaultPage = 1;

    @Override // Retrieves x comments
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Number of comments to return to the use
        int numComments;
        int page;
        try {
            numComments = Integer.parseInt(request.getParameter("numComments"));
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            System.out.println(e);
            
            numComments = defaultNumComments;
            page = defaultPage;
        }
                
        // Retrieving Comments from DataStore
        Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);

        // Create ArrayList of all comments retrieved form DataStore
        List<Comment> commentList = new ArrayList<>();
        
        // Calculating start and end index
        int startIndex = (page * numComments) - (numComments);
        List<Entity> entityList = results.asList(FetchOptions.Builder.withLimit(numComments * page));
        int endIndex = min(startIndex + (numComments + 1), entityList.size());

        for (int i = startIndex; i < endIndex; i++) {
            
            Entity entity = entityList.get(i);
            String name = (String) entity.getProperty("name");
            String message = (String) entity.getProperty("message");
            long timestamp = (long) entity.getProperty("timestamp"); 
            
            EmbeddedEntity voteMap = (EmbeddedEntity) entity.getProperty("voteMap");
            String userID = (String) entity.getProperty("userID");
            long id = entity.getKey().getId();

            Comment newComment = new Comment(name, message, timestamp, voteMap, userID, id);
            commentList.add(newComment);
        }
    

        // Convert commentList to JSON and send response
        Gson gson = new Gson();
        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(commentList));
    }

    @Override // Creates a new comment
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        
        if (userService.isUserLoggedIn()) {
            createCommnet(request, userService);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        
    }

    private void createCommnet(HttpServletRequest request, UserService userService) throws IOException {

        // To be used for timestamp
        long timestamp = System.currentTimeMillis();
        String userID = userService.getCurrentUser().getUserId();

        // Create JSON object with GSON
        String requestData = request.getReader().lines().collect(Collectors.joining());
        Comment comment = new Gson().fromJson(requestData, Comment.class);

        // Creating DataStore Entity
        Entity taskEntity = new Entity("Comment");
        taskEntity.setProperty("name", comment.getName());
        taskEntity.setProperty("message", comment.getMessage());
        taskEntity.setProperty("userID", userID);
        taskEntity.setProperty("timestamp", timestamp);

        EmbeddedEntity voteMap = new EmbeddedEntity();
        voteMap.setProperty(userID, 1);
        
        taskEntity.setProperty("voteMap", voteMap);

        // Placing Entity in datastore for persistant storage
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(taskEntity);
    }

}
