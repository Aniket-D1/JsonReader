package com.testvagrant.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import POJO.Player;

public class TeamValidations {

	private static final String FILE_PATH = "/src/main/res/TeamRCB.json";
	
	private static List<Player> playersInfo = new ArrayList<>();
	
	@BeforeMethod
	public void fetchPlayersDetails() {
		playersInfo = getPlayersInfo(FILE_PATH);
	}
	
	@Test
	public void validateForeignPlayersInTeam() {
		System.out.println("Verifying if Foreign players are equal to 4");
		int foreignPlayersCount = playersInfo.stream()
				.filter(player -> !player.getCountry().equalsIgnoreCase("India"))
				.collect(Collectors.toList())
				.size();
		Assert.assertEquals(foreignPlayersCount, 4, "Foreign players are not equal to 4");
	}
	
	@Test
	public void validateWicketKeepersInTeam() {
		System.out.println("Verifying if there is atleast one wicket keeper");
		boolean isWicketKeeper = playersInfo.stream()
				.filter(player -> player.getRole().equalsIgnoreCase("Wicket-keeper"))
				.findAny()
				.isPresent();
		Assert.assertTrue(isWicketKeeper, "There is no wicket keeper in the team");
	}
	
	private JsonObject getJsonObject(String filePath) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		File file = new File(System.getProperty("user.dir") + filePath);
		 return JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
	}
	
	private List<Player> getPlayersInfo(String filePath) {
		try {
			JsonArray jsonArrayOfPlayers = getJsonObject(filePath).get("player").getAsJsonArray();
			List<Player> playersList = new ArrayList<>();
			
			for(JsonElement playerInfo : jsonArrayOfPlayers) {
				JsonObject playerInfoObject = playerInfo.getAsJsonObject();
				
				String name = playerInfoObject.get("name").getAsString();
				String country  = playerInfoObject.get("country").getAsString();
				String role = playerInfoObject.get("role").getAsString();
				Double priceInCrores = playerInfoObject.get("price-in-crores").getAsDouble();
				
				playersList.add(new Player(name, country, role, priceInCrores));
			}
			return playersList;
			
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			Assert.fail("Error message:" + e.getMessage());
		}
		return null;
		
	}
}
