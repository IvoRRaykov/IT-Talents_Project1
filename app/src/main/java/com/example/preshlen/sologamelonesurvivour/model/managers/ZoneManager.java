package com.example.preshlen.sologamelonesurvivour.model.managers;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.classes.User;
import com.example.preshlen.sologamelonesurvivour.model.classes.Zone;
import com.example.preshlen.sologamelonesurvivour.model.enums.LevelEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Preshlen on 3/10/2016.
 */
public class ZoneManager {

    private static HashMap<LevelEnum, ArrayList<Zone>> createdZones = new HashMap<LevelEnum, ArrayList<Zone>>();


    public static void createZones(){

        ArrayList<Zone> zones1 = new ArrayList<>();
        zones1.add(new Zone("zone11", LevelEnum.LEVEL1));
        zones1.add(new Zone("zone12", LevelEnum.LEVEL1));
        zones1.add(new Zone("zone13", LevelEnum.LEVEL1));
        zones1.add(new Zone("zone14", LevelEnum.LEVEL1));

        ArrayList<Zone> zones2 = new ArrayList<>();
        zones2.add(new Zone("zone21", LevelEnum.LEVEL1));
        zones2.add(new Zone("zone22", LevelEnum.LEVEL1));
        zones2.add(new Zone("zone23", LevelEnum.LEVEL1));
        zones2.add(new Zone("zone24", LevelEnum.LEVEL1));

        ArrayList<Zone> zones3 = new ArrayList<>();
        zones3.add(new Zone("zone31", LevelEnum.LEVEL1));
        zones3.add(new Zone("zone32", LevelEnum.LEVEL1));

        createdZones.put(LevelEnum.LEVEL1, zones1);
        createdZones.put(LevelEnum.LEVEL2, zones2);
        createdZones.put(LevelEnum.LEVEL3, zones3);
    }


    public static void giveZonesToPlayer(User player) {
        User otherPlayer = new User("Bot","Bot","Bot");
        for (LevelEnum enumLevel : createdZones.keySet()) {
            ArrayList<Zone> zones = createdZones.get(enumLevel);
            ArrayList<Zone> playerOneZones = player.getZones();
            ArrayList<Zone> playerTwoZones = otherPlayer.getZones();
            if (enumLevel.getLevel() == 3) {
                playerOneZones.add(zones.get(0));
                playerTwoZones.add(zones.get(1));
                player.setZones(playerOneZones);
                otherPlayer.setZones(playerTwoZones);
            } else {
                playerOneZones.add(zones.get(0));
                playerOneZones.add(zones.get(1));
                playerTwoZones.add(zones.get(2));
                playerTwoZones.add(zones.get(3));
                player.setZones(playerOneZones);
                otherPlayer.setZones(playerTwoZones);
            }
        }
        for(Zone zone: player.getZones()){
            zone.setColor(player.getColor());
        }
    }
}
