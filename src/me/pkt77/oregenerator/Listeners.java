package me.pkt77.oregenerator;

import java.util.List;
import java.util.Random;
//import org.bukkit.Material;
import cn.nukkit.block.BlockAir;
import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockFromEvent;
import cn.nukkit.math.*;
import cn.nukkit.level.Level;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.level.generator.Normal;

public class Listeners implements Listener {
	private OreGenerator _og;

	public Listeners(OreGenerator og) {
		_og = og;
		_og.getServer().getPluginManager().registerEvents(this, og);
	}

	@EventHandler
	public void onFromTo(BlockFromEvent event) {
		int id = event.getBlock().getId();
		if (id >= 8 && id <= 11) {
			Block block = event.getBlock();
			int toid = block.getId();
			if (toid == 0) {
				if (generatesCobble(id, block)) {
					List<String> worlds = _og.getConfig().getStringList("Worlds");
					if (worlds.contains(event.getBlock().getLocation().getWorld().getName())) {
						Random pick = new Random();
						int chance = 0;
						for (int counter = 1; counter <= 1; counter++) {
							chance = 1 + pick.nextInt(100);
						}

						double coal = _og.getConfig().getDouble("Chances.Coal");
						double iron = _og.getConfig().getDouble("Chances.Iron");
						double gold = _og.getConfig().getDouble("Chances.Gold");
						double redstone = _og.getConfig().getDouble("Chances.Redstone");
						double lapis = _og.getConfig().getDouble("Chances.Lapis");
						double emerald = _og.getConfig().getDouble("Chances.Emerald");
						double diamond = _og.getConfig().getDouble("Chances.Diamond");

						if (chance > 0 && chance <= coal) {
							this.setBlock(Block.COAL_ORE);
						}
						if (chance > coal && chance <= iron) {
							this.setBlock(Block.IRON_ORE);
						}
						if (chance > iron && chance <= gold) {
							this.setBlock(Block.GOLD_ORE);
						}
						if (chance > gold && chance <= redstone) {
							this.setBlock(Block.REDSTONE_ORE);
						}
						if (chance > redstone && chance <= lapis) {
							this.setBlock(Block.LAPIS_ORE);
						}
						if (chance > lapis && chance <= emerald) {
							this.setBlock(Block.EMERALD_ORE);
						}
						if (chance > emerald && chance <= diamond) {
							this.setBlock(Block.DIAMOND_ORE);
						}
						if (chance > diamond && chance <= 100) {
							this.setBlock(Block.COBBLESTONE);
						}
					}
				}
			}
		}
	}
    private final Vector3 getSide(int side, int step) {
        switch (side) {
            case Vector3.SIDE_DOWN:
            case Vector3.SIDE_UP:
            case Vector3.SIDE_NORTH:
            case Vector3.SIDE_SOUTH:
            case Vector3.SIDE_WEST:
            case Vector3.SIDE_EAST:
            default:
                return this;
        }
    }
	public boolean generatesCobble(int id, Block block) {
		int mirrorID1 = (id == 8 || id == 9 ? 10 : 8);
		int mirrorID2 = (id == 8 || id == 9 ? 11 : 9);
		for (Vector3 side : side) {
			Block blocks = block.getRelative(side, 1);
			if (blocks.getId() == mirrorID1 || blocks.getId() == mirrorID2) {
				return true;
			}
		}
		return false;
	}
}
