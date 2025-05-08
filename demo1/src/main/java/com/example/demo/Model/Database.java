package com.example.demo.Model;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

    private MongoClient mongoClient;
    private MongoDatabase database;

    // Constructor to initialize the MongoDB connection
    public Database(String connectionString, String dbName) {
        // Server API settings
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        // MongoDB client settings
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Establish the MongoDB connection
        try {
            this.mongoClient = MongoClients.create(settings);
            this.database = mongoClient.getDatabase(dbName);
        } catch (MongoException e) {
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to close the MongoDB connection when done
    public void closeConnection() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
        }
    }

    // Add a user to the "Users" collection
    public void addUserToDatabase(String username, String email, String password) {
        MongoCollection<Document> collection = database.getCollection("Users");
        try {
            Document newUser = new Document("username", username)
                    .append("email", email)
                    .append("password", password)
                    .append("friends", List.of()) // empty list
                    .append("favouriteSongs", List.of()) // empty list
                    .append("userTune","") // empty string
                    .append("profileImageNo","0"); // empty string


            collection.insertOne(newUser);
            System.out.println("User '" + username + "' added successfully.");
        } catch (MongoException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    // Add a friend to the user's "friends" list
    public void addFriendToDatabase(String username, String newFriend) {
        MongoCollection<Document> collection = database.getCollection("Users");
        try {
            collection.updateOne(
                    new Document("username", username), // Filter: find user with this username
                    new Document("$addToSet", new Document("friends", newFriend)) // Add to friends array
            );
            System.out.println("Friend '" + newFriend + "' added successfully to user '" + username + "'.");
        } catch (MongoException e) {
            System.err.println("Error adding friend: " + e.getMessage());
        }
    }

    // Perform a wildcard search on the "Users" collection for a specific query
    public TuneUser searchUserInDatabase(String searchQuery) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Modify the aggregation query to target only the "username" field and ensure case-insensitive matching
            AggregateIterable<Document> results = collection.aggregate(Arrays.asList(
                    new Document("$match",
                            new Document("username",
                                    new Document("$regex", searchQuery) // Regular expression for case-insensitive search
                                            .append("$options", "i") // "i" for case-insensitive search
                            )
                    )
            ));

            // If a matching document is found, process it
            for (Document doc : results) {
                // Extract fields from the document
                String username = doc.getString("username");
                String password = doc.getString("password");
                String mail = doc.getString("mail");
                int id = doc.getInteger("id", 0); // Default to 0 if no id is found
                ArrayList<Document> friendsDocs = (ArrayList<Document>) doc.get("friends"); //TODO forbidden cast
                ArrayList<Document> favouriteSongsDocs = (ArrayList<Document>) doc.get("favouriteSongs");

                // Convert friends from Document list to ArrayList<TuneUser>
                ArrayList<TuneUser> friends = new ArrayList<>();
                for (Document friendDoc : friendsDocs) { // TODO: getting error "class java.lang.String cannot be cast to class org.bson.Document"
                    String friendUsername = friendDoc.getString("username");
                    String friendPassword = friendDoc.getString("password");
                    String friendMail = friendDoc.getString("mail");
                    int friendId = friendDoc.getInteger("id", 0);
                    // Add each friend as a new TuneUser to the list
                    friends.add(new TuneUser(friendUsername, friendPassword, friendMail, friendId, new ArrayList<TuneUser>(), new ArrayList<Song>(), this));
                }

                // Convert favouriteSongs from Document list to ArrayList<Song>
                ArrayList<Song> favouriteSongs = new ArrayList<>();
                for (Document songDoc : favouriteSongsDocs) {
                    String trackID = songDoc.getString("trackID");
                    String name = songDoc.getString("name");
                    String artist = songDoc.getString("artist");
                    String language = songDoc.getString("language");
                    int year = songDoc.getInteger("year", 0);
                    String genre = songDoc.getString("genre");
                    String mood = songDoc.getString("mood");
                    String imageUrl = songDoc.getString("imageUrl");
                    int duration = songDoc.getInteger("duration", 0); // In milliseconds

                    // Create Song object using the updated constructor and add to the list
                    favouriteSongs.add(new Song(trackID, name, artist, language, year, genre, mood, imageUrl, duration));
                }

                // Construct the TuneUser object
                TuneUser user = new TuneUser(username, password, mail, id, friends, favouriteSongs, this);

                // Return the user directly
                return user;
            }

            // If no user matches the search query, return null
            return null;

        } catch (MongoException e) {
            System.err.println("Error performing search: " + e.getMessage());
            return null; // Return null in case of an error
        }
    }




    public void removeFriendFromDatabase(String username, String friendToRemove) {
        MongoCollection<Document> collection = database.getCollection("Users");
        try {
            collection.updateOne(
                    new Document("username", username), // Find user by username
                    new Document("$pull", new Document("friends", friendToRemove)) // Remove friend from the friends array
            );
            System.out.println("Friend '" + friendToRemove + "' removed successfully from user '" + username + "'.");
        } catch (MongoException e) {
            System.err.println("Error removing friend: " + e.getMessage());
        }
    }

    public boolean checkUserExistInDatabase(String username, String password) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Query to find a user with the provided username and password
            Document user = collection.find(new Document("username", username).append("password", password)).first();

            // If a matching user is found, return true, else false
            return user != null;

        } catch (MongoException e) {
            System.err.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }

    public boolean checkIfUserUnique(String username, String email) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Query to check if the username or email already exists
            Document existingUser = collection.find(
                    new Document("$or", Arrays.asList(
                            new Document("username", username),
                            new Document("email", email)
                    ))
            ).first();

            // If a matching user is found, return false (username or email already exists)
            return existingUser == null;

        } catch (MongoException e) {
            System.err.println("Error checking uniqueness: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUsernameInDatabase(String username, String newUserName) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Check if the new username already exists
            boolean isUnique = checkIfUserUnique(newUserName, "");
            if (!isUnique) {
                System.out.println("Error: The new username is already taken.");
                return false; // Return false if the new username is already in use
            }

            // Update the username if it's unique
            UpdateResult result = collection.updateOne(
                    new Document("username", username), // Find the user by the current username
                    new Document("$set", new Document("username", newUserName)) // Set the new username
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Username updated successfully.");
                return true;
            } else {
                System.out.println("Error: User not found or username is the same.");
                return false; // Return false if no user is found or the username is the same
            }

        } catch (MongoException e) {
            System.err.println("Error updating username: " + e.getMessage());
            return false;
        }
    }

    public boolean updateEmailInDatabase(String username, String newEmail) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Check if the new email already exists
            boolean isUnique = checkIfUserUnique("", newEmail);
            if (!isUnique) {
                System.out.println("Error: The new email is already in use.");
                return false; // Return false if the new email is already in use
            }

            // Update the email if it's unique
            UpdateResult result = collection.updateOne(
                    new Document("username", username), // Find the user by the current username
                    new Document("$set", new Document("email", newEmail)) // Set the new email
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Email updated successfully.");
                return true;
            } else {
                System.out.println("Error: User not found or email is the same.");
                return false; // Return false if no user is found or the email is the same
            }

        } catch (MongoException e) {
            System.err.println("Error updating email: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePasswordInDatabase(String username, String newPassword) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Update the password
            UpdateResult result = collection.updateOne(
                    new Document("username", username), // Find the user by the current username
                    new Document("$set", new Document("password", newPassword)) // Set the new password
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Password updated successfully.");
                return true;
            } else {
                System.out.println("Error: User not found or password is the same.");
                return false; // Return false if no user is found or the password is the same
            }

        } catch (MongoException e) {
            System.err.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

    public boolean updateProfileImagoNoInDatabase(String username, String newProfileImageNo) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Update the profile image no
            UpdateResult result = collection.updateOne(
                    new Document("username", username), // Find the user by the current username
                    new Document("$set", new Document("profileImageNo",newProfileImageNo)) // Set the new password
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Password updated successfully.");
                return true;
            } else {
                System.out.println("Error: User not found or password is the same.");
                return false; // Return false if no user is found or the password is the same
            }

        } catch (MongoException e) {
            System.err.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

    public boolean removeUserFromDatabase(String username) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Remove the user from the "Users" collection
            // First, remove the user from all friends' lists in the database
            collection.updateMany(
                    new Document("friends", username),  // Find all users who have this user in their friends list
                    new Document("$pull", new Document("friends", username))  // Remove the user from their friends list
            );

            // Then, remove the user from the "Users" collection
            DeleteResult result = collection.deleteOne(new Document("username", username));

            // Check if the deletion was successful
            if (result.getDeletedCount() > 0) {
                System.out.println("User '" + username + "' and their references in friends lists removed successfully.");
                return true;
            } else {
                System.out.println("Error: User not found.");
                return false;  // Return false if no user is found or the deletion was unsuccessful
            }

        } catch (MongoException e) {
            System.err.println("Error removing user: " + e.getMessage());
            return false;
        }
    }

    public boolean addFavouritesToDatabase(String username, String songName) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Check if the song already exists in the user's favourites
            Document user = collection.find(new Document("username", username)).first();
            if (user != null) {
                // Check if the song is already in the favouriteSongs list
                List<String> favouriteSongs = user.getList("favouriteSongs", String.class);
                if (favouriteSongs.contains(songName)) {
                    System.out.println("Error: Song is already in the favourites.");
                    return false;  // Song already exists in the favourites
                }
            }

            // Add the song to the favouriteSongs list
            UpdateResult result = collection.updateOne(
                    new Document("username", username),  // Find the user by their username
                    new Document("$addToSet", new Document("favouriteSongs", songName)) // Add song to favourites (without duplicates)
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Song '" + songName + "' added to '" + username + "'s favourites.");
                return true;
            } else {
                System.out.println("Error: User not found or song already in favourites.");
                return false;  // Return false if no user is found or the song was not added
            }

        } catch (MongoException e) {
            System.err.println("Error adding song to favourites: " + e.getMessage());
            return false;
        }
    }

    public boolean removeFavouritesFromDatabase(String username, String songName) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Remove the song from the favouriteSongs list
            UpdateResult result = collection.updateOne(
                    new Document("username", username),  // Find the user by their username
                    new Document("$pull", new Document("favouriteSongs", songName)) // Remove song from favourites
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Song '" + songName + "' removed from '" + username + "'s favourites.");
                return true;
            } else {
                System.out.println("Error: User not found or song not in favourites.");
                return false;  // Return false if no user is found or the song was not removed
            }

        } catch (MongoException e) {
            System.err.println("Error removing song from favourites: " + e.getMessage());
            return false;
        }
    }

    //add Song To Database
    public void addSongToDatabase(String trackId, String songName, String artist, String language, int year, String genre, String mood, String imageUrl, int duration, String parameters) {
        MongoCollection<Document> collection = database.getCollection("Songs");
        try {
            Document newSong = new Document("name", songName)
                    .append("artist", artist)
                    .append("trackId", trackId)
                    .append("language", language)
                    .append("year", year)
                    .append("genre", genre)
                    .append("mood", mood)
                    .append("imageUrl", imageUrl)
                    .append("duration", duration)
                    .append("parameters", parameters)
                    .append("currentPositionMS", 0);


            collection.insertOne(newSong);
            System.out.println("Song '" + songName + "' added successfully.");
        } catch (MongoException e) {
            System.err.println("Error adding song: " + e.getMessage());
        }
    }

    public void removeSongFromFavoritesFromDatabase(String username, String songName){
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Remove the song from the favouriteSongs list
            UpdateResult result = collection.updateOne(
                    new Document("username", username),  // Find the user by their username
                    new Document("$pull", new Document("favouriteSongs", songName)) // Remove song from favourites
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Song '" + songName + "' removed from '" + username + "'s favourites.");
            } else {
                System.out.println("Error: User not found or song not in favourites.");
            }

        } catch (MongoException e) {
            System.err.println("Error removing song from favourites: " + e.getMessage());
        }
    }

    public void addSongToFavoritesInDatabase(String username, String songName){
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Add the song to the favouriteSongs list
            UpdateResult result = collection.updateOne(
                    new Document("username", username),  // Find the user by their username
                    new Document("$addToSet", new Document("favouriteSongs", songName)) // Add song to favourites (without duplicates)
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Song '" + songName + "' added to '" + username + "'s favourites.");
            } else {
                System.out.println("Error: User not found or song already in favourites.");
            }

        } catch (MongoException e) {
            System.err.println("Error adding song to favourites: " + e.getMessage());
        }
    }

    public void updateSongCurrentPositionMS(String songName, int currentPositionMS) {
        MongoCollection<Document> collection = database.getCollection("Songs");

        try {
            // Update the current position of the song
            UpdateResult result = collection.updateOne(
                    new Document("name", songName),  // Find the song by its name
                    new Document("$set", new Document("currentPositionMS", currentPositionMS)) // Set the new current position
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Current position of song '" + songName + "' updated successfully.");
            } else {
                System.out.println("Error: Song not found or current position is the same.");
            }

        } catch (MongoException e) {
            System.err.println("Error updating song current position: " + e.getMessage());
        }
    }

    /*
    DOES NOT WORK !!!
    public Song searchSongInDatabase(String searchQuery) {
    }
    */


}

//import java.util.ArrayList;

//public class Database {

//
//    public boolean checkUserExistInDatabase(String username) { // DONE
//        return false;
//    }
//
//    public boolean checkIfUserUnique(String username, String email) { // DONE
//        return false;
//    }
//
//    public void addUserToDatabase(String username, String email, String password) { // DONE
//
//    }z
//
//    public void setUserTuneInDatabase(String username, boolean checkTuneExistence, Song tuneSong, String tuneNote) {
//
//    }
//
//    public void setTunedSongsInDatabase(String username, ArrayList<Song> TunedSongs) {
//
//    }
//
//
//    public void updateProfileImgInDatabase(String username, int profileImg) { // DONE
//
//    }
//
//    public void removeFriendFromDatabase(String username, String friendUsername) { // DONE
//
//    }
//
//    public void addFriendToDatabase(String username, String friendUsername) { // DONE
//
//    }
//
//    public Song searchSongInDatabase(String songName) {
//        return new Song();
//    }
//
//    public void removeUserFromDatabase(String username) { // DONE
//
//    }
//
//    public void updateUserInDatabase(String username, String email, String password) { // DONE
//
//    }
//
//    public TuneUser getUserFromDatabase(String username) {
//        return new TuneUser();
//    }
//
//    public void addSongToFavoritesInDatabase(String username, String songName) { // DONE
//
//    }
//
//    public void removeSongFromFavoritesFromDatabase(String username, String songName) { // DONE
//
//    }
//
//    public void updateNumbOfTunedSongsWithFriendsInDatabase(String username, int number) {
//
//    }
//
//
//    public Song suggestInstantTuneFromDatabase(String username) {
//        return new Song();
//    }
//
//    public Song suggestTuneWithFriendFromDatabase(String username) {
//        return new Song();
//    }
//
//    public Song suggestDetailedTuneFromDatabase(ArrayList<String> answers) {
//        return new Song();
//    }

//}
