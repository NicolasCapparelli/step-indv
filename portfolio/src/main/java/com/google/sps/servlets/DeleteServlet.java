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
import com.google.appengine.api.datastore.Key;

import java.util.List; 
import java.util.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

import java.util.ArrayList;
import java.util.stream.Collectors; 
import com.google.sps.data.DeleteData;

/** Endpoint for deleting comments. Has 'security'*/
@WebServlet("/delete-data")
public class DeleteServlet extends HttpServlet {

    private final String key = "NoAccessToMemeGenOrBadgesMakesMeSad";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Create JSON object with GSON
        String requestData = request.getReader().lines().collect(Collectors.joining());
        DeleteData deleteData = new Gson().fromJson(requestData, DeleteData.class);    

        // Delete all comments if credentials match
        if (deleteData.getKey().equals(key)) {
            deleteAllComments();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void deleteAllComments () {
        
        // Retrieve all comments from DataStore to get their key which is needed to delete
        Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);

        
        for (Entity entity : results.asIterable() ) {
            Key commentKey = entity.getKey();
            datastore.delete(commentKey);
        }
    }

}
