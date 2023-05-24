package cli.commands.status;

import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeStatus implements Runnable {
  private String name;

  public GetPokeStatus(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    // Make the API request
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon/" + name);
    String res = fetcher.getResponse();

    // Parse the JSON response
    String[] stats = res.split("\"stats\":\\[")[1].split("\\]")[0].split("\\},\\{");

    // Print the status of the Pokemon
    Logger.attention("Pokemon status for " + name + ":");
    System.out.println();
    for (String stat: stats) {
      String statName = stat.split("\"name\":\"")[1].split("\"")[0];
      int baseStat = Integer.parseInt(stat.split("\"base_stat\":")[1].split(",")[0]);
      Logger.log(statName + ": ");
      Logger.success(Integer.toString(baseStat));
      System.out.println();
    }
  }
}
