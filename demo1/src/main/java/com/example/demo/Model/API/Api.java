package com.example.demo.Model.API;

import com.example.demo.Model.Song;
import com.google.gson.JsonArray;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.player.*;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Api {

    public static final String clientId = "2b230f5f59124ac189a5a17fccd5efcc";
    public static final String clientSecret = "89f49024768b4c5b837c2be769be09e8";
    public static final URI redirectUri = SpotifyHttpManager.makeUri("https://drallin.com/");

    public static final String accessTokenAuth = "BQDWQ7t94J8E-YpOb6IA_Z3QkKML0hd1S3vwe0hGHhW68_-A7wJRglNUfGk2zq7r42LnW-z9RCGXJ-DGOoteRAXPcoj9Ndn4jJbOMowyz8DVpg_xaYmAJlnxuP1w7DK_6nh6DwI-AvdIOuki-4PW4FUXYfcfx-pOovuF6QY0NVMiQhW_-ImSZIbdUb05oncB2wetOfxo6I1phqJb2i83gRJ8fjpOkOZh3EmXwBR_i0Po__W0-vN5xA";
    public static final String refreshTokenAuth = "AQCqgsOMEZWtLTJLcmNgkyRUv_jt07QHKW4Y0XqAHjqr0xqEdDf1wUjC6bCG969KKksECbY1ZfOnZ4JMRQwGoDbe8sahDgvfLcnsUhOZTEbxSbDrHqcC007sfng9w5SUsrs";


    public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setAccessToken(accessTokenAuth)
            .setRefreshToken(refreshTokenAuth)
            .build();

    private static final PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback()
//          .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
            .build();


    private static final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi
            .skipUsersPlaybackToNextTrack()
//          .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
            .build();

    private static final SkipUsersPlaybackToPreviousTrackRequest skipUsersPlaybackToPreviousTrackRequest = spotifyApi
            .skipUsersPlaybackToPreviousTrack()
//          .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
            .build();



    private static StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest;

//            = spotifyApi
//            .startResumeUsersPlayback()
//            .context_uri("spotify:album:5zT1JLIj9E57p3e1rFm9Uq")
//*          .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
//*          .offset(JsonParser.parseString("{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}").getAsJsonObject())
//*          .uris(JsonParser.parseString("[\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"]").getAsJsonArray())
//*          .position_ms(10000)
//            .build();

    private static final GetUsersCurrentlyPlayingTrackRequest getUsersCurrentlyPlayingTrackRequest = spotifyApi
            .getUsersCurrentlyPlayingTrack()
//          .market(CountryCode.SE)
            .additionalTypes("track")
            .build();





    public void pausePlayback() {
        try {
            pauseUsersPlaybackRequest.execute();

            System.out.println("Paused");
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void startResumePlayback(Song song, ArrayList<Song> songsList) {
        JsonArray nextSongs = new JsonArray();
        nextSongs.add("spotify:track:" + song.getTrackID());
        for (Song nextSong : songsList) {
            nextSongs.add("spotify:track:" + nextSong.getTrackID());
        }

        startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback()
                .uris(nextSongs)
                .position_ms(song.getCurrentPositionMS())
                .build();

        try {
            startResumeUsersPlaybackRequest.execute();

            System.out.println("Started");
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //************************ For fun
    public void startGS() {
        JsonArray nextSongs = new JsonArray();
        nextSongs.add("spotify:track:0mOMxAtKwV4tfW0eYhWPbt");

        startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback()
                .uris(nextSongs)
                .position_ms(36000)
                .build();

        try {
            startResumeUsersPlaybackRequest.execute();

            System.out.println("Started");
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    //************************

    public void startResumePlayback() {
        startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback().build();

        try {
            startResumeUsersPlaybackRequest.execute();

            System.out.println("Started");
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void startTrackFromRandomPos(Song song) {
        JsonArray nextSongs = new JsonArray();
        nextSongs.add("spotify:track:" + song.getTrackID());

        startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback()
                .uris(nextSongs)
                .position_ms((int) (Math.random() * 45000) + 30000)
                .build();

        try {
            startResumeUsersPlaybackRequest.execute();

            System.out.println("Started");
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void skipPlaybackToNextTrack() {
        try {
            skipUsersPlaybackToNextTrackRequest.execute();

            System.out.println("Next");
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void skipPlaybackToPreviousTrack() {
        try {
            skipUsersPlaybackToPreviousTrackRequest.execute();

            System.out.println("Previous");
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}