package com.example.demo.Model.API.PKCE;

import com.example.demo.Model.API.Api;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.pkce.AuthorizationCodePKCERefreshRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationCodePKCERefresh {

    private static final SpotifyApi spotifyApi = Api.spotifyApi;

    private static final AuthorizationCodePKCERefreshRequest authorizationCodePKCERefreshRequest = spotifyApi.authorizationCodePKCERefresh()
            .build();

    public static void authorizationCodeRefresh_Sync() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodePKCERefreshRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void authorizationCodeRefresh_Async() {
        try {
            final CompletableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = authorizationCodePKCERefreshRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeCredentialsFuture.join();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) {
        authorizationCodeRefresh_Sync();
        authorizationCodeRefresh_Async();
    }
}