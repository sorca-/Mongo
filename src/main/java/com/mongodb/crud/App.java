package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.util.Helpers;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Map;
import java.util.Set;

public class App
{
    public static void main(String[] args)
    {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> collection = db.getCollection("students");
        Bson homeWorkFilter = Filters.eq("type", "homework");
        Bson filter = Filters.eq("countries.1", "Sweden");
//        Bson sorts = Sorts.ascending("student_id", "score");

//        int counter = 0;
//        for (Document document : collection.find().filter(filter))
//        {
//            counter++;
//        }
//        System.out.println(counter);

        MongoCursor<Document> cursor = collection.find().iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document student = cursor.next();
                Set<Map.Entry<String, Object>> entries = student.entrySet();
                for (Map.Entry<String, Object> entry : entries)
                {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                }

            }
        }
        finally
        {
            cursor.close();
        }

//        FindIterable<Document> homeWorks = collection.find().filter(homeWorkFilter).sort(sorts);
//        MongoCursor<Document> cursor = collection.find().filter(homeWorkFilter).sort(sorts).iterator();
//
//        try
//        {
//            int currentId = -1;
//           while (cursor.hasNext())
//           {
//               Document next = cursor.next();
//               if (!next.getInteger("student_id").equals(currentId))
//               {
//                   currentId = next.getInteger("student_id");
//                   collection.deleteOne(Filters.eq("_id", next.get("_id")));
//               }
//               
//           }
//        }
//        finally
//        {
//            cursor.close();
//        }
//
//        collection.updateOne(Filters.eq("_id", 1), Updates.set("examiner", "Jones"));
    }
}
