package com.example.demo.Model.API;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.player.PauseUsersPlaybackRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;


import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.net.URI;

public class ApiTest {

    protected static final String clientId = "2b230f5f59124ac189a5a17fccd5efcc";
    protected static final String clientSecret = "89f49024768b4c5b837c2be769be09e8";
    protected static final URI redirectUri = SpotifyHttpManager.makeUri("https://drallin.com/");

    protected static final String accessTokenAuth = "BQA3qdN0CwgPILf1cmfsuogp42uZP6d-jIuQCUVHZ0I_RpX_Jv-C9Q6cIakni2420pMfvrMGqSsGi1xqPOzLNXtpDNq6cHQhxBMu3gx2AbsP8Mzi9BtYTa7vUtFNLQtGTB4DgpqUKOfiz2rV0p0YMBLsfGdfxs5NNF2Vc3rZgnL0F-KcWna4pu1JGzLNHFSdrZ3jz4SlQhs2PN92EYG1tOZ_qa3ZeDZCfTGJ_rjWv73Uqw5ZiA";
    protected static final String refreshTokenAuth = "AQCqgsOMEZWtLTJLcmNgkyRUv_jt07QHKW4Y0XqAHjqr0xqEdDf1wUjC6bCG969KKksECbY1ZfOnZ4JMRQwGoDbe8sahDgvfLcnsUhOZTEbxSbDrHqcC007sfng9w5SUsrs";
    //protected static final String accessTokenPKCE = "BQDIcueIAgtZg7TGGVcm0k5kmuRJd4sInbFHJ1vm4mmmzzFTEX9_t0D_Gpt_2SHNCY46utG2FDB13GT8UqEyKnbM_7NmlAca2LMMCHtc_RUs8woFYgmyXVFzRrVB_HeuNP7CZLoliC61LCERaQ2V6z1mAm1jzrYzrqgXAHBxssnEfdtCdHf3v2fe42sWNkc8TWiPP7fshWJ1Pf4hA_WMIWyjhR9u3_66VLK_O5o7MtG8ju3aAQ";
    //protected static final String refreshTokenPKCE = "AQC9evD_ES98_7UmO7Rf_bpS9nSM5HdaI-qQ5_MKv022N-5ISyhJCfqqrAnyfDlfyxeU7HjScQUnsRbvJRV0nLPdhqK6JQ0Sw6xJ5i83n9gy4n4eKDbwC2Q8JmF3pNeow0U";

    private static final String id = "01iyCAUm8EvOFqVWYJ3dVX";

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

    //********************************
    private static final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
//          .market(CountryCode.SE)
            .build();

    public static void getTrack_Sync() {
        try {
            final Track track = getTrackRequest.execute();


            System.out.println("Name: " + track.getName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getTrack_Async() {
        try {
            final CompletableFuture<Track> trackFuture = getTrackRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Track track = trackFuture.join();

            System.out.println("Name: " + track.getName());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }


    public static void main(String[] args) throws IOException {
        pauseUsersPlayback_Sync();
    }

}