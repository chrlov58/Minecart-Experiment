package me.chrlov58.minecarts;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.entity.Minecart;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_15_R1.Entity;
import net.minecraft.server.v1_15_R1.EntityMinecartRideable;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.World;

public class CartEntity extends EntityMinecartRideable {

	public CartEntity(World w) {
		super(EntityTypes.MINECART, w);
	}
	
	@Override
	public void collide(Entity collider) {
	}
	
	public static Minecart spawn(Location loc) {
		CartEntity entity = new CartEntity(((CraftWorld) loc.getWorld()).getHandle());
		entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
		((CraftWorld) loc.getWorld()).addEntity(entity, SpawnReason.CUSTOM);
		return (Minecart) entity.getBukkitEntity();
	}
	
}
