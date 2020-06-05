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
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import static java.lang.Math.toIntExact;

import java.util.List; 
import java.util.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

import java.util.ArrayList;
import java.util.stream.Collectors; 
import com.google.sps.data.RatingData;

/** Endpoint for updating comment ratings'*/
@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // Create JSON object with GSON
        String requestData = request.getReader().lines().collect(Collectors.joining());
        RatingData ratingData = new Gson().fromJson(requestData, RatingData.class);    

        // Grab target comment with key created from commentID
        Key commentKey = KeyFactory.createKey("Comment", ratingData.getID());
        
        // Attempt to retrieve comment from datastore with Key
        Entity comment;
        try {
            comment = datastore.get(commentKey);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        
        if (ratingData.isUpvote()) {
            int upvotes = toIntExact((long) comment.getProperty("upvotes"));
            comment.setProperty("upvotes", ++upvotes);
        } else {
            int downvotes = toIntExact((long) comment.getProperty("downvotes"));
            comment.setProperty("downvotes", ++downvotes);
        }
        
        datastore.put(comment);        
    }

}
