package net.swimmingtuna.lotm.client;

public class ClientSpiritualityData {
    private static int playerSpirituality; //Used as spirituality

    public static void set(int spirituality) {
        ClientSpiritualityData.playerSpirituality = spirituality;
    } //defines getSpirituality as spirituality

    public static int getPlayerSpirituality() {
        return playerSpirituality;
    } //has getPlayerSpirituality as another way to call upon it but in a manner of getting it, this part is very confusing tbh.
}
