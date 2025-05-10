package com.example.demo.Model.API;

import com.example.demo.Model.Song;
import com.google.gson.JsonArray;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.player.PauseUsersPlaybackRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.requests.data.player.SkipUsersPlaybackToNextTrackRequest;
import se.michaelthelin.spotify.requests.data.player.SkipUsersPlaybackToPreviousTrackRequest;
import se.michaelthelin.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class Api {

    public static final String clientId = "2b230f5f59124ac189a5a17fccd5efcc";
    public static final String clientSecret = "89f49024768b4c5b837c2be769be09e8";
    public static final URI redirectUri = SpotifyHttpManager.makeUri("https://drallin.com/");

    public static final String accessTokenAuth = "BQBdiQjvVgFshKjfNs2NuP9kRdfAixUrEMmKGRdHrISpzHfk_lcGaCZ8qjdnA3881EPDobpQeAwMsQ9_17T7asSH9wZFlUKq8CFXyk1d0ylAUde_o2Jr_jh9VETlFma_TTDbPv5_My24k5AGuf0PCLJpQNxrJxRj2cZkDVTvnvTmR41pZFmT0NmpfnWF3J4M_L_bnLWQuhNl7_5XR23oIFacIR82ion79VwLPGERRnZSTXtgYfTj9w";
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