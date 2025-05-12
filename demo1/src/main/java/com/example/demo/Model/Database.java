package com.example.demo.Model;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;
import java.util.regex.Pattern;

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
                    .append("profileImageNo","0")
                    .append("numbOfTunedSongsWithFriends",0)
                    .append("tunedSongs", List.of())
                    .append("tuneNote",""); // empty string
                    // empty string


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

    // Search for a user in the database and return a TuneUser object and their friends are also Users but friends of friends string
    public TuneUser searchTuneUserInDatabase(String username) {
        MongoCollection<Document> collection = database.getCollection("Users");
        TuneUser user = null;

        try {
            // Case-insensitive regex pattern to match any part of the song name
            String pattern = ".*" + Pattern.quote(username) + ".*";
            Document foundUser = collection.find(
                    new Document("username", new Document("$regex", pattern).append("$options", "i"))
            ).first();


            if (foundUser != null) {
                ArrayList<TuneUser> friends = new ArrayList<TuneUser>();
                ArrayList<Song> favSongs = new ArrayList<Song>();
                ArrayList<Song> tunedSongs = new ArrayList<Song>();

                ArrayList<String> songNames = (ArrayList<String>) foundUser.getList("favouriteSongs", String.class);
                for (String songName : songNames) {
                    favSongs.add(searchSongInDatabase(songName));
                }

                ArrayList<String> tunedSongNames = (ArrayList<String>) foundUser.getList("tunedSongs", String.class);
                for (String songName : tunedSongNames) {
                    tunedSongs.add(searchSongInDatabase(songName));
                }

                ArrayList<String> friendNames = (ArrayList<String>) foundUser.getList("friends", String.class);

                for(String friendName : friendNames) {
                    String patternTwo = ".*" + Pattern.quote(friendName) + ".*";
                    Document foundFriend = collection.find(
                            new Document("username", new Document("$regex", patternTwo).append("$options", "i"))
                    ).first();
                    if (foundFriend != null) {
                        TuneUser aFriend = new TuneUser(foundFriend.getString("username"), "", "", 0, new ArrayList<TuneUser>(), new ArrayList<Song>(), new ArrayList<Song>(), Integer.parseInt(foundFriend.getString("profileImageNo")));
                        aFriend.setTune();
                        friends.add(aFriend);
                    }
                }

                // Construct the Song object
                user = new TuneUser(
                        foundUser.getString("username"),
                        foundUser.getString("password"),
                        foundUser.getString("email"),
                        112,
                        friends,
                        favSongs,
                        tunedSongs,
                        Integer.parseInt(foundUser.getString("profileImageNo"))
                );
            } else {
                System.out.println("No user found with the name: " + username);
            }

        } catch (MongoException e) {
            System.err.println("Error searching for user: " + e.getMessage());
            e.printStackTrace();
        }

        return user;

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
            // Check only if the username exists
            Document existingUser = collection.find(
                    new Document("username", username)
            ).first();

            if (existingUser != null) {
                return false; // username already exists
            }

            Document dummyUser = new Document("username", "__temp__check__")
                    .append("email", email);

            collection.insertOne(dummyUser);
            collection.deleteOne(new Document("username", "__temp__check__"));

            return true;

        } catch (MongoWriteException e) {
            // Unique index on email triggered a duplicate key error
            if (e.getError().getCategory() == ErrorCategory.DUPLICATE_KEY) {
                return false; // email already exists
            } else {
                System.err.println("Write error: " + e.getMessage());
                return false;
            }
        } catch (MongoException e) {
            System.err.println("MongoDB error: " + e.getMessage());
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

    public void increaseNumbOfTunedSongsWithFriendsInDatabase(String username) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Increment the number of tuned songs with friends
            UpdateResult result = collection.updateOne(
                    new Document("username", username),  // Find the user by their username
                    new Document("$inc", new Document("numbOfTunedSongsWithFriends", 1)) // Increment by 1
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Number of tuned songs with friends updated successfully.");
            } else {
                System.out.println("Error: User not found or number is already at maximum.");
            }

        } catch (MongoException e) {
            System.err.println("Error updating number of tuned songs with friends: " + e.getMessage());
        }
    }

    public int getNumbOfTunedSongsWithFriendsFromDatabase(String username) {
        MongoCollection<Document> collection = database.getCollection("Users");
        int number = 0;

        try {
            // Find the user by their username
            Document user = collection.find(new Document("username", username)).first();

            if (user != null) {
                // Get the number of tuned songs with friends
                number = user.getInteger("numbOfTunedSongsWithFriends", 0);
            } else {
                System.out.println("Error: User not found.");
            }

        } catch (MongoException e) {
            System.err.println("Error retrieving number of tuned songs with friends: " + e.getMessage());
        }

        return number;
    }


    public void setUserTuneInDatabase(String username, Song tuneSong, String tuneNote) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Update the user's tune
            UpdateResult result = collection.updateOne(
                    new Document("username", username),
                    new Document("$set", new Document("userTune", tuneSong.getName())
                            .append("tuneNote", tuneNote))
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("User's tune updated successfully.");
            } else {
                System.out.println("Error: User not found or tune is the same.");
            }

        } catch (MongoException e) {
            System.err.println("Error updating user's tune: " + e.getMessage());
        }
    }

    public String getUserTuneFromDatabase(String username) {
        MongoCollection<Document> collection = database.getCollection("Users");
        String tune = "";

        try {
            // Find the user by their username
            Document user = collection.find(new Document("username", username)).first();

            if (user != null) {
                // Get the user's tune
                tune = user.getString("userTune");
            } else {
                System.out.println("Error: User not found.");
            }

        } catch (MongoException e) {
            System.err.println("Error retrieving user's tune: " + e.getMessage());
        }

        return tune;
    }

    public String getTuneNoteFromDatabase(String username){
        MongoCollection<Document> collection = database.getCollection("Users");
        String tuneNote = "";

        try {
            // Find the user by their username
            Document user = collection.find(new Document("username", username)).first();

            if (user != null) {
                // Get the user's tune note
                tuneNote = user.getString("tuneNote");
            } else {
                System.out.println("Error: User not found.");
            }

        } catch (MongoException e) {
            System.err.println("Error retrieving user's tune note: " + e.getMessage());
        }

        return tuneNote;
    }

    //add a song name to tuneSongs in database
    public void addSongToTunedSongsInDatabase(String username, String songName) {
        MongoCollection<Document> collection = database.getCollection("Users");

        try {
            // Add the song to the tuneSongs list
            UpdateResult result = collection.updateOne(
                    new Document("username", username),  // Find the user by their username
                    new Document("$addToSet", new Document("tuneSongs", songName)) // Add song to tuneSongs (without duplicates)
            );

            // Check if the update was successful
            if (result.getModifiedCount() > 0) {
                System.out.println("Song '" + songName + "' added to '" + username + "'s tune songs.");
            } else {
                System.out.println("Error: User not found or song already in tune songs.");
            }

        } catch (MongoException e) {
            System.err.println("Error adding song to tune songs: " + e.getMessage());
        }
    }

    //get tunedSongs from database return Song arraylist
    public ArrayList<Song> getTunedSongsFromDatabase(String username) {
        MongoCollection<Document> collection = database.getCollection("Users");
        ArrayList<Song> tunedSongs = new ArrayList<>();

        try {
            // Find the user by their username
            Document user = collection.find(new Document("username", username)).first();

            if (user != null) {
                // Get the user's tuned songs
                List<String> tuneSongs = user.getList("tuneSongs", String.class);
                for (String songName : tuneSongs) {
                    Song song = searchSongInDatabase(songName);
                    if (song != null) {
                        tunedSongs.add(song);
                    }
                }
            } else {
                System.out.println("Error: User not found.");
            }

        } catch (MongoException e) {
            System.err.println("Error retrieving user's tuned songs: " + e.getMessage());
        }

        return tunedSongs;
    }



    //add Song To Database
    public void addSongToDatabase(String trackId, String songName, String artist, String language, int year, String genre, String mood, String imageUrl, int durationMS) {
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
                    .append("durationMS", durationMS)
                    .append("parameters", mood+" "+ genre +" "+ language + " "+year)
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

    public Song searchSongInDatabase(String searchQuery) {
        MongoCollection<Document> collection = database.getCollection("Songs");
        Song song = null;

        try {
            // Case-insensitive regex pattern to match any part of the song name
            String pattern = ".*" + Pattern.quote(searchQuery) + ".*";
            Document foundSong = collection.find(
                    new Document("name", new Document("$regex", pattern).append("$options", "i"))
            ).first();

            if (foundSong != null) {
                // Safely extract number fields
                Number yearNum = foundSong.get("year", Number.class);
                int year = (yearNum != null) ? yearNum.intValue() : 0;

                Number durationNum = foundSong.get("durationMS", Number.class);
                int durationMS = (durationNum != null) ? durationNum.intValue() : 0;

                // Handle null or blank imageUrl
                String imageUrl = foundSong.getString("imageUrl");
                if (imageUrl == null || imageUrl.isBlank()) {
                    imageUrl = "https://example.com/default-image.png"; // Replace with a valid fallback image
                }

                // Construct the Song object
                song = new Song(
                        foundSong.getString("trackId"),
                        foundSong.getString("name"),
                        foundSong.getString("artist"),
                        foundSong.getString("language"),
                        year,
                        foundSong.getString("genre"),
                        foundSong.getString("mood"),
                        imageUrl,
                        durationMS
                );
            } else {
                System.out.println("No song found with the name: " + searchQuery);
            }

        } catch (MongoException e) {
            System.err.println("Error searching for song: " + e.getMessage());
            e.printStackTrace();
        }

        return song;
    }

    public ArrayList<Song> getAllSongsInDatabase(){
        MongoCollection<Document> collection = database.getCollection("Songs");
        ArrayList<Song> songs = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Safely extract number fields
                Number yearNum = doc.get("year", Number.class);
                int year = (yearNum != null) ? yearNum.intValue() : 0;

                Number durationNum = doc.get("durationMS", Number.class);
                int durationMS = (durationNum != null) ? durationNum.intValue() : 0;

                // Handle null or blank imageUrl
                String imageUrl = doc.getString("imageUrl");
                if (imageUrl == null || imageUrl.isBlank()) {
                    imageUrl = "https://example.com/default-image.png"; // Replace with a valid fallback image
                }

                // Construct the Song object
                Song song = new Song(
                        doc.getString("trackId"),
                        doc.getString("name"),
                        doc.getString("artist"),
                        doc.getString("language"),
                        year,
                        doc.getString("genre"),
                        doc.getString("mood"),
                        imageUrl,
                        durationMS
                );
                songs.add(song);
            }
        } catch (MongoException e) {
            System.err.println("Error retrieving all songs: " + e.getMessage());
        }

        return songs;
    }



    //suggest random song that is not in favourites og user
    public Song suggestInstantTuneFromDatabase(String username) {
        MongoCollection<Document> usersCollection = database.getCollection("Users");
        MongoCollection<Document> songsCollection = database.getCollection("Songs");
        Song song = null;

        try {
            // Find the user by their username
            Document user = usersCollection.find(new Document("username", username)).first();

            if (user != null) {
                // Get the user's favourite song trackIds
                List<String> favouriteSongs = user.getList("favouriteSongs", String.class);
                if (favouriteSongs == null) {
                    favouriteSongs = new ArrayList<>();
                }

                // Find a random song that is not in the user's favourites
                Document randomSong = songsCollection.aggregate(Arrays.asList(
                        new Document("$match", new Document("trackId", new Document("$nin", favouriteSongs))),
                        new Document("$sample", new Document("size", 1))
                )).first();

                if (randomSong != null) {
                    // Safely extract number fields
                    Number yearNum = randomSong.get("year", Number.class);
                    int year = (yearNum != null) ? yearNum.intValue() : 0;

                    Number durationNum = randomSong.get("durationMS", Number.class);
                    int durationMS = (durationNum != null) ? durationNum.intValue() : 0;

                    // Handle null or blank imageUrl
                    String imageUrl = randomSong.getString("imageUrl");
                    if (imageUrl == null || imageUrl.isBlank()) {
                        imageUrl = "https://example.com/default-image.png"; // Replace with a valid fallback image
                    }

                    // Construct the Song object
                    song = new Song(
                            randomSong.getString("trackId"),
                            randomSong.getString("name"),
                            randomSong.getString("artist"),
                            randomSong.getString("language"),
                            year,
                            randomSong.getString("genre"),
                            randomSong.getString("mood"),
                            imageUrl,
                            durationMS
                    );
                } else {
                    System.out.println("No suitable song found for suggestion.");
                }
            } else {
                System.out.println("Error: User not found.");
            }

        } catch (MongoException e) {
            System.err.println("Error suggesting instant tune: " + e.getMessage());
        }

        return song;
    }

    public Song tuneWithFriend(String username, String friendName) {
        MongoCollection<Document> usersCollection = database.getCollection("Users");
        MongoCollection<Document> songsCollection = database.getCollection("Songs");

        try {
            Document user1 = usersCollection.find(new Document("username", username)).first();
            Document user2 = usersCollection.find(new Document("username", friendName)).first();

            if (user1 == null || user2 == null) {
                System.out.println("Error: One or both users not found.");
                return null;
            }

            List<String> favSongs1 = user1.getList("favouriteSongs", String.class);
            List<String> favSongs2 = user2.getList("favouriteSongs", String.class);
            if (favSongs1 == null) favSongs1 = new ArrayList<>();
            if (favSongs2 == null) favSongs2 = new ArrayList<>();

            Set<String> combinedFavourites = new HashSet<>(favSongs1);
            combinedFavourites.addAll(favSongs2);

            Map<String, Integer> genreCount = new HashMap<>();

            for (String songName : combinedFavourites) {
                Document songDoc = songsCollection.find(new Document("name", songName)).first();
                if (songDoc != null) {
                    String genre = songDoc.getString("genre");
                    if (genre != null) {
                        genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
                    }
                }
            }

            if (genreCount.isEmpty()) {
                System.out.println("No genres found in users' favourite songs.");
                return null;
            }

            String mostPopularGenre = Collections.max(genreCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Most popular genre: " + mostPopularGenre);
            Document suggestedSongDoc = songsCollection.aggregate(Arrays.asList(
                    new Document("$match", new Document("genre", mostPopularGenre)
                            .append("name", new Document("$nin", new ArrayList<>(combinedFavourites)))),
                    new Document("$sample", new Document("size", 1))
            )).first();

            if (suggestedSongDoc != null) {
                return buildSongFromDocument(suggestedSongDoc);
            } else {
                System.out.println("No song found in genre '" + mostPopularGenre + "' excluding favourites.");
                return null;
            }

        } catch (MongoException e) {
            System.err.println("Error suggesting genre-based song: " + e.getMessage());
            return null;
        }
    }

    public Song buildSongFromDocument(Document doc) {
        int year = doc.get("year", Number.class) != null ? doc.get("year", Number.class).intValue() : 0;
        int durationMS = doc.get("durationMS", Number.class) != null ? doc.get("durationMS", Number.class).intValue() : 0;
        String imageUrl = doc.getString("imageUrl");
        if (imageUrl == null || imageUrl.isBlank()) {
            imageUrl = "https://example.com/default-image.png";
        }

        return new Song(
                doc.getString("trackId"),
                doc.getString("name"),
                doc.getString("artist"),
                doc.getString("language"),
                year,
                doc.getString("genre"),
                doc.getString("mood"),
                imageUrl,
                durationMS
        );
    }

    //returns random if all parameters are together or returns null
    public ArrayList<Song> suggestDetailedTuneFromDatabase(String answerText) {
        MongoCollection<Document> collection = database.getCollection("Songs");
        List<Song> songs = new ArrayList<>();

        try {

            Bson regexFilter = Filters.regex("parameters", Pattern.compile(".*" + Pattern.quote(answerText) + ".*", Pattern.CASE_INSENSITIVE));

            FindIterable<Document> foundSongs = collection.find(regexFilter);

            for (Document foundSong : foundSongs) {
                Song song = buildSongFromDocument(foundSong);
                songs.add(song);
            }

        } catch (MongoException e) {
            System.err.println("Error suggesting random tune: " + e.getMessage());
            return null;
        }

        songs.add(suggestInstantTuneFromDatabase(""));
        return (ArrayList<Song>) songs;
    }

    public ArrayList<Song> suggestSearchBarTunesFromDatabase(String songName) {
        MongoCollection<Document> collection = database.getCollection("Songs");
        ArrayList<Song> songs = new ArrayList<>();

        try {
            String pattern = "^" + Pattern.quote(songName);

            FindIterable<Document> results = collection.find(
                    new Document("name", new Document("$regex", pattern).append("$options", "i"))
            ).limit(5); // En fazla 5 sonuç döndür

            for (Document doc : results) {

                Number yearNum = doc.get("year", Number.class);
                int year = (yearNum != null) ? yearNum.intValue() : 0;

                Number durationNum = doc.get("durationMS", Number.class);
                int durationMS = (durationNum != null) ? durationNum.intValue() : 0;


                String imageUrl = doc.getString("imageUrl");
                if (imageUrl == null || imageUrl.isBlank()) {
                    imageUrl = "https://example.com/default-image.png";
                }

                // Construct the Song object
                Song song = new Song(
                        doc.getString("trackId"),
                        doc.getString("name"),
                        doc.getString("artist"),
                        doc.getString("language"),
                        year,
                        doc.getString("genre"),
                        doc.getString("mood"),
                        imageUrl,
                        durationMS
                );
                songs.add(song);
            }

            if (songs.isEmpty()) {
                System.out.println("No songs found containing: " + songName);
            }

        } catch (MongoException e) {
            System.err.println("Error searching for songs: " + e.getMessage());
        }

        return songs;
    }







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
