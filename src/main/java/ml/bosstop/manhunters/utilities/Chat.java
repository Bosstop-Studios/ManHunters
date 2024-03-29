package ml.bosstop.manhunters.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat {
    private final char translateCharacter = '&';
    private final String prefix = "&0[&4ManHunt&0]&r";
    private HashMap<String, String> hexToMCColors = new HashMap<String, String>(); // HEX, MC-Color

    public Chat() {
        hexToMCColors.put("#000000", "0");
        hexToMCColors.put("#00002A", "1");
        hexToMCColors.put("#002A00", "2");
        hexToMCColors.put("#002A2A", "3");
        hexToMCColors.put("#2A0000", "4");
        hexToMCColors.put("#2A002A", "5");
        hexToMCColors.put("#2A2A00", "6");
        hexToMCColors.put("#2A2A2A", "7");
        hexToMCColors.put("#151515", "8");
        hexToMCColors.put("#15153F", "9");
        hexToMCColors.put("#153F15", "a");
        hexToMCColors.put("#153F3F", "b");
        hexToMCColors.put("#3F1515", "c");
        hexToMCColors.put("#3F153F", "d");
        hexToMCColors.put("#3F3F15", "e");
        hexToMCColors.put("#3F3F3F", "f");
    }

    public String colorize(String input) {
        if (hasHEXColorSupport()) {
            return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes(translateCharacter, input);
        } else {
            return ChatColor.translateAlternateColorCodes(translateCharacter,
                    hexCompatibilityConverter(translateCharacter, input));
        }
    }

    public void send(CommandSender sender, String input) {
        sender.sendMessage(colorize(prefix + " " + input));
    }

    public void messageBlast(List<Player> players, String input) {
        players.forEach((player) -> send(player, input));
    }

    public void console(String input) {
        Bukkit.getConsoleSender().sendMessage(colorize(input));
    }

    public boolean hasHEXColorSupport() {
        try {
            Class.forName("net.md_5.bungee.api.ChatColor");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private String hexCompatibilityConverter(char altColorChar, String inputHEX) {
        String output = "";
        int last_distance = Integer.MAX_VALUE;
        Color input_color = hex2Rgb(inputHEX);

        for (Entry<String, String> entry : hexToMCColors.entrySet()) {
            int distance = calculateColorDistance(input_color, hex2Rgb(entry.getKey()));
            if (distance < last_distance) {
                last_distance = distance;
                output = altColorChar + entry.getValue();
            }
        }
        return output;
    }

    private int calculateColorDistance(Color color1, Color color2) {
        int distance = Integer.MAX_VALUE;
        distance = (int) Math.round(
                Math.sqrt(Math.pow(
                        color1.getRed() - color2.getRed(), 2)
                        + Math.pow(color1.getGreen() - color2.getGreen(), 2)
                        + Math.pow(color1.getBlue() - color2.getBlue(), 2)));
        return distance;
    }

    private Color hex2Rgb(String colorStr) {
        return Color.fromRGB(Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
    }
}
