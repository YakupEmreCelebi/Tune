package com.example.demo.Model.API;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.player.PauseUsersPlaybackRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.requests.data.player.StartResumeUsersPlaybackRequest;


import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.net.URI;

public class ApiTest {

    protected static final String clientId = "2b230f5f59124ac189a5a17fccd5efcc";
    protected static final String clientSecret = "89f49024768b4c5b837c2be769be09e8";
    protected static final URI redirectUri = SpotifyHttpManager.makeUri("https://drallin.com/");

    protected static final String accessTokenAuth = "BQA1iBAAJV4kuhfXzuTfXEhiFicSyr-nO6WPZfb5eGf-_hHe7SJWEr6_piJYDPqPV0J2P1qjZqKzQFFTcj38sZeu8kfHDgPuYMwCXfPfHHdCaC7CP6TMbG-zyRQ2ZOks3vYre6dIiJS5Tzxn7L5CC4uy_4GbwKiTLZ-CKMWo_SBCgYA2k4QL9LA_qkB5efHo2guL4IWpBaG2NmHZk1pHc96Lriy01u_hCCdyncCWsr5COsnXfQ";
    protected static final String refreshTokenAuth = "AQCqgsOMEZWtLTJLcmNgkyRUv_jt07QHKW4Y0XqAHjqr0xqEdDf1wUjC6bCG969KKksECbY1ZfOnZ4JMRQwGoDbe8sahDgvfLcnsUhOZTEbxSbDrHqcC007sfng9w5SUsrs";
    protected static final String accessTokenPKCE = "BQDPYgKxOAWsKI6vQQHacahaoioJOmoDhhDWWMRQgUMfqLn1aAIlNImJiWmWJzVHkELgwwIkzitjeutGuNG8np6tobYxZpnlke2kmIVrGXXngkFyODPi8kWaCoIqUzvsScwvU230QH2tNlNue-SWORrhcLEIadXcDh98byKZb-hZO-WUnQPo1512CNF1CDnCK8aRsAHzDpC6URkTLpA7fVG7ib1f-JoUxHmt-m2vjyLXOeYVKA";
    protected static final String refreshTokenPKCE = "AQC9evD_ES98_7UmO7Rf_bpS9nSM5HdaI-qQ5_MKv022N-5ISyhJCfqqrAnyfDlfyxeU7HjScQUnsRbvJRV0nLPdhqK6JQ0Sw6xJ5i83n9gy4n4eKDbwC2Q8JmF3pNeow0U";

    protected static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setAccessToken(accessTokenAuth)
            .setRefreshToken(refreshTokenAuth)
            .build();

    private static final PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback()
//          .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
            .build();

    private static final StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
            .startResumeUsersPlayback()
//          .context_uri("spotify:album:5zT1JLIj9E57p3e1rFm9Uq")
//          .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
//          .offset(JsonParser.parseString("{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}").getAsJsonObject())
//          .uris(JsonParser.parseString("[\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"]").getAsJsonArray())
//          .position_ms(10000)
            .build();

    public static void pauseUsersPlayback_Sync() {
        try {
            final String string = pauseUsersPlaybackRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void pauseUsersPlayback_Async() {
        try {
            final CompletableFuture<String> stringFuture = pauseUsersPlaybackRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final String string = stringFuture.join();

            System.out.println("Null: " + string);
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }


    public static void startResumeUsersPlayback_Sync() {
        try {
            final String string = startResumeUsersPlaybackRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void startResumeUsersPlayback_Async() {
        try {
            final CompletableFuture<String> stringFuture = startResumeUsersPlaybackRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final String string = stringFuture.join();

            System.out.println("Null: " + string);
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) throws IOException {
        startResumeUsersPlayback_Sync();

        pauseUsersPlayback_Sync();

    }

}