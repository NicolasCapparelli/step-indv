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

package com.google.sps;

import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Set;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {

    // Edge Case no attendees 
    if (events.isEmpty() && request.getAttendees().isEmpty()) {
      return Arrays.asList(TimeRange.WHOLE_DAY);
    }

    // Edge Case meeting lasts longer than single day
    if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return Arrays.asList();
    }

    List<TimeRange> sortedEventTimes = getSortedEventTimes(events, request);
    return findOpenRanges(sortedEventTimes, request);

  }

  private List<TimeRange> getSortedEventTimes(Collection<Event> events, MeetingRequest request) {
    
    List<TimeRange> sortedEventList = new ArrayList<TimeRange>();
    
    Collection<String> meetingAttendees = request.getAttendees();

    for (Event ev : events) {
      
      Set<String> eventAttendees = ev.getAttendees();      
      for (String person : meetingAttendees) {

        // If just a single person from the meetingAttendees is in eventAttendees, add to list
        if (eventAttendees.contains(person)){
          sortedEventList.add(ev.getWhen());
          break;
        }
      }
      
    }

    Collections.sort(sortedEventList, TimeRange.ORDER_BY_START);
    return sortedEventList;
  }

  private Collection<TimeRange> findOpenRanges(List<TimeRange> sortedEventTimes, MeetingRequest request) {
    
    int cursor = TimeRange.START_OF_DAY;
    
    Collection<TimeRange> ranges = new ArrayList<TimeRange>();
    
    for (TimeRange eventRange : sortedEventTimes) {

      if (cursor > eventRange.start() && cursor < eventRange.end()) {
        cursor = eventRange.end();
        continue;
      } else if (cursor > eventRange.end()) {
        continue;
      }
      
      int timeOpen = eventRange.start() - cursor;      
      if (timeOpen >= request.getDuration()) {        
        TimeRange openRange = TimeRange.fromStartEnd(cursor, eventRange.start(), false);
        ranges.add(openRange);
      }
            
      cursor = eventRange.end();
    }

    // Add in time left after the last event
    if ((TimeRange.END_OF_DAY - cursor) >= request.getDuration()) {
      TimeRange openRange = TimeRange.fromStartEnd(cursor, TimeRange.END_OF_DAY, true);
      ranges.add(openRange);
    }

    return ranges;
  }

}
