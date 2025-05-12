package com.example.demo.Model;

public class SongAdditionDatabase {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://admin:admin@project102.8x4g8.mongodb.net/?retryWrites=true&w=majority&appName=Project102";
        String dbName = "Project-102";

        // Initialize the database handler
        Database database = new Database(connectionString, dbName);

        database.addSongToDatabase("6K4t31amVTZDgR3sKmwUJJ","The Less I Know The Better", "Tame Impala", "EN", 2015, "pop", "relax", "https://media-hosting.imagekit.io/0a4c6d67be9e4ab2/download.jpg?Expires=1841061730&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=i5qYNIdRzYGeJeQrBQLYv-v2vrw-ZzSyBp8P5ggdprSk2Lcnf~7Dsajr7jHn8yrJm-03NMbRNTKbZgg2dLU9PKLzhYefsu-XGJOEk7fwlhH3ZylD7FfhT-K4h0wvQ5SfUZTrSPG2JM4XAQmDfw3BEkd5woguwV9Cl7ocFq-2KF8Z2C6e0nKfdFvYlEK1KIDBSsw~KQTLPDwPdSrpU7yXBthBmY7qBvOvXHywcy0AWfJPZvrPPUAA7TI~7UsUn7bBJvrtkj6sexeassE6cQaDFjW3uUZxzKofqxlLCQ0NriTDr-D8kWeCUTgGa6GxyRKZ18DtXL71mF6jJ2MJRlltbw__", 4);
        database.addSongToDatabase("6AU0mbi9zlNn8mYkam3PRR","Fish Maan", "Hotel Ugly", "EN", 2023, "indie", "relax", "https://media-hosting.imagekit.io/d83e3bc3fe9b478f/download.jpg?Expires=1841061858&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Fim2TOslQbw5Bg129HLDMNrnviYW-geOnH6PrN7wlzFO3HlLXP-M8NUnfI1F-QVVc1pcaHhzbN7rPskQe4~YA0sheXBb7MZchL6iQ6-QFx84-PRFAIcqHpSW3P0POTYw4FW8EzdANp4JnoS~N36Ga~L1JrVLTTW~9b7wMBAJ1Co-uIk~9mWhOzikyW4u6UudIlcAxvr1XfYQm6CPRncr1mT9R3Yk8qQHUmobbtQ~OPWPLz45C7QSDd-gD11I6kNSYJZ3P8wgGu~idOBMrolFmlMJ--Av4BeQdukxd1aKZRlmzDTgaq7mjAcZi1tLnmVZBBlR-gvp5s4q3iqZm0IUKg__", 4);
        database.addSongToDatabase("7KtPUqnxtCkfFfvot80yPM","Seattle", "eamon mo", "EN", 2024, "indie", "relax", "https://media-hosting.imagekit.io/7d3c90f6e4e943b5/download.jpg?Expires=1841058444&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=Z0sv5NAwrgSZJiRumKf~2McaQoyh-Xlc513BiPIp88W~WiERxe8X6XADOAt272ykz88faEvAfUinnWLUS64cqSSkk39KdwVYeHT4RszLruiDjL77MBkkuaYHAJWTQ3qJ6to48BSEeYkkNv069UxtOAUHplneTdyySUh2t9a2s7ZqE089CtmU9TMN-UYXw6JurfMOZ9qUXzw8Ktf-YCuiDUYssQlSQg-1MXcdLclbWfuaNPHcHjM6SNUe3G4nlMh0JWACCHWw8jovKuH~HL2O7l8X5ZHL0Q1k-gdHAd1DHX8DtbCgILEfT9uxSNwX4zeAesKZbpgmPDp5oTjL129jCw__", 4);
        database.addSongToDatabase("7o7E1nrHWncYY7PY94gCiX","Video Killed The Radio Star", "The Buggles", "EN", 1980, "indie", "good", "https://media-hosting.imagekit.io/bf502f9321f14090/download.jpg?Expires=1841062015&Key-Pair-Id=K2ZIVPTIP2VGHC&Signature=bEQ4F9vrw56SrAMqMv1YPv6pVCxf6ULbGIhLB~ZHJ8BEq48pt3tubOBmd7Ki8ZXL6afyrLsJ2DMmd7V5~D9KRE8y2Gw0lK7P7ExyQi2VH7GWia8N2R9VpmgKz-pbkcvO9hkY35eWQsRu5Nm6~0ZMl-4yAEll7B2g5fSmdIXp0FBf6nY6dnkWicJ9dHTGgw~JBqJYngu48cmZDUOMVGqmcbssGY3brQ7wbpMkkCpYgwvghgPJZeOZydW-67NSNx1dcUqSVGecID8rbr5dvmqfGEeclLX0wIVwNCcCTCbKY7dA5vVhOGRG-QF9ZdKQubuM76SeURK1FtyvKsPFhUIOig__", 4);

    }
}
